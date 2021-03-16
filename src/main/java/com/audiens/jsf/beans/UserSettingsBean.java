package com.audiens.jsf.beans;


import java.io.IOException;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class UserSettingsBean {
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void redirectHome() {
		try {
			new SessionUtils().redirect("home.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changePassword() {
		
	}

}
