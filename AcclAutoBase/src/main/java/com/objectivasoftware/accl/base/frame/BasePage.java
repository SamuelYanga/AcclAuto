package com.objectivasoftware.accl.base.frame;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BasePage extends AbstractFrame {
	
	public static String handle;
	
	public String getTitle() {
		return this.myDriver.getTitle();
	}

	public WebElement findElement(By by) {
		return this.myDriver.findElement(by);
	}

	public List<WebElement> findElements(By by) {
		return this.myDriver.findElements(by);
	}

	public String getCurrentUrl() {
		return this.myDriver.getCurrentUrl();
	}

}
