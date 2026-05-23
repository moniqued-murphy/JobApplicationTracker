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
* Version: 2026-04-29
*/
package repositories;

import java.util.ArrayList;

import models.ApplicationModel;

/**
 * Purpose: The responsibility of ApplicationRepository is ...
 * Store a collection of job and internship applications, 
 * and provide methods to add, delete, and retrieve applications
 * 
 * ApplicationRepository is-a ...
 * ApplicationRepository is ...
 */
public class ApplicationRepository
{
	private ArrayList<ApplicationModel> applications;
	
	/**
	 * Constructor for ApplicationRepository
	 * Purpose: Initialize the applications collection
	 * 
	 * @return a new ApplicationRepository object with an empty applications collection
	 */
	public ApplicationRepository()
	{
		applications = new ArrayList<>();
	}
	
	/**
	 * Method: addApplications
	 * Purpose: Retrieve the collection of applications from the repository
	 * 
	 * @return an ArrayList of ApplicationModel objects representing the applications in the repository
	 */
	public void addApplication(ApplicationModel application)
	{
		applications.add(application);
	}
	
	/**
	 * Method: deleteApplication
	 * Purpose: Remove an application from the repository
	 * 
	 * @param application the ApplicationModel object to be removed from the repository
	 * @return void
	 */
	public void deleteApplication(ApplicationModel application)
	{
		applications.remove(application);
	}
	
	/**
	 * Method: editApplication
	 * Purpose: Edit an existing application in the repository
	 * 
	 * @param oldApplication the ApplicationModel object to be edited
	 * @return void
	 */
	public void editApplication(ApplicationModel oldApplication, ApplicationModel newApplication)
	{
		// Find the index of the old application in the applications collection
		int index = applications.indexOf(oldApplication);
		
		// If the old application is found, replace it with the new application
		if (index != -1)
		{
			applications.set(index, newApplication);
		}
	}
	
	/**
	 * Method: searchApplications
	 * Purpose: Search for applications in the repository based on a search term
	 * 
	 * @param keyWord the String to search for in the applications collection
	 * @return an ArrayList of ApplicationModel objects that match the search term
	 */
	public ArrayList<ApplicationModel> searchApplications(String keyWord)
	{
		// Create a new ArrayList to store the search results
		ArrayList<ApplicationModel> searchResults = new ArrayList<>();
		
		// Search through the applications collection 
		//and add any applications that match the search term to the search results
		for (int i = 0; i < applications.size(); i++)
		{
		    ApplicationModel application = applications.get(i);

		    if (application.getCompanyName().toLowerCase().contains(keyWord.toLowerCase()) ||
		        application.getJobTitle().toLowerCase().contains(keyWord.toLowerCase()) ||
		        application.getJobLocation().toLowerCase().contains(keyWord.toLowerCase()) ||
		        application.getStatus().toLowerCase().contains(keyWord.toLowerCase()) ||
		        application.getApplicationType().toLowerCase().contains(keyWord.toLowerCase()))
		    {
		        searchResults.add(application);
		    }
		}
		
		return searchResults;
	}
	
	/**
	 * Method: getApplications
	 * Purpose: Return the full list of saved applications
	 * 
	 * @return the applications ArrayList containing all the ApplicationModel objects in the repository
	 */
	public ArrayList<ApplicationModel> getApplications()
	{
		return applications;
	}
}
