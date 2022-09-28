
package org.betterme.framework.helper.Button;

import org.apache.log4j.Logger;
import org.betterme.framework.helper.Logger.LoggerHelper;
import org.betterme.framework.helper.Navigation.IwebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ButtonHelper implements IwebComponent {
	
	private WebDriver driver;
	private Logger logger = LoggerHelper.getLogger(ButtonHelper.class);
	
	public ButtonHelper(WebDriver driver) {
		this.driver = driver;
		logger.debug("Button Helper : " + this.driver.hashCode());
	}
	

	public void click(WebElement element){
		element.click();
		logger.info(element);
	}
}
