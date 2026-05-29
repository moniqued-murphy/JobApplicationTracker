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
* Version: 2026-05-28
*/
package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import models.ApplicationModel;
import repositories.ApplicationRepository;
import views.AddApplicationView;
import views.ApplicationsView;
import views.DashboardView;
import services.FileService;
import services.StatisticsService;

/**
 * The responsibility of DashboardController is ...
 * To control the interactions between the DashboardView and the underlying data and services,
 * such as loading applications from a file, updating the dashboard statistics and recent applications
 * 
 */
public class DashboardController
{
	private DashboardView dashboardView;
	private ApplicationRepository repository;
	private FileService fileService;
	private StatisticsService statesService;
	
	/**
	 * Constructor
	 * Purpose: Create an instance of DashboardController
	 * 
	 * @param dashboardView
	 * @param repository
	 * @param fileService
	 * @param statesService
	 */
	public DashboardController(DashboardView dashboardView, ApplicationRepository repository, FileService fileService, StatisticsService statesService)
	{
		this.dashboardView = dashboardView;
		this.repository = repository;
		this.fileService = fileService;
		this.statesService = statesService;
		
		// Load applications from file and add them to the repository
		repository.getApplications().addAll(fileService.loadApplications());
		
		// Show the stats and recent applications on the dashboard
		refreshDashboard();

		// Add action listener for the Applications button
		dashboardView.getApplicationsButton().addActionListener(e ->
		{
			ApplicationsView applicationsView = new ApplicationsView();
			new ApplicationsController(applicationsView, repository);

			// When Applications window closes, refresh the dashboard
			applicationsView.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosed(WindowEvent e)
				{
					refreshDashboard();
				}
			});
		});

		// Action listener for the Add Application button
		dashboardView.getAddApplicationButton().addActionListener(e ->
		{
			AddApplicationView addView =
					new AddApplicationView();
			
			// Add action listener for the Save button in the Add Application view
			addView.getSaveButton().addActionListener(saveEvent ->
			{
				addView.hidePayError();
				addView.hideDateError();

				String payText = addView.getPay();
				String dateText = addView.getDateApplied();

				boolean valid = true;
				double pay = 0;

				// Validate the pay input and date format
				try
				{
					pay = Double.parseDouble(payText);
				}
				catch (NumberFormatException error)
				{
					addView.showPayError("Please enter a valid number (e.g. 5000.00).");
					valid = false;
				}

				if (!dateText.matches("\\d{2}/\\d{2}/\\d{2}"))
				{
					addView.showDateError("Please use MM/DD/YY format (e.g. 05/21/26).");
					valid = false;
				}
				
				// If either validation fails, do not proceed with creating the application
				if (!valid)
				{
					return;
				}

				ApplicationModel application =
						new ApplicationModel(
								addView.getCompanyName(),
								addView.getJobTitle(),
								addView.getJobLocation(),
								pay,
								dateText,
								addView.getStatus(),
								addView.getNotes(),
								addView.getJobType()
						);

				repository.addApplication(application);

				// Validate that the application was added successfully before saving
				try
				{
					fileService.saveApplications(repository.getApplications());
				}
				catch (RuntimeException ex)
				{
					JOptionPane.showMessageDialog(
							addView,
							ex.getMessage(),
							"Save Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				refreshDashboard();
				addView.clearForm();
			});
		});
	}

	/**
	 * Purpose: Refresh the dashboard by updating the stats and recent applications
	 *
	 * @return void
	 */
	public void refreshDashboard()
	{
		dashboardView.updateTotalApplications(statesService.getTotalApplications());
		dashboardView.updateInterviews(statesService.getInterviewCount());
		dashboardView.updateOffers(statesService.getOfferCount());
		dashboardView.updateRejections(statesService.getRejectedCount());
		dashboardView.displayRecentApplications(repository.getApplications());
		dashboardView.updatePieChart(
				statesService.getAppliedCount(),
				statesService.getInterviewCount(),
				statesService.getOfferCount(),
				statesService.getRejectedCount());
	}
}
