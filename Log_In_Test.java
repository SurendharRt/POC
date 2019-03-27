package all_Test_Cases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Base_Class.Baseclass;
import Pages.Home_Page;
import Pages.Log_In_Page;

public class Log_In_Test extends Baseclass{
	
	Log_In_Page sign_in;
	Home_Page hpage;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	public Log_In_Test(){
		super();
		
	}
	
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Suren windows");
		extent.addSystemInfo("User Name", "Surendhar tester");
		extent.addSystemInfo("Environment", "QA");
	}
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		
		//FileUtils.
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
		
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	
	@BeforeMethod
	public void setup() {
		initialization();
		sign_in = new Log_In_Page();
		}
   @Test
   public void Title_Check(){
	   extentTest = extent.startTest("Title Check");
	   String title = sign_in.Title_chk();
	   Assert.assertEquals(title, "Eclipse Royalties Receivable Login");
	   System.out.println("Web Application Title ::" + title);
   }
   
   @Test (groups = {"functionalTest"} )
   public void Wrong_credential_check(){
	   
	   extentTest = extent.startTest("Wrong Password");	   
	   sign_in.login_wrong_uname(prop.getProperty("User1"),prop.getProperty("Pwrd"));
	   System.out.println("");
	   
	   String url = driver.getCurrentUrl();
	   Assert.assertEquals(url, "http://1.6.74.188/test/Login.aspx");
	   
   }
   @Test (groups = {"functionalTest"} )
   public void Correct_credential_Check(){
	   extentTest = extent.startTest("Correct Password");
	   hpage = sign_in.Login_crt_Uname(prop.getProperty("User"), prop.getProperty("Pwrd"));
	   
	   System.out.println("We are right now in Home Page");
   }
   
   @AfterMethod
	public void quite(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = Log_In_Test.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
			String screenshotPath = Log_In_Test.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS,extentTest.addScreenCapture(screenshotPath));
		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		
		driver.quit();
		
	}
}
