package org.betterme.framework.helper.PageObject.homepage;

import org.apache.log4j.Logger;
import org.betterme.framework.helper.Logger.LoggerHelper;
import org.betterme.framework.helper.PageObject.PageBase;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}


	public WebDriver getDriver() {
		return this.driver;
	}
	


	



}
