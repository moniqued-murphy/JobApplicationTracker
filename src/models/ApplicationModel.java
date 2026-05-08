/**
* Lead Author(s):
* @author Monique Murphy; 0005396987
* 
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Version: 2026-04-29
*/
package models;

/**
 * Purpose: The responsibility of ApplicationModel is ...
 * Store one job or internship application, including relevant information:
 * companyName, jobTitle, location, pay, dateApplied, status, notes, applicationType
 * 
 *
 * ApplicationModel is-a ...
 * ApplicationModel is ...
 */
public class ApplicationModel
{
	private String companyName;
	private String jobTitle;
	private String location; // City / State
	private double pay;
	private String dateApplied; // 00/00/00
	private String status; // 
	private String notes;
	private String applicationType; // Job / internship
	
	/**
	 * Constructor for ApplicationModel
	 * Purpose: Create a new application object with all required information
	 * 
	 * @param companyName the company name
	 * @param jobTitle the job title
	 * @param location the job location
	 * @param pay the pay or salary
	 * @param dateApplied the application date
	 * @param status the current application status
	 * @param notes additional notes
	 * @param applicationType the type of application
	 */
	public ApplicationModel(String companyName, String jobTitle, String location,
	                        double pay, String dateApplied, String status,
	                        String notes, String applicationType)
	{
	    this.companyName = companyName;
	    this.jobTitle = jobTitle;
	    this.location = location;
	    this.pay = pay; //00.00
	    this.dateApplied = dateApplied; // 00/00/00
	    this.status = status; // applied, interview, rejected
	    this.notes = notes;
	    this.applicationType = applicationType; // internship, job
	}
	
	public String getCompanyName()
	{
		return companyName;
	}
	
	public String getJobTitle()
	{
		return jobTitle;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public double getPay()
	{
		return pay;
	}
	
	public String getDateApplied()
	{
		return dateApplied;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public String getNotes()
	{
		return notes;
	}
	
	public String getApplicationType()
	{
		return applicationType;
	}
}
