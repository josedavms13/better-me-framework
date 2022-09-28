package org.betterme.framework.helper.TextBox;

import org.apache.log4j.Logger;
import org.betterme.framework.helper.Generic.GenericHelper;
import org.betterme.framework.helper.Logger.LoggerHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class TextBoxHelper extends GenericHelper {

	private WebDriver driver;
	private Logger logger = LoggerHelper.getLogger(TextBoxHelper.class);

	public TextBoxHelper(WebDriver driver) {
		super(driver);
	}

	public void sendKeys(WebElement element,String value) {
		logger.info("Locator : " + element + " Value : " + value);
		element.sendKeys(value);
	}
	
	public void clear(WebElement element) {
		logger.info("Locator : " + element);
		element.clear();
	}
	
	public String getText(WebElement element) {
		logger.info("Locator : " + element);
		return element.getText();
	}
	
	public void clearAndSendKeys(WebElement element,String value) {
		element.clear();
		element.sendKeys(value);
		logger.info("Locator : " + element + " Value : " + value);
	}

}
