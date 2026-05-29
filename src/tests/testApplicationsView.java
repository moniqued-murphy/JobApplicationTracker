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
package tests;

import controllers.ApplicationsController;
import repositories.ApplicationRepository;
import views.ApplicationsView;

/**
 * Purpose: The responsibility of testApplicationsView is ...
 * To test the ApplicationsView class by creating an instance of ApplicationsView,
 * and ApplicationRepository, and then ApplicationsController with the view and repository.
 * 
 */
public class testApplicationsView
{
	public static void main(String[] args)
	{
		ApplicationsView view = new ApplicationsView();
		
		ApplicationRepository repo = new ApplicationRepository();
		
		new ApplicationsController(view, repo);
	}
}
