package test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.model.User;
import com.util.ReadCSV;

@RunWith(Parameterized.class)
public class TestLogin {

	private WebDriver driver;
	private String baseUrl;
	private String username;
	private String password;
	private String email;
	private User user = null;

	public TestLogin(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		
		System.out.println("TestLogin   run");
	}

	@Parameters
	public static Collection<Object[]> getData() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String path = "C:/Users/Administrator/Desktop/info.csv";
		list = ReadCSV.readFlie(path);
		Object[][] it = new Object[list.size()][3];
		int i = 0;
		for (String[] item : list) {
			it[i][0] = item[0];
			it[i][1] = item[0].substring(4);
			it[i][2] = item[1];
			i++;
		}
		return Arrays.asList(it);
		
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D:/firefox/firefox.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://www.ncfxy.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		user = new User(username, password, email);
	}
//	@Before
//	public void setUser() throws Exception {
//		System.setProperty("webdriver.firefox.bin", "D:/firefox/firefox.exe");
//		driver = new FirefoxDriver();
//		baseUrl = "http://www.ncfxy.com/";
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		user = new User(username, password, email);
//	}

	@Test
	public void testTestLogin() throws Exception {
		String xpath = ".//*[@id='table-main']/tr[1]/td[2]";
		driver.get(baseUrl);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(user.getUsername());
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys(user.getPassword());
		driver.findElement(By.id("submit")).click();
		WebElement table = driver.findElement(By.xpath(xpath)); 
		String text = table.getText();
		//System.out.println(text);
		assertEquals(user.getEmail(), text);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}
}
