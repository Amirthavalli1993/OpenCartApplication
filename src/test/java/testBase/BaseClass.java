package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public static WebDriver driver;
	public Logger log;
	public Properties property;
	
	@BeforeClass(groups={"sanity","regression","master"})
	@Parameters({"browser", "os"}) //Corresponding value will be retrieved from testng.xml file
	public void setup(String br, String os) throws IOException
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		property=new Properties();
		property.load(file); //load property file
		
		//loading log files for active classes
		log=LogManager.getLogger(this.getClass()); //this will refer the current class file
		
		if(property.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//choose OS
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No OS matching"); return;
			}
			
			
			//choose browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("Invalid Browser"); return;			
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities); //since RemoteWebDriver is parent of all the different browsers
		}
		switch(br.toLowerCase())
		{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			default: System.out.println("Invalid Browser Name"); return; //return will exit from entire execution
		}		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(property.getProperty("Appurl"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups={"sanity","regression","master"})
	public void tearDown()
	{
		driver.close();
	}
	
	
	public String randomString()
	{
		String randomString=RandomStringUtils.randomAlphabetic(10);
		return randomString;
	}
	
	public String randomNumber()
	{
		String randomNumber=RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}
	
	public String randomStringNumber()
	{
		String randomString=RandomStringUtils.randomAlphabetic(5);
		String randomNumber=RandomStringUtils.randomNumeric(5);
		return (randomString+"#"+randomNumber);
	}
	
	public String captureScreenshot(String tname)
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourcepath=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetpath=".\\screenshots\\"+tname+"_"+timeStamp+".png";
		File target=new File(targetpath);
		
		sourcepath.renameTo(target);
		
		return targetpath;
	}
}
