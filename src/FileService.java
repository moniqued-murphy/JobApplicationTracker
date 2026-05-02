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

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	
	public void saveApplications(ArrayList<ApplicationModel> appList)
	{
		try 
		{
			FileWriter writer = new FileWriter(fileName);
			
			// Loop through each application in the list
			for (int i = 0; i < appList.size(); i++)
			{
				ApplicationModel application = appList.get(i);
				
				// Write one application per line 
				writer.write(
						application.getCompanyName() + "," +
						application.getJobTitle() + "," +
						application.getLocation() + "," +
						application.getPay() + "," +
						application.getDateApplied() + "," +
						application.getStatus() + "," +
						application.getNotes() + "," +
						application.getApplicationType() + "\n"
						);
			}
			
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Error saving your application to file.");
		}
	}
}