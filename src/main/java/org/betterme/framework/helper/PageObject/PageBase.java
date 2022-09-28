package org.betterme.framework.helper.PageObject;

import static java.lang.String.format;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.betterme.framework.helper.Logger.LoggerHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class PageBase{
	
	private final Logger log = LoggerHelper.getLogger(PageBase.class);
	private WebDriver driver;

	public void waitForElement(WebElement element,int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotFoundException.class);
		wait.pollingEvery(250,TimeUnit.MILLISECONDS);
		wait.until(elementLocated(element));
	}
	
	private Function<WebDriver, Boolean> elementLocated(final WebElement element) {
		return new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				log.debug("Waiting for Element : " + element);
				return element.isDisplayed();
			}
		};
	}
	
	public PageBase(WebDriver driver) {
		if(driver == null)
			throw new IllegalArgumentException("Driver object is null");
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		this.driver = driver;
	}
	
	public boolean checkForTitle(String title){
		log.info(title);
		if(title == null || title.isEmpty())
			throw new IllegalArgumentException(title);
		return driver.getTitle().trim().contains(title);
	}

	protected void navigateTo(String url) {
		driver.navigate().to(url);
		log.info( format("Navigation to %s", url));
	}
}
