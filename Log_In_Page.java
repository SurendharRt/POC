package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base_Class.Baseclass;

public class Log_In_Page extends Baseclass {
	
	
	@FindBy(id="Welcome") WebElement Title;
	@FindBy(id="txtUsername") WebElement UName;
	@FindBy(id="txtPassword") WebElement Pword;
	@FindBy (id ="Login") WebElement Signin;
	
	
	public Log_In_Page(){
		
		PageFactory.initElements(driver, this);
		
	}
	
	public String Title_chk(){
		
		return driver.getTitle();
	}
	
	public String login_wrong_uname(String Uname, String pword){
		
	UName.sendKeys(Uname);
	Pword.sendKeys(pword);
	Signin.click();
	return driver.getCurrentUrl();
		
	}
	public Home_Page Login_crt_Uname(String un,String pwd){
		
		UName.sendKeys(un);
		Pword.sendKeys(pwd);
		Signin.click();
		
		System.out.println("We are Successfully reached home page");
		
		return new Home_Page();
	}

	}
