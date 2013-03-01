package pl.kwi.entities;

public class SurnameEntity {
	
	
	private Long id;
	private String surname;
	
	
	public SurnameEntity() {}
	
	public SurnameEntity(Long id, String surname) {
		this.id = id;
		this.surname = surname;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
		
}
