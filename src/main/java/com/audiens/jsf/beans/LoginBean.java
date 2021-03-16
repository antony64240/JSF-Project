package com.audiens.jsf.beans;

import java.io.IOException;
import java.util.UUID;

import javax.faces.bean.ManagedBean;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.audiens.drive.io.DriveDAO;
import com.audiens.drive.io.DriveDAOEmailException;
import com.audiens.drive.io.DriveDAOImpl;
import com.audiens.drive.model.User;

@ManagedBean
public class LoginBean {
	private String email = "";
	private String password = "";
	private String login = "";
	private String errorMessage = "";

	private static final Logger logger = LogManager.getLogger(LoginBean.class);

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void actionForm() {
		logger.debug(this.email + " "+ this.password);
		DriveDAO dao = new DriveDAOImpl();
		User u = dao.login(this.email, this.password);
		HttpSession session;
		session = new SessionUtils().getSession();
		session.setAttribute("user", u);
		if (u != null ) {
			try {
				session.setAttribute("currentUrl", "");
				new SessionUtils().redirect("listedossiers.xhtml");
			} catch (IOException e) {
				logger.error("erreur critique");
			}
			logger.debug("LoginBean actionForm() redirection effectuée");
		}
		logger.debug(u);

	}
	public String redirectRegister() {
		return "register";
	}

	public String redirectLogin() {
		return "login";
	}

	public void createUser() {
		logger.debug(this.email + this.password + this.login);
		UUID uuid = UUID.randomUUID();
		User user = new User(this.login, this.email, this.password, uuid.toString());
		DriveDAO dao = new DriveDAOImpl();
		boolean isCreate;
		try {
			isCreate = dao.createuser(user);
			logger.debug(isCreate);
		} catch (DriveDAOEmailException e) {
			logger.error(e);
		}

	}

}
