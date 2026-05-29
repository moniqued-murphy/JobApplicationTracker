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
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import models.ApplicationModel;
import repositories.ApplicationRepository;

/**
 * Purpose: The responsibility of TestApplicationModel is ...
 * To test the functionality of the ApplicationModel class and its interaction with the ApplicationRepository.
 */
public class TestApplicationModel
 {

     @Test
     public void testAddApplications()
     {
         ApplicationRepository repo = new ApplicationRepository();
         
         ApplicationModel app = new ApplicationModel(
                 "Mesa College",
                 "Intern",
                 "San Diego, CA",
                 23.00,
                 "05/01/26",
                 "Applied",
                 "Summer Intership",
                 "Internship"
                 );
         
         repo.addApplication(app);
         
         assertEquals(1, repo.getApplications().size()); 
     }
     
     @Test
     public void testDeleteApplication()
     {
         ApplicationRepository repo = new ApplicationRepository();
         
         ApplicationModel app = new ApplicationModel(
				 "Apple",
				 "Intern",
				 "San Diego, CA",
				 25.00,
				 "05/01/26",
				 "Applied",
				 "Summer Intership",
				 "Internship"
				 );
         
         repo.addApplication(app);
		 repo.deleteApplication(app);
		 
		 assertEquals(0, repo.getApplications().size());
     }
     
     @Test
     public void testEditApplication()
     {
    	 ApplicationRepository repo = new ApplicationRepository();
    	 
    	 ApplicationModel oldApp = new ApplicationModel(
    			 				 "Google",
    			 				 "Software Engineer",
    			 				 "San Francisco, CA",
    			 				 120000.00,
    			 				 "05/01/26",
    			 				 "Applied",
    			 				 "Dream Job",
    			 				 "Job"
    			 				 );
    	 
    	 ApplicationModel newApp = new ApplicationModel(
    			 				 "Google",
    			 				 "Software Engineer",
    			 				 "San Francisco, CA",
    			 				 130000.00,
    			 				 "05/01/26",
    			 				 "Interview",
    			 				 "Dream Job",
    			 				 "Job"
    			 				 );
    	 
    	 repo.addApplication(oldApp);
    	 repo.editApplication(oldApp, newApp);
    	 
    	 assertEquals(1, repo.getApplications().size());
     }
     
     @Test
     public void testSearchApplications()
     {
    	 ApplicationRepository repo = new ApplicationRepository();
		 
		 ApplicationModel app1 = new ApplicationModel(
				 				 "Microsoft",
				 				 "Software Engineer",
				 				 "Redmond, WA",
				 				 110000.00,
				 				 "05/01/26",
				 				 "Applied",
				 				 "Great Company",
				 				 "Job"
				 				 );
		 
		 ApplicationModel app2 = new ApplicationModel(
				 				 "Microsoft",
				 				 "Data Scientist",
				 				 "Redmond, WA",
				 				 115000.00,
				 				 "05/01/26",
				 				 "Applied",
				 				 "Great Company",
				 				 "Job"
				 				 );
		 
		 repo.addApplication(app1);
		 repo.addApplication(app2);
		 
		 assertEquals(2, repo.searchApplications("Microsoft").size());
		 
     }
     
     @Test
     public void testGetApplications()
     {
    	 ApplicationRepository repo = new ApplicationRepository();
    	 
    	 ApplicationModel app = new ApplicationModel(
    			 				 "Amazon",
    			 				 "Software Engineer Intern",
    			 				 "Seattle, WA",
    			 				 25.00,
    			 				 "05/01/26",
    			 				 "Applied",
    			 				 "Great Company",
    			 				 "Internship"
    			 				 );
    	 
    	 repo.addApplication(app);
    	 repo.getApplications();
    	 
    	 assertEquals(1, repo.getApplications().size());
     }
 }