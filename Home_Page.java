package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base_Class.Baseclass;

public class Home_Page extends Baseclass{
	
	
	@FindBy(id="HelpHolder1") WebElement menu;
	@ FindBy(id="UserMenuDiv")WebElement user_name; 
	@FindBy(id="Royalty") WebElement R_menu;
	@FindBy (id = "RoyaltyItemUpload") WebElement Itemup;
	
	public Home_Page(){
		
		PageFactory.initElements(driver, this);
	}
	
	
	public String Hometitlechk(){
		
		return driver.getTitle();
		
	}

	public String usernamechk(){
		menu.click();
		return user_name.getText();
		
			
	}
	
	public Royal_Page menusec(){
		
		menu.click();
		R_menu.click();
		
		System.out.println("We are right now clicked Royality menu");
		
		return new Royal_Page();
	}
	
	public Items_Upl_Page Item_Upld_Menu(){
		menu.click();
		Itemup.click();
		return new Items_Upl_Page();
	}

}
