package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base_Class.Baseclass;

public class Items_Upl_Page extends  Baseclass {
	
	@FindBy (id = "HelpHolder1") WebElement menu;
	@FindBy (id = "RoyaltyItemUpload") WebElement Itemup;
	@FindBy (id = "btnDownload") WebElement xldwn;
	@FindBy (id = "btnAddNew1") WebElement xlup;
	@FindBy (id = "fleUpload") WebElement upld;
	@FindBy (id = "Iframe2") WebElement ifrm;
	@FindBy (xpath = "//*[@id='diviFrameRightsRTExcel']/iframe") WebElement ifrm2;
	@FindBy (id = "btnUpload") WebElement upldbtn;
	
	
	//Iframe3
  public Items_Upl_Page (){
	  
	  PageFactory.initElements(driver, this);
  }
  
  public String isAlertpresent(){
	  
	  Alert alt = driver.switchTo().alert();
	  
	  alt.getText();
	  alt.accept();
	  	  
	  return alt.getText();
  }
  
  public void itemupload(String path) throws IOException{
	  
	  
	  
	//  menu.click();
	 // Itemup.click();
	  driver.switchTo().frame(ifrm);
	  //xldwn.click();
	  xlup.click();
	  driver.switchTo().frame(ifrm2);
	  upld.sendKeys(path);
	  upldbtn.click();
  }
  
  
}
