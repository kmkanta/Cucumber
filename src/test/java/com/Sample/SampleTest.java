package com.Sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleTest {
	public WebDriver driver;

	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public void takePartialScreenShot(WebElement element) throws IOException {
		String screenShot = System.getProperty("user.dir") + "\\screenShot.png";
		File screen = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
		Point p = element.getLocation();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		BufferedImage img = ImageIO.read(screen);
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width, height);
		ImageIO.write(dest, "png", screen);
		FileUtils.copyFile(screen, new File(screenShot));
	}

	@Test
	public void loginOperation() throws IOException {
		WebElement uname = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.xpath("(//*[@type ='submit'])[1]"));
		uname.sendKeys("mani");
		password.sendKeys("password");
		takePartialScreenShot(loginButton);
		loginButton.click();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}
