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

package services;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import models.ApplicationModel;
/**
 * Purpose: The responsibility of FileService is ...
 * Save and load application data to and from a text file
 *
 * FileService is-a ...
 * FileService is ...
 */
public class FileService
{
	private String fileName;
	
	/**
	 * Constructor for FileService
	 * Purpose: Initialize the fileName for saving and loading application data
	 * 
	 * @param fileName the name of the file to save and load application data
	 */
	public FileService(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * Method: saveApplications
	 * Purpose: Save the collection of applications to a text file
	 * 
	 * @param appList an ArrayList of ApplicationModel objects representing the applications to be saved to the file
	 * @return void
	 */
	public void saveApplications(ArrayList<ApplicationModel> appList)
	{
		try 
		{
			FileWriter writer = new FileWriter(fileName);
			
			// Loop through each part of the application in the list
			for (int i = 0; i < appList.size(); i++)
			{
				ApplicationModel application = appList.get(i);
				
				// write each part of the application 
				writer.write(
						application.getCompanyName() + "|" +
						application.getJobTitle() + "|" +
						application.getJobLocation() + "|" + 
						application.getPay() + "|" +
						application.getDateApplied() + "|" +
						application.getStatus() + "|" +
						application.getNotes() + "|" +
						application.getApplicationType() + "\n"
						);
			}
			
			writer.close();
		}
		catch (IOException e)
		{
			throw new RuntimeException("Could not save your application.");
		}
	}
	
	/**
	 * Method: loadApplications
	 * Purpose: Load the collection of applications from a text file
	 * 
	 * @param appList an ArrayList of ApplicationModel objects representing the applications to be loaded from the file
	 * @return void
	 */
	public ArrayList<ApplicationModel> loadApplications()
	{
		ArrayList<ApplicationModel> applications = new ArrayList<>();
		
		try
		{
			FileReader reader = new FileReader(fileName);
			Scanner scanner = new Scanner(reader);
			
			// Read each line of the fine 
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				
				// Split the line using commas
				String[] data = line.split("\\|");
				
				// new ApplicationMdel object
				ApplicationModel application = new ApplicationModel(
						data[0], // companyName
						data[1], // jobTitle
						data[2], // location
						Double.parseDouble(data[3]), // pay 
						data[4], // dateApplied
						data[5], // status
						data[6], // notes
						data[7]  // applicationType
						);
				
				// Add the application to the list of applications
				applications.add(application);
			}
			
			scanner.close();
		}
		
		catch (IOException e)
		{
			System.out.println("Error loading your application from file.");
		}
		
		return applications;
	}
	
}