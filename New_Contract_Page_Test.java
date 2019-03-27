package all_Test_Cases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Base_Class.Baseclass;

import Pages.Home_Page;
import Pages.Log_In_Page;
import Pages.New_Contract;
import Pages.Royal_Page;
import Test_data.testutility_xl;

public class New_Contract_Page_Test extends Baseclass {

	Log_In_Page sign_in;
	Home_Page hpage;
	Royal_Page royality;
	New_Contract N_cntr;
	public ExtentReports extent;
	public ExtentTest extentTest;

	public New_Contract_Page_Test() {

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
	/* "FailedTestsScreenshots Stored file Location" */
		/*under src folder*/
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
		royality = hpage.menusec();
		N_cntr = royality.addnew();

	}

	@Test

	public void Contract_Page_title() {
		extentTest = extent.startTest("Page Title Check");
		String PageName = N_cntr.Title_chk();
		Assert.assertEquals(PageName, "Contract");
		System.out.println("Page Title ::" + " " + PageName);
	}

	@Test

	public void without_data_save() {
		extentTest = extent.startTest("Without data Entry");
		String alttxt = N_cntr.without_data();
		Assert.assertEquals(alttxt, "1px solid red");
	}
	
	@DataProvider

	 public Iterator<Object[]> exceldata() {

			ArrayList<Object[]> testdata = testutility_xl.getdata();

			System.out.println(testdata);

			return testdata.iterator();
		}


	@Test(dataProvider = "exceldata")

	public void with_data(String code, String desc, String mar, String frq, String week, String due, String amt_typ,
			String sdate, String per, String edate) throws InterruptedException {
		
		extentTest = extent.startTest("With data entry");
		
		N_cntr.New_contract(code,desc,mar,frq,week,due,amt_typ,sdate,per,edate);
	}

	@AfterMethod
	public void quite(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); 
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); 
			String screenshotPath = New_Contract_Page_Test.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); 
					
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
			String screenshotPath = Royality_Page_Test.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS,extentTest.addScreenCapture(screenshotPath));
		}

		extent.endTest(extentTest); // ending test and ends the current test and
									// prepare to create html report

		driver.quit();

	}
}
