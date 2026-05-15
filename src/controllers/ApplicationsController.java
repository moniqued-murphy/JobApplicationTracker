/**
* Lead Author(s):
* @author Monique Murphy; 0005396987
* 
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
* 
*
* Version: 2026-05-13
*/
package controllers;

import repositories.ApplicationRepository;
import views.AddApplicationView;
import views.ApplicationsView;
import services.FileService;
import models.ApplicationModel;

/**
 * Purpose: The responsibility of ApplicationsController is ...
 * To manage the flow of data between the ApplicationsView and the ApplicationRepository
 *
 * ApplicationsController is-a ...
 * ApplicationsController is ...
 */
public class ApplicationsController
{
	private ApplicationsView view;
	private ApplicationRepository repo;
	private FileService fileService;
	
	/**
	 * Constructor for ApplicationsController
	 * Purpose: Initialize the ApplicationsController with the given view and repository
	 * 
	 * @param view the ApplicationsView object that this controller will manage
	 * @param repo the ApplicationRepository object that this controller will use to access application data
	 */
	public ApplicationsController(ApplicationsView view, ApplicationRepository repo)
	{
		fileService = new FileService("Applications.txt");
		this.view = view;
		this.repo = repo;
		
		repo.getApplications().addAll(fileService.loadApplications());
		
		view.displayApplications(repo.getApplications());
		
		view.getAddButton().addActionListener(e ->
		{
			AddApplicationView addView =
					new AddApplicationView();

			addView.getSaveButton().addActionListener(saveEvent ->
			{
				try
				{
					ApplicationModel application =
							new ApplicationModel(
									addView.getCompanyName(),
									addView.getJobTitle(),
									addView.getJobLocation(),
									Double.parseDouble(
											addView.getPay()),
									addView.getDateApplied(),
									addView.getStatus(),
									addView.getNotes(),
									addView.getJobType()
							);

					repo.addApplication(application);

					fileService.saveApplications(
							repo.getApplications());

					view.displayApplications(
							repo.getApplications());

					addView.clearForm();

					System.out.println(
							"Application saved!");
				}
				catch (NumberFormatException error)
				{
					System.out.println(
							"Please enter a valid pay amount.");
				}
			});
		});
		
		view.getSearchButton().addActionListener(e ->
		{
			String searchText = view.getSearchText();
		});
		
		view.getFilterBox().addActionListener(e ->
		{
			String selectedFilter = (String)view.getFilterBox().getSelectedItem();
		});
		
		view.getDeleteButton().addActionListener(e ->
		{
			int selectedIndex = view.getSelectedApplicationIndex();

		});
	}
	
}
