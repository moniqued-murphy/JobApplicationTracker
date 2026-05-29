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
import services.FileService;

/**
 * Purpose: The responsibility of TestFileService is to test saving
 * and loading ApplicationModel objects to and from a file.
 *
 */
public class TestFileService
 {

     @Test
     public void testSaveApplications()
     {
         // Set up a file name and a FileService
         String fileName = "test_applications.txt";
         FileService fileService = new FileService(fileName);

         // Create one application to save
         ApplicationModel app = new ApplicationModel(
                 "Mesa College",
                 "Intern",
                 "San Diego, CA",
                 23.00,
                 "05/01/26",
                 "Applied",
                 "Summer Internship",
                 "Internship"
                 );

         // Put the application in a list
         java.util.ArrayList<ApplicationModel> appList = new java.util.ArrayList<>();
         appList.add(app);

         // Save the list to the file
         fileService.saveApplications(appList);

         // The file should now exist on disk
         assertEquals(true, new java.io.File(fileName).exists());

         // Delete the file after the test
         new java.io.File(fileName).delete();
     }

     @Test
     public void testLoadApplications()
     {
         // Set up a file name and a FileService
         String fileName = "test_applications.txt";
         FileService fileService = new FileService(fileName);

         // Arrange: create one application and save it to the file
         ApplicationModel app = new ApplicationModel(
                 "Mesa College",
                 "Intern",
                 "San Diego, CA",
                 23.00,
                 "05/01/26",
                 "Applied",
                 "Summer Internship",
                 "Internship"
                 );

         java.util.ArrayList<ApplicationModel> appList = new java.util.ArrayList<>();
         appList.add(app);
         fileService.saveApplications(appList);

         // Load the applications back from the file
         java.util.ArrayList<ApplicationModel> loaded = fileService.loadApplications();

         // The loaded list should have 1 entry with the correct company name
         assertEquals(1, loaded.size());
         assertEquals("Mesa College", loaded.get(0).getCompanyName());

         // Delete the file after the test
         new java.io.File(fileName).delete();
     }

     @Test
     public void testLoadApplicationsFileNotFound()
     {
         // Point to a file that does not exist
         String fileName = "nonexistent_file.txt";
         FileService fileService = new FileService(fileName);

         // Try to load from the missing file
         java.util.ArrayList<ApplicationModel> loaded = fileService.loadApplications();

         // The result should be an empty list 
         assertEquals(0, loaded.size());
     }

 }
