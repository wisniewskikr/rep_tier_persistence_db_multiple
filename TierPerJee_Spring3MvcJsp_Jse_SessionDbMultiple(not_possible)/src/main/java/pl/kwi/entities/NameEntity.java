package pl.kwi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pl.kwi.db.spring.AbstractEntity;

@Entity
@Table(name="names")
public class NameEntity extends AbstractEntity{
	
	
	private static final long serialVersionUID = 1L;
	@Column(name = "name", unique = true)
	private String name;
	
	public NameEntity() {}
	
	public NameEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		

}
