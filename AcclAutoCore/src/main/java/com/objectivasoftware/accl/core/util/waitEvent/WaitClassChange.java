package com.objectivasoftware.accl.core.util.waitEvent;

import org.openqa.selenium.WebElement;

import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;

public class WaitClassChange implements UntilEvent {
	private WebElement element;
	private String classValue;

	public WaitClassChange(WebElement element, String classValue) {
		this.element = element;
		this.classValue = classValue;
	}

	@Override
	public boolean excute() {
		String classTemp = element.getAttribute("class");
		return classTemp.contains(classValue);
	}
}
