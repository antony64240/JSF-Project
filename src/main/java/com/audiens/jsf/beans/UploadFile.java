package com.audiens.jsf.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.audiens.drive.io.DriveDAO;
import com.audiens.drive.io.DriveDAOImpl;
import com.audiens.drive.io.UserException;
import com.audiens.drive.model.User;

@ManagedBean(name = "UploadFile")
public class UploadFile {

	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(LoginBean.class);
	private Part uploadedFile; // +getter+setter

	public void upload() throws IOException {
		DriveDAO dao = new DriveDAOImpl();
		HttpSession session;
		session = new SessionUtils().getSession();

		User user = (User) session.getAttribute("user");
		String url = (String) session.getAttribute("currentUrl");
		String contentDisp = uploadedFile.getHeader("content-disposition");
		System.out.println("content-disposition header= " + contentDisp);
		String fileName = fileName(contentDisp);
		File savedFile = null;
		try {
			savedFile = new File(dao.getRacine(user) + url + '/' + fileName);
		} catch (UserException e1) {
			logger.error("Session perdu");
		}
		try (InputStream input = uploadedFile.getInputStream()) {
			Files.copy(input, savedFile.toPath());
		} catch (IOException e) {
			session.setAttribute("message", "Erreur lors de la récuparation du fichier.");
			logger.error(e);
		}

	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String fileName(String contentDisp) {
		String[] SplitUrl = contentDisp.split(";");
		String fileName = "";
		for (String Spliturl : SplitUrl) {
			if (Spliturl.trim().startsWith("filename")) {
				fileName = Spliturl.substring(Spliturl.indexOf("=") + 2, Spliturl.length() - 1);
				fileName = new File(fileName).getName();
			}
		}
		return fileName;
	}

}
