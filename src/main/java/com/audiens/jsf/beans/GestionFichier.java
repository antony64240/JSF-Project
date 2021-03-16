package com.audiens.jsf.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.audiens.drive.io.DriveDAO;
import com.audiens.drive.io.DriveDAOImpl;
import com.audiens.drive.io.UserException;
import com.audiens.drive.model.Dossier;
import com.audiens.drive.model.User;

@ManagedBean(name = "GestionFichier")
public class GestionFichier {

	private String newdossier;
	private static final Logger logger = LogManager.getLogger(LoginBean.class);

	public String getNewdossier() {
		return newdossier;
	}

	public void setNewdossier(String newdossier) {
		this.newdossier = newdossier;
	}

	public String AddDossier() {
		DriveDAO dao = new DriveDAOImpl();
		HttpSession session;
	
			session = new SessionUtils().getSession();
			String url = (String) session.getAttribute("currentUrl");
			User user = (User) session.getAttribute("user");
			try {
				dao.add(new Dossier(dao.getRacine(user) + url + '/' + newdossier));
			} catch (UserException e) {
				try {
					logger.error("Session expired");
					new SessionUtils().redirect("login.xhtml");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
			
			return"listedossiers";
		}
}
