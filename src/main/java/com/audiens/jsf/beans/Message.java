package com.audiens.jsf.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Message", eager = true)
@RequestScoped
public class Message {
	
	private String message = "";
	
	public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}