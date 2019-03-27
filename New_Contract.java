package Pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base_Class.Baseclass;
import Config.datepicking;

public class New_Contract extends Baseclass {

	@FindBy(id = "lblContractDesc")
	WebElement Page_title;
	@FindBy(id = "txtContractID")
	WebElement Code;
	@FindBy(id = "txtContractDesc")
	WebElement code_des;
	@FindBy(id = "txtCustomerNumber")
	WebElement Cnt_No;
	@FindBy(xpath = "//*[@class='ac_results']/ul")
	WebElement table;
	@FindBy(tagName = "li")
	WebElement choice;
	@FindBy(id = "selFrequency")
	WebElement Freqz;
	@FindBy(id = "selStartDay")
	WebElement day;
	@FindBy(id = "txtDueDays")
	WebElement wday;
	@FindBy(id = "selRoyaltyCalcType")
	WebElement Royal_cal_type;
	@FindBy(id = "txtStartDate")
	WebElement S_Date;
	@FindBy(xpath = "//*[@id='divSummary']/div/div[4]/div[2]/div[2]/img")
	WebElement E1_Date;
	@FindBy(xpath = "//input[@id='txtEndDate']")
	WebElement E_Date;
	@FindBy(id = "txtDefaultRate")
	WebElement Default_rate;
	@FindBy(id = "imgBtnSaveNew")
	WebElement SaveBtn;
	@FindBy(id = "Iframe2")
	WebElement frame;
	@FindBy(id = "imgBtnFinish")
	WebElement finish;
	@FindBy(xpath = "//*[text()='Commit']")
	WebElement comit;
	@FindBy(xpath = "//*[text()='Cancel']")
	WebElement Cncl;
	@FindBy(id = "frmInsUpd")
	WebElement frame1;
	@FindBy(id = "itemsNav")
	WebElement items;
	@FindBy(id = "tab ui-state-default ui-corner-top ui-tabs-selected ui-state-active ui-state-hover")
	WebElement it_upld;

	@FindBy(id = "WelcomeUser")
	WebElement log;
	@FindBy(xpath = "//*[@id='UserOptionsDiv']/div[4]/span")
	WebElement logout;

	// frame id=frmInsUpd
	// next page finish id=imgBtnFinish

	public New_Contract() {

		PageFactory.initElements(driver, this);
	}

	public String Title_chk() {

		driver.switchTo().frame(frame1);
		return Page_title.getText();
	}

	public String without_data() {

		driver.switchTo().frame(frame1);
		 SaveBtn.click();
		// Alert alt = driver.switchTo().alert();
	
		// alt.getText();
	
		// System.out.println("Alert value" + " " + alt);

		String txtclr = Default_rate.getCssValue("border");
		
		// if (txtclr.equals("border: 1px solid red")) {

		// System.out.println("Need to Enter Data ");
		// } else {
		// System.out.println("Changes not made");
		// }
		return txtclr;
	}

	public void New_contract(String code, String desc, String mar, String frq, String week, String due, String amt_typ,
			String sdate, String per, String edate) throws InterruptedException {

		driver.switchTo().frame(frame1);

		Code.sendKeys(code);
		code_des.sendKeys(desc);

		/* ---------Customer number Entry Part---------- */
		/*
		 * Actions actn = new Actions(driver); WebElement act1 = Cnt_No;
		 * actn.moveToElement(act1).moveToElement(table).doubleClick().build().
		 * perform(); //Cnt_No.sendKeys("ADAMPARK0001");
		 * 
		 * WebElement lt = table; List<WebElement> val =
		 * lt.findElements(By.xpath("//*[@class='ac_results']/ul"));
		 * 
		 * for (int i = 0; i < val.size(); i++) {
		 * 
		 * if (val.get(i).getText().equals(mar)) { val.get(i).click();
		 * 
		 * break; } }
		 * 
		 *///Thread.sleep(2000);
		 Cnt_No.clear();
		 Cnt_No.click();
		Cnt_No.sendKeys(mar);
		Actions actioni = new Actions(driver);
		//WebElement act1 = Cnt_No;
		actioni.moveToElement(driver.findElement(By.xpath("//*[text()='RK0001']"))).click().build().perform();
		
		
		Thread.sleep(2000);
		Select part = new Select(Freqz);

		List<WebElement> list = part.getOptions();

		for (int i = 0; i <= list.size(); i++) {
			if (list.get(i).getText().equals(frq)) {
				list.get(i).click();

				break;
			}

		}
		//Thread.sleep(2000);

		if (frq.equals("Weekly")) {

			Select days = new Select(day);
			List<WebElement> lts = days.getOptions();
			for (int j = 0; j <= lts.size(); j++) {
				if (lts.get(j).getText().equals(week)) {
					lts.get(j).click();

					break;
				}

			}

			wday.sendKeys(String.valueOf(due));
		}

		Select caltype = new Select(Royal_cal_type);

		List<WebElement> typ = caltype.getOptions();

		for (int i = 0; i <= typ.size(); i++) {
			if (typ.get(i).getText().equals(amt_typ)) {
				typ.get(i).click();

				break;
			}

		}
		//Thread.sleep(3000);

		datepicking.datejs(driver, S_Date, sdate);

		Default_rate.sendKeys(String.valueOf(per));

		Thread.sleep(1000);
		E_Date.clear();
		E_Date.click();

		// WebElement dateWidget =
		// driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr/td"));
		// List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		//
		// for (WebElement cell: columns){
		// //Select 13th Date
		// if (cell.getText().equals("19")){
		// cell.findElement(By.linkText("19")).click();
		// break;
		// }
		E_Date.sendKeys(edate);
		// E1_Date.sendKeys("19/02/2019");
		// datepicking.datejs(driver, E_Date, edate);

		Thread.sleep(1000);
		SaveBtn.click();
		Thread.sleep(1000);
		finish.click();

		// Alert alt = driver.switchTo().alert();
		//
		// alt.accept();

		comit.click();

		// driver.switchTo().frame(frame1);
		// items.click();
		// it_upld.click();

		driver.switchTo().defaultContent();

		Actions action = new Actions(driver);
		WebElement act = log;
		action.moveToElement(act).moveToElement(logout).click().build().perform();

	}

}
