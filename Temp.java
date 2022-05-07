package testing;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Temp
{
	WebDriver driver;
	
	@BeforeClass
		public void setup() throws InterruptedException
		{
		System.setProperty("webdriver.chrome.driver", "D:/selenium/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://bytzsoft.in/FlyPalTemp/Login.aspx?ReturnUrl=%2fFlypalbytz%2f");	
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		driver.findElement(By.id("txtUserName")).sendKeys("AMAR");
		driver.findElement(By.id("txtPassword")).sendKeys("AMAR123");
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(30000);
		
		
		driver.switchTo().frame("FrameLeft");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[contains(text(),'Masters')]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//tbody/tr[4]/td[1]/ul[1]/li[1]/div[1]/ul[1]/li[2]/a[1]")).click();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("FrameCentre");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@id='btnAddTop']")).click();
		Thread.sleep(5000);
		}

	@Test(dataProvider="VendorData")
	public void VendorTest(String vname,String vcode,String vadd) throws InterruptedException
	{			
	WebElement Vendorname =driver.findElement(By.xpath("//input[@id='txtName']"));
	Vendorname.clear();
	Vendorname.sendKeys(vname);
	
	WebElement Vendorcode =driver.findElement(By.xpath("//input[@id='txtVendorCode']"));
	Vendorcode.clear();
	Vendorcode.sendKeys(vcode);
	
	WebElement VendorAdd =driver.findElement(By.xpath("//textarea[@id='txtAddress']"));
	VendorAdd.clear();
	VendorAdd.sendKeys(vadd);

}
@DataProvider(name="VendorData")
public String [][] getvendorData() throws IOException
{
						String path="D:\\selenium\\test1.xlsx";
						XLUtility xlutil=new XLUtility(path);
						
						int totalrows=xlutil.getRowCount("Sheet2");
						int totalcols=xlutil.getCellCount("Sheet2",1);	
								
						String VendorTest[][]=new String[totalrows][totalcols];
							
						
						for(int i=1;i<=totalrows;i++) //1
						{
							for(int j=0;j<totalcols;j++) //0
							{
								VendorTest[i-1][j]=xlutil.getCellData("Sheet2", i, j);
							}
								
						}
						return VendorTest;
}


	
}


