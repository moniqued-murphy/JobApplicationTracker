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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import repositories.ApplicationRepository;
import views.AddApplicationView;
import views.ApplicationsView;
import services.FileService;
import models.ApplicationModel;

/**
 * Purpose: The responsibility of ApplicationsController is ...
 * To manage the flow of data between the ApplicationsView and the ApplicationRepository
 *
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
		
		view.displayApplications(repo.getApplications());

		// Add button: open the add application form
		view.getAddButton().addActionListener(e ->
		{
			AddApplicationView addView = new AddApplicationView();

			addView.getSaveButton().addActionListener(saveEvent ->
			{
				addView.hidePayError();
				addView.hideDateError();

				String payText = addView.getPay();
				String dateText = addView.getDateApplied();

				boolean valid = true;
				double pay = 0;

				// Validate the pay input
				try
				{
					pay = Double.parseDouble(payText);
				}
				catch (NumberFormatException error)
				{
					addView.showPayError("Please enter a valid number (e.g. 5000.00).");
					valid = false;
				}

				// Validate the date input using a regular expression to check for MM/DD/YY format
				if (!dateText.matches("\\d{2}/\\d{2}/\\d{2}"))
				{
					addView.showDateError("Please use MM/DD/YY format (e.g. 05/21/26).");
					valid = false;
				}
				// If either input is invalid, do not proceed with saving the new application
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

				repo.addApplication(application);

				// Validate that the application was added successfully before saving
				try
				{
					fileService.saveApplications(repo.getApplications());
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

				// Refresh the applications table
				view.displayApplications(repo.getApplications());
				addView.clearForm();
			});
		});

		// Search button: search for applications that match the search text and display the results
		view.getSearchButton().addActionListener(e ->
		{
			String searchText = view.getSearchText();
			
			// If the search text is empty, display all applications
			if (searchText.isEmpty())
			{
				view.displayApplications(repo.getApplications());
				return;
			}
			
			// Search for applications that match the search text and display the results
			ArrayList<ApplicationModel> results = repo.searchApplications(searchText);
			// If no results found, display a message to the user
			view.displayApplications(results);
		});
		
		// Filter box: filter applications based on the selected filter and display the results
		view.getFilterBox().addActionListener(e ->
		{
			String selectedFilter = (String)view.getFilterBox().getSelectedItem();
			
			// If "All" is selected, display all applications
			if (selectedFilter.equals("All"))
			{
				view.displayApplications(repo.getApplications());
				return;
			}
			
			// Filter applications based on the selected filter and display the results
			ArrayList<ApplicationModel> filtered = new ArrayList<>();
			// Loop through all applications and add those that match the selected filter to the filtered list
			for (int i = 0; i < repo.getApplications().size(); i++)
			{
				ApplicationModel application = repo.getApplications().get(i);
				
				if (application.getStatus().equals(selectedFilter))
				{
					filtered.add(application);
				}
			}
			view.displayApplications(filtered);
		});
		
		// Delete button: delete the selected application from the repository and update the view
		view.getDeleteButton().addActionListener(e ->
		{
			int selectedIndex = view.getSelectedApplicationIndex();
			
			// Check if an application had been selected
			if (selectedIndex == -1)
			{
				JOptionPane.showMessageDialog(view, 
						"Please select an application to delete.",
						"No Selection",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// Get application thats selected
			ApplicationModel selected = repo.getApplications().get(selectedIndex);
			// Delete application from repository
			repo.deleteApplication(selected);
			// Save updated repository to file
			fileService.saveApplications(repo.getApplications());
			// Update view with new repository data
			view.displayApplications(repo.getApplications());
		});

		// Edit button: open the edit application form for the selected application
		view.getEditButton().addActionListener(e ->
		{
			int selectedIndex = view.getSelectedApplicationIndex();
			// Check if an application had been selected
			if (selectedIndex == -1)
			{
				JOptionPane.showMessageDialog(view,
						"Please select an application to edit.",
						"No Selection",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// Get application thats selected
			ApplicationModel oldApplication = repo.getApplications().get(selectedIndex);
			// Create a new edit view
			AddApplicationView editView = new AddApplicationView();
			// Populate the edit view with the data from the selected application
			editView.populate(oldApplication);

			// Add action listener to the save button in the edit view
			editView.getSaveButton().addActionListener(saveEvent ->
			{
				// Hide any previous error messages
				editView.hidePayError();
				editView.hideDateError();

				String payText = editView.getPay();
				String dateText = editView.getDateApplied();

				boolean valid = true;
				double pay = 0;

				// Validate the pay and date inputs
				try
				{
					pay = Double.parseDouble(payText);
				}
				catch (NumberFormatException error)
				{
					editView.showPayError("Please enter a valid number (e.g. 5000.00).");
					valid = false;
				}

				// Validate the date input using a regular expression to check for MM/DD/YY format
				if (!dateText.matches("\\d{2}/\\d{2}/\\d{2}"))
				{
					editView.showDateError("Please use MM/DD/YY format (e.g. 05/21/26).");
					valid = false;
				}

				// If either input is invalid, do not proceed with saving the edited application
				if (!valid)
				{
					return;
				}

				ApplicationModel updatedApplication =
						new ApplicationModel(
								editView.getCompanyName(),
								editView.getJobTitle(),
								editView.getJobLocation(),
								pay,
								dateText,
								editView.getStatus(),
								editView.getNotes(),
								editView.getJobType()
						);

				// Update the application in the repository with the new data from the edit view
				repo.editApplication(oldApplication, updatedApplication);

				// Save the updated repository to the file,
				// and handle any potential errors that may occur during the save process
				try
				{
					fileService.saveApplications(repo.getApplications());
				}
				catch (RuntimeException ex)
				{
					JOptionPane.showMessageDialog(
							editView,
							ex.getMessage(),
							"Save Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Update the main view with the new repository data and close the edit view
				view.displayApplications(repo.getApplications());
				editView.dispose();
			});
		});

		// Double click on a row in the table to open the edit view
		view.getApplicationsTable().addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// Check if the user double clicked
				if (e.getClickCount() == 2)
				{
					int selectedIndex = view.getSelectedApplicationIndex();

					// Make sure a row is actually selected
					if (selectedIndex == -1)
					{
						return;
					}

					// Get the selected application from the repository
					ApplicationModel oldApplication = repo.getApplications().get(selectedIndex);

					// Open the edit view and populate it with the selected application's data
					AddApplicationView editView = new AddApplicationView();
					editView.populate(oldApplication);

					// Add action listener to the save button in the edit view
					editView.getSaveButton().addActionListener(saveEvent ->
					{
						// Hide any previous error messages
						editView.hidePayError();
						editView.hideDateError();

						String payText = editView.getPay();
						String dateText = editView.getDateApplied();

						boolean valid = true;
						double pay = 0;

						// Validate the pay input
						try
						{
							pay = Double.parseDouble(payText);
						}
						catch (NumberFormatException error)
						{
							editView.showPayError("Please enter a valid number (e.g. 5000.00).");
							valid = false;
						}

						// Validate the date input using a regular expression to check for MM/DD/YY format
						if (!dateText.matches("\\d{2}/\\d{2}/\\d{2}"))
						{
							editView.showDateError("Please use MM/DD/YY format (e.g. 05/21/26).");
							valid = false;
						}

						// If either input is invalid, do not proceed with saving
						if (!valid)
						{
							return;
						}

						ApplicationModel updatedApplication =
								new ApplicationModel(
										editView.getCompanyName(),
										editView.getJobTitle(),
										editView.getJobLocation(),
										pay,
										dateText,
										editView.getStatus(),
										editView.getNotes(),
										editView.getJobType()
								);

						// Replace the old application with the updated one in the repository
						repo.editApplication(oldApplication, updatedApplication);

						// Save the updated repository to the file
						// and handle errors that may occur during the save process
						try
						{
							fileService.saveApplications(repo.getApplications());
						}
						catch (RuntimeException ex)
						{
							JOptionPane.showMessageDialog(
									editView,
									ex.getMessage(),
									"Save Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						// Update the main view with the new repository data and close the edit view
						view.displayApplications(repo.getApplications());
						editView.dispose();
					});
				}
			}
		});
	}
}
