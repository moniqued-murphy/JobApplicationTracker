/**
* Lead Author(s):
* @author Monique Murphy; 0005396987
* 
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Version: 2026-05-28
*/
package models;

/**
 * Purpose: The responsibility of ApplicationModel is ...
 * Store one application, including relevant information:
 * companyName, jobTitle, location, pay, dateApplied, status, notes, applicationType
 * 
 */
public class ApplicationModel
{
	private String companyName;
	private String jobTitle;
	private String jobLocation; 
	private double pay; // 00.00
	private String dateApplied; // 00/00/00
	private String status; // applied, interview, rejected
	private String notes;
	private String applicationType; // Job-Full Time, Job-Part Time, Internship-Full Time, Internship-Part Time
	
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
	    this.jobLocation = location;
	    this.pay = pay; 
	    this.dateApplied = dateApplied; 
	    this.status = status; 
	    this.notes = notes;
	    this.applicationType = applicationType; 
	}
	
	// Getters for all fields
	public String getCompanyName()
	{
		return companyName;
	}
	
	public String getJobTitle()
	{
		return jobTitle;
	}
	
	public String getJobLocation()
	{
		return jobLocation;
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
	
	/**
	 * Method: To String
	 * Purpose: Return a string representation of the application object
	 * 
	 * @return a string representation of the application object
	 */
	@Override
	public String toString()
	{
		return "Company: " + companyName + 
				", Job Title: " + jobTitle +
				", Location: " + jobLocation +
				". Pay: " + pay +
				", Date Applied: " + dateApplied +
				", Status: " + status +
				", Type: " + applicationType;
	}
}
