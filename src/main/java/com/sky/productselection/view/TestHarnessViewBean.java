package com.sky.productselection.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Backing bean for test harness view.
 * @author Jonathan
 *
 */
@ManagedBean(name="testHarnessViewBean")
@ViewScoped
public class TestHarnessViewBean {
	
	/**
	 * The cookie value
	 */
	private String cookieValue;

	/**
	 * Sets the entered cookie value.
	 * @return the next page to render as a string.
	 */
	public String setCookie() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("maxAge", 86400);
		properties.put("path", "/"); 	
		FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("customerID", cookieValue, properties);
		return "test-harness";
	}

	/**
	 * @return the cookieValue
	 */
	public String getCookieValue() {
		return cookieValue;
	}

	/**
	 * @param cookieValue the cookieValue to set
	 */
	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}

}
