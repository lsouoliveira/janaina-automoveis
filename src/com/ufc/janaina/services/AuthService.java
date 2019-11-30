package com.ufc.janaina.services;

import com.ufc.janaina.jdbc.dao.UserDAO;
import com.ufc.janaina.models.Usuario;

/*
 * TODO
 * authenticate()
 */

public class AuthService {
	private static AuthService instance = null;
	
	private Auth auth;
	
	public static AuthService getInstance() {
		if(instance == null) {
			instance = new AuthService();
		}
		
		return instance;
	}
	
	public void authenticate(String username, String password) {
		UserDAO userDAO = new UserDAO();
		
		Usuario usuario = userDAO.getWithCredentials(username, password);
		
		if(usuario == null) {
			return;
		}
		
		auth = new Auth(usuario);
	}
	
	public Auth getAuth() {
		return auth;
	}
	
	public boolean isAuthenticated() {
		if(auth == null) {
			return false;
		}
		
		return true;
	}
	
	public void close() {
		auth = null;
	}
}
