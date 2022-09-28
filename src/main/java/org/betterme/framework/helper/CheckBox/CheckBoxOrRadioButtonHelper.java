package org.betterme.framework.helper.CheckBox;

import org.apache.log4j.Logger;
import org.betterme.framework.helper.Logger.LoggerHelper;
import org.betterme.framework.helper.Navigation.IwebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckBoxOrRadioButtonHelper implements IwebComponent {
	
	private WebDriver driver;
	private Logger logger = LoggerHelper.getLogger(CheckBoxOrRadioButtonHelper.class);

	public CheckBoxOrRadioButtonHelper(WebDriver driver) {
		this.driver = driver;
		logger.debug("CheckBoxOrRadioButtonHelper : " + this.driver.hashCode());
	}
	
	public void selectCheckBox(By locator) {
		logger.info(locator);
		selectCheckBox(driver.findElement(locator));
	}
	
	public void unSelectCheckBox(By locator) {
		logger.info(locator);
		unSelectCheckBox(driver.findElement(locator));
	}
	
	public boolean isIselected(By locator) {
		logger.info(locator);
		return isIselected(driver.findElement(locator));
	}
	
	public boolean isIselected(WebElement element) {
		boolean flag = element.isSelected();
		logger.info(flag);
		return flag;
	}
	
	public void selectCheckBox(WebElement element) {
		if(!isIselected(element))
			element.click();
		logger.info(element);
	}
	
	public void unSelectCheckBox(WebElement element) {
		if(isIselected(element))
			element.click();
		logger.info(element);
	}
}
