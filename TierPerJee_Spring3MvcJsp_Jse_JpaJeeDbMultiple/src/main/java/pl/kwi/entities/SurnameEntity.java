package pl.kwi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pl.kwi.db.jpa.AbstractEntity;

@Entity
@Table(name="surnames")
public class SurnameEntity extends AbstractEntity{
	
	
	private static final long serialVersionUID = 1L;
	@Column(name = "surname", unique = true)
	private String surname;
	
	
	public SurnameEntity() {}
	
	public SurnameEntity(Long id, String surname) {
		this.id = id;
		this.surname = surname;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
		
}
