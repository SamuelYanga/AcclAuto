package com.objectivasoftware.accl.base.frame;

public abstract class BaseComponent extends AbstractFrame {
	public String getCurrentUrl() {
		return this.myDriver.getCurrentUrl();
	}
}
