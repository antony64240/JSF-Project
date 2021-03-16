package com.audiens.jsf.beans;


import java.io.IOException;
import java.util.List;



import javax.faces.bean.ManagedBean;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.audiens.drive.io.DriveDAO;
import com.audiens.drive.io.DriveDAOImpl;
import com.audiens.drive.io.UserException;
import com.audiens.drive.model.Dossier;
import com.audiens.drive.model.Fichier;
import com.audiens.drive.model.User;

@ManagedBean(name = "dossier")
public class DossierBean {

	

	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(LoginBean.class);
	private Part uploadedFile; // +getter+setter


	public List<Fichier> getliste() {
		DriveDAO dao = new DriveDAOImpl();
		HttpSession session;
		Dossier dossier = null;
		session = new SessionUtils().getSession();
		User user = (User) session.getAttribute("user");
		String url = (String) session.getAttribute("currentUrl");
		try {
			dossier = new Dossier(dao.getRacine(user) + url);
			return dao.liste(dossier);
		} catch (Exception e) {
			try {
				logger.error(e.getMessage());
				new SessionUtils().redirect("login.xhtml");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
		
	}


	public Part getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
