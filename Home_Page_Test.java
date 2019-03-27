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
import Pages.Royal_Page;

public class Home_Page_Test extends Baseclass {

	Log_In_Page sign_in;
	Home_Page hpage;
	Royal_Page royality;
	public ExtentReports extent;
	public ExtentTest extentTest;

	public Home_Page_Test() {

		super();
	}

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Suren windows");
		extent.addSystemInfo("User Name", "Surendhar tester");
		extent.addSystemInfo("Environment", "QA");
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);

		// FileUtils.
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		sign_in = new Log_In_Page();
		hpage = sign_in.Login_crt_Uname(prop.getProperty("User"), prop.getProperty("Pwrd"));

	}

	@Test (groups = {"functionalTest"} )
	public void Home_title_check() {
		extentTest = extent.startTest("Title Check");
		String H_title = hpage.Hometitlechk();
		Assert.assertEquals(H_title, "Eclipse Royalties Receivable Portal");
		System.out.println("Home Page Title ::" + H_title);

	}

	@Test(groups = {"functionalTest"} )
	public void User_Name_Check() {
		extentTest = extent.startTest("UserNameCheck");
		String user = hpage.usernamechk();
		Assert.assertEquals(user, "Admin");
		System.out.println("User Name Is ::" + user);

	}

	@Test(groups = {"Sample"} )
	public void menu_check() {
		extentTest = extent.startTest("Menucheck");

		royality = hpage.menusec();
	}

	@AfterMethod
	public void quite(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			// to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName());
			// to add error/exception in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

			String screenshotPath = Home_Page_Test.getScreenshot(driver, result.getName());
			// to add screenshot in extent report
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

			// extentTest.log(LogStatus.FAIL,
			// extentTest.addScreencast(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
			String screenshotPath = Royality_Page_Test.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS,extentTest.addScreenCapture(screenshotPath));
		}

		// ending test and ends the current test and prepare to create html report
		extent.endTest(extentTest);

		driver.quit();

	}
}
