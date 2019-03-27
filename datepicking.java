package Config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class datepicking {
	
	
	public static void datejs(WebDriver driver,WebElement element,String val){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].setAttribute('value','"+val+"');",element);
		}	

}
