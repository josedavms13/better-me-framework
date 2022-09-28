package org.betterme.framework.helper.Generic;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.betterme.framework.helper.Logger.LoggerHelper;
import org.betterme.framework.helper.Navigation.IwebComponent;
import org.betterme.framework.utility.DateTimeHelper;
import org.betterme.framework.utility.ResourceHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class GenericHelper implements IwebComponent {

	private WebDriver driver;
	private Logger logger = LoggerHelper.getLogger(GenericHelper.class);

	public GenericHelper(WebDriver driver) {
		this.driver = driver;
		logger.debug("GenericHelper : " + this.driver.hashCode());
	}

	public WebElement getElement(By locator) {
		logger.info(locator);
		if (IsElementPresentQuick(locator))
			return driver.findElement(locator);
		
		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			logger.error(re);
			throw re;
		}
	}
	
	public WebElement getElementWithNull(By locator) {
		logger.info(locator);
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	public boolean IsElementPresentQuick(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		logger.info(flag);
		return flag;
	}

	public String takeScreenShot(String name) throws IOException {

		if (driver instanceof HtmlUnitDriver) {
			logger.fatal("HtmlUnitDriver Cannot take the ScreenShot");
			return "";
		}

		File destDir = new File(ResourceHelper.getResourcePath("screenshots/")
				+ DateTimeHelper.getCurrentDate());
		if (!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath()
				+ System.getProperty("file.separator") + name + ".jpg");
		try {
			FileUtils
					.copyFile(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			logger.error(e);
			throw e;
		}
		logger.info(destPath.getAbsolutePath());
		return destPath.getAbsolutePath();
	}

	public String takeScreenShot() {
		logger.info("");
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

}
