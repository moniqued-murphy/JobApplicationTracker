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

import views.DashboardView;
import repositories.ApplicationRepository;
import controllers.DashboardController;
import services.FileService;
import services.StatisticsService;

/**
 * Purpose: The responsibility of Main is to launch the application
 * 
 */
public class Main
{
	public static void main(String[] args)
	{
		ApplicationRepository repo = new ApplicationRepository();
		FileService fileService = new FileService("Applications.txt");
		StatisticsService statsService = new StatisticsService(repo);
		DashboardView dashboardView = new DashboardView();
		new DashboardController(dashboardView, repo, fileService, statsService);
	}
}
