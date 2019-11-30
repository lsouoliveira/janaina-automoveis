package com.ufc.janaina.services;

import com.ufc.janaina.models.Usuario;

public class Auth {
	private Usuario user;

	public Auth(Usuario user) {
		this.user = user;
	}
	
	public Usuario getUser() {
		return user;
	}
}
