package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base_Class.Baseclass;

public class Royal_Page extends Baseclass {
	
	@FindBy (className ="ui-jqgrid-title") WebElement Name;
	@FindBy(id="btnAddNew") WebElement CreateNew;
	@FindBy (id = "Iframe2") WebElement frame;
	
	
	public Royal_Page(){
		
		PageFactory.initElements(driver, this);
	}

	public String pagetitlechk(){
		
		driver.switchTo().frame(frame);
		return Name.getText();
		
	}
	public New_Contract addnew(){
		
		driver.switchTo().frame(frame);
		CreateNew.click();
		
		return new New_Contract();
		
	}
	
}
