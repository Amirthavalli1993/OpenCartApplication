package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent;//common information of the report
	public ExtentTest tests; //test case status and screenshots 
	
	String repName;
	
	public void onStart(ITestContext context)
	{
		//report name with timestamp
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";		
		sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName);
		
		//UI of the report
		sparkReporter.config().setDocumentTitle("OpenCart Application Testing"); //title of report
		sparkReporter.config().setReportName("Functional Testing"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		//common information of the report
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser Name", browser);
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("OS", os);
		
		List<String> allgroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!allgroups.isEmpty()) 
		{
			extent.setSystemInfo("Groups", allgroups.toString());
		}
	}
	
	  public void onTestSuccess(ITestResult result) //Invoked each time a test succeeds.
	  {
		  tests=extent.createTest(result.getTestClass().getName());//create new entry in the report and add Class name
		  tests.assignCategory(result.getMethod().getGroups()); //to display groups
		  tests.log(Status.PASS, "Test PASSED: "  +result.getName()); 
		  
	  }
	  
	  public void onTestFailure(ITestResult result) //Invoked each time a test fails.
	  {
		  tests=extent.createTest(result.getTestClass().getName());//create new entry in the report
		  tests.assignCategory(result.getMethod().getGroups()); //to display groups
		  tests.log(Status.FAIL, "Test FAILED: "  +result.getName()); 
		  tests.log(Status.INFO, result.getThrowable().getMessage()); 
		  //capture screenshot
		  try
		  {
		  String imgpath=new BaseClass().captureScreenshot(result.getName());
		  tests.addScreenCaptureFromPath(imgpath);
		  
		  //email
		 /* URL url=new URL("file://"+System.getProperty("user.dir")+"\\reports\\"+repName);		  
		  ImageHtmlEmail email=new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googlemail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("amirthu.r@gmail.com","password"));
		  email.setSSLOnConnect(true);
		  email.setFrom("amirthu.r@gmail.com"); //sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find the report attached...");
		  email.addTo("amirthavalli1993@gmail.com"); //receiver
		  email.attach(url, "extent report", "Please check report");
		  email.send(); //send email */
		  }
		  
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  
		  
		  
	  }
	  
	  public void onTestSkipped(ITestResult result) //Invoked each time a test is skipped.
	  {
		  tests=extent.createTest(result.getName());//create new entry in the report
		  tests.assignCategory(result.getMethod().getGroups()); //to display groups
		  tests.log(Status.SKIP, "Test Skipped: "  +result.getName()); 
		  tests.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	
	
	public void onFinish(ITestContext context)
	{
		extent.flush();  //writer test information to output view
		
		//open reports on browser automatically once tests are completed. 
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

	

