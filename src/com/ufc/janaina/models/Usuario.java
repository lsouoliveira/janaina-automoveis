package com.ufc.janaina.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	public enum AdminLevel {VENDEDOR, GERENTE};
	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	@Enumerated(EnumType.ORDINAL)
	private AdminLevel adminLevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AdminLevel getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(AdminLevel adminLevel) {
		this.adminLevel = adminLevel;
	}
}
