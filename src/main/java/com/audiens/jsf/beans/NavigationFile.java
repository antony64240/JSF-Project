package com.audiens.jsf.beans;

import java.util.Arrays;
import java.util.Vector;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean(name = "NavigationFile")
public class NavigationFile {

	private static final Logger logger = LogManager.getLogger(LoginBean.class);

	public String Submit(String url) {
		HttpSession session;
		session = new SessionUtils().getSession();
			String CurrentUrl = (String) session.getAttribute("currentUrl");
			session.setAttribute("currentUrl", CurrentUrl + "/" + url);
			return "listedossiers";
	}

	public String SubmitRetour() {
		HttpSession session;
		session = new SessionUtils().getSession();

		String url = (String) session.getAttribute("currentUrl");
		url = ConvertString(url);
		session.setAttribute("currentUrl", url);
		logger.debug(session.getAttribute("currentUrl"));
		return "listedossiers";
		
	}

	public String ConvertString(String url) {
		String[] a = url.split("/");
		int taille = a.length;
		Vector<String> abc = new Vector<>();
		;
		for (int i = 0; i < taille - 1; i++) {
			abc.add(a[i]);
		}
		String[] array = abc.toArray(new String[abc.size()]);
		String liste = Arrays.asList(array).toString();
		liste = liste.replaceAll(" ", "");
		liste = liste.replaceAll(",", "/");
		liste = liste.substring(0, liste.length() - 1);
		liste = liste.substring(1);
		return liste;
	}
}
