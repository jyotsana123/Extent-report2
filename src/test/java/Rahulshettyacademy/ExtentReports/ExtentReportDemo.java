package Rahulshettyacademy.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

//Section 21: Framework Part 5 -Extent HTML reports & TestNG Listeners & Thread Safe execution
//Video: 173. What are Extent reports? Quick 30 minutes Tutorial
//First we create extent report for single test
public class ExtentReportDemo {

	ExtentReports extent;
	@BeforeTest
	public void config() //In this method we prepare extent report configuration
	{
		//two classes are helpful to generate extent report - ExtentReports, ExptentSparkReporter class
		//ExptentSparkReporter class expect a path where your report should be created, This is responsible to creating report
		String path = System.getProperty("user.dir")+"\\reports\\index.html"; //Give path to create report inside the project
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Result");
		reporter.config().setReportName("Web Automation Results");
		//ExtentSparkReporter is a helper class which is helping to create some configuration and that will finally report to it's main class
		
		//ExtentReport class is main class which is responsible to create and consolidate all your test execution
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Jyotsana Pandey");
		
		//In framework if we have multiple test cases then we need to write all lines in every test methods, Which is not good approach, So we do it in optimized way
		//So I write these lines somewhere so all the test cases will commonly use that without messing up this code in the actual test
		//So for that there is a concept called TestNG listeners and that will awesomely handle this work.
		//Before that we need add extent report dependency in our project (PropFTXAdmin)
		//So first let me do one thing, for the report handling you need to set up metadata like creating extent spark reporter class object, setting report name, document title,
		//so for that under the resource in main java, let me create one new class to exclusively tell it as ExtentReporterNG. So inside this , Let's create one method which will create a report entry and give the object out of it - Get report object
		//Just copy paste these lines in ExtentReporterNG class (PropFTX/PropFTX.resources/ExtentReporterNG)
	}
	
	@Test
	public void initialDemo()
	{
		ExtentTest test = extent.createTest("initialDemo"); //here we say to ExtentReport class object to create one new test in the reporting file
		                                  //here our test case report is get attached to the main class ExtentReports object
		                                  //After writing this step, this extent variable keep on monitoring the result status of this test case
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		extent.flush(); //It will notified that test is done and it will no more start monitoring it.
		                //If you not write this then your report is still on listening mode that any test case remaining to execute and result will not generated
		
		//After executing your code, refresh your project to see the report folder of html report
		
		//when you create test "extent.createTest("initialDemo");" automatically one object is created for your complete test method, 
		  // so you can catch the object by writing   "ExtentTest test = extent.createTest("initialDemo");"
		  // so whenever you create test extent reoprt will create one object which is unique to your test method,
		  // so the object will be responsible to listen and report all the happenings back to the extent report.
		
	}
}
