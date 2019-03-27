package Base_Class;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Baseclass {
	
	 public static WebDriver driver;
	 public static Properties prop;
	
	public Baseclass()  {
		
		 FileInputStream file;
		try {
			file = new FileInputStream("C:\\Users\\suthanthira_devi\\workspace\\POC\\src\\main\\"
					+ "java\\Config\\config.properties");
		
			prop = new Properties();
			 prop.load(file);
			} catch (FileNotFoundException e) { 
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}
		
		public static void initialization() {	
			
			String Broname = prop.getProperty("browser");
			
			if (Broname.equals("chrome")) {
				
				 System.setProperty("webdriver.chrome.driver", "D:\\Chrome\\chromedriver.exe");
				 driver = new ChromeDriver();
			}else if(Broname.equals("firefox")) {
				
				 System.setProperty("webdriver.gecko.driver", "D:\\firefox\\geckodriver.exe");
				 driver = new FirefoxDriver();
			}
		 		
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		 driver.manage().deleteAllCookies();
		 driver.get(prop.getProperty("Url"));
		
		}
		
		
	}
