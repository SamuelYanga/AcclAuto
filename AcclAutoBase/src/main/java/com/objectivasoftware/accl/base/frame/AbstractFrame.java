package com.objectivasoftware.accl.base.frame;

import com.objectivasoftware.accl.base.Configurations;
import com.objectivasoftware.accl.base.Constants;
import com.objectivasoftware.accl.base.browser.DriverFactory;
import com.objectivasoftware.accl.base.browser.MyDriver;
import com.objectivasoftware.accl.base.wait.MyElementLocatorFactory;
import com.objectivasoftware.accl.base.wait.WaitUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AbstractFrame {

	protected MyDriver myDriver;

	public final static String TARGET_URL = Configurations.getConfiguration(Constants.SELENIUM_TARGETURL);

	public AbstractFrame() {
		this.myDriver = DriverFactory.getBrowser();
		waitPageLoadIdentifier();
		PageFactory.initElements(new MyElementLocatorFactory(myDriver), this);
	}

	private void waitPageLoadIdentifier() {
		WaitUtil.waitOn(myDriver, 50000).untilPageDown();
		List<String> pageIdentitfierList = getLoadIdentifier();
		for (String pageIdentitfier : pageIdentitfierList) {
			WaitUtil.waitOn(this.myDriver).untilAdded(By.id(pageIdentitfier));
		}
	}

	/** 注入jQuery支持 */
	public void inJectJquery(JavascriptExecutor jsExecutor) {
		if (jQueryLoaded(myDriver)) {
			return;
		}

		jsExecutor.executeScript("var headID = document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');" + "newScript.type = 'text/Javascript';"
				+ "newScript.src=\"http://code.jquery.com/jquery-2.1.4.min.js\";" + "headID.appendChild(newScript);");
	}

	/** 判断当前页面是否使用了jQuery */
	public Boolean jQueryLoaded(JavascriptExecutor jsExecutor) {
		Boolean loaded = true;
		try {
			loaded = (Boolean) jsExecutor.executeScript("return jQuery()! = null");
		} catch (WebDriverException e) {
			loaded = false;
		}

		return loaded;
	}

	protected List<String> getLoadIdentifier() {
		return Collections.emptyList();
	}

	public void scrollMoveToElement(WebElement we) {
		scrollToTop();
		int y = we.getLocation().getY();
		String script = "return screen.availHeight;";
		Object obj = myDriver.executeScript(script);
		int screenAvailHeight = Integer.valueOf(obj.toString());
		int move = y - (screenAvailHeight / 3);

		if (move <= 0) {
			move = 1;
		}
		windowScrollToTop(move);
	}

	public void scrollToTop() {
		windowScrollToTop(1);
	}

	private void windowScrollToTop(int move) {
		inJectJquery(myDriver);
		String setscroll = "$(window).scrollTop(" + move + ");";
		myDriver.executeScript(setscroll);
	}

	public String currentHandle;

	public boolean switchToWindowByUrl(String windowUrl) {
		boolean flag = false;
		try {
			currentHandle = myDriver.getWindowHandle();
			Set<String> handles = myDriver.getWindowHandles();
			if (!handles.contains(currentHandle)) {
				currentHandle = handles.iterator().next();
				myDriver.switchTo().window(currentHandle);
			}
			for (String s : handles) {
				if (isCurrentUrl(windowUrl)) {
					flag = true;
					break;
				}

				if (s.equals(currentHandle)) {
					continue;
				} else {
					myDriver.switchTo().window(s);
					if (isCurrentUrl(windowUrl)) {
						flag = true;
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			flag = false;
		}
		WaitUtil.waitOn(myDriver).waitTime(500L);
		return flag;
	}

	private boolean isCurrentUrl(String windowUrl) {
		String currentUrl = myDriver.getCurrentUrl();
		if (currentUrl.contains("?")) {
			currentUrl = currentUrl.substring(0, currentUrl.indexOf("?"));
		}
		return currentUrl.contains(windowUrl);
	}

	public boolean closeCurrentWindow() {
		String currentUrl = myDriver.getCurrentUrl();
		currentUrl = currentUrl.substring(currentUrl.lastIndexOf("/"));
		return closeWindowByUrl(currentUrl);
	}

	public boolean closeWindowByUrl(String windowUrl) {
		if (switchToWindowByUrl(windowUrl)) {
			myDriver.close();
			Set<String> handles = myDriver.getWindowHandles();
			myDriver.switchTo().window(handles.iterator().next());
			return true;
		}
		return false;
	}

	public void switchToIframe(String framId) {
		if (framId == null) {
			myDriver.switchTo().defaultContent();
		} else {
			myDriver.switchTo().frame(framId);
		}
	}

	public void refreshPage() {
		myDriver.navigate().refresh();
	}
}
