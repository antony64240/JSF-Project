package com.audiens.jsf.beans;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

public class SessionUtils{
	FacesContext facesContext;
	ExternalContext externalContext;
	HttpSession session;
	
	
	public SessionUtils(){
		this.facesContext = FacesContext.getCurrentInstance();
		this.externalContext = (ExternalContext) facesContext.getExternalContext();
		this.session = (HttpSession) externalContext.getSession(true);
	}
	
	public HttpSession getSession(){
		return session;
	}
	
	public void redirect(String url) throws IOException{
		externalContext.redirect(url);
	}

}