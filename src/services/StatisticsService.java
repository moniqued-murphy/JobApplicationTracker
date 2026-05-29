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
package services;

import java.util.ArrayList;
import models.ApplicationModel;
import repositories.ApplicationRepository;

/**
 * The responsibility of StatisticsService is ...
 * To add statistics functionality to the application, such as counting the number of 
 * applications with different statuses and providing that information to the dashboard.
 * 
 */
public class StatisticsService
{
	private ApplicationRepository repository;

	/**
	 * Constructor
	 * Purpose: Create an instance of StatisticsService
	 * 
	 * @param repository
	 */
	public StatisticsService(ApplicationRepository repository)
	{
		this.repository = repository;
	}

	/**
	 * Purpose: Get the total number of applications
	 * 
	 * @return the total number of applications
	 */
	public int getTotalApplications()
	{
		return repository.getApplications().size();
	}

	/**
	 * Purpose: Get the number of applications with status "Applied"
	 *
	 * @return the number of applications with status "Applied"
	 */
	public int getAppliedCount()
	{
		int count = 0;

		// Loop through the applications and count how many have status "Applied"
		for (int i = 0; i < repository.getApplications().size(); i++)
		{
			if (repository.getApplications().get(i).getStatus().equals("Applied"))
			{
				count++;
			}
		}

		return count;
	}

	/**
	 * Purpose: Get the number of applications with status "Interview"
	 *
	 * @return the number of applications with status "Interview"
	 */
	public int getInterviewCount()
	{
		int count = 0;

		// Loop through the applications and count how many have status "Interview"
		for (int i = 0; i < repository.getApplications().size(); i++)

		{
			if (repository.getApplications().get(i).getStatus()
					.equals("Interview"))
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * Purpose: Get the number of applications with status "Offer"
	 * 
	 * @return the number of applications with status "Offer"
	 */
	public int getOfferCount()
	{
		int count = 0;

		// Loop through the applications and count how many have status "Offer"
		for (int i = 0; i < repository.getApplications().size(); i++)
		{
			if (repository.getApplications().get(i).getStatus().equals("Offer"))
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * Purpose: Get the number of applications with status "Rejected"
	 * 
	 * @return the number of applications with status "Rejected"
	 */
	public int getRejectedCount()
	{
		int count = 0;

		// Loop through the applications and count how many have status "Rejected"
		for (int i = 0; i < repository.getApplications().size(); i++)
		{
			if (repository.getApplications().get(i).getStatus()
					.equals("Rejected"))
			{
				count++;
			}
		}
		return count;
	}

}
