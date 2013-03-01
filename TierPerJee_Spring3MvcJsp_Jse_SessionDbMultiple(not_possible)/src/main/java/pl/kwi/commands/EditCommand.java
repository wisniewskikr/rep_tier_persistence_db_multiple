package pl.kwi.commands;

import java.io.Serializable;

public class EditCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String userSurname;
	private String submit;
	private Long id;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
		
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
}
