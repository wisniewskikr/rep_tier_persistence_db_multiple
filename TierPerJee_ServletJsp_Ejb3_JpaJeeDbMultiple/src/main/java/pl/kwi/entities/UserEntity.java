package pl.kwi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import pl.kwi.db.jpa.AbstractEntity;

@Entity
@Table(name="users")
public class UserEntity extends AbstractEntity{
	
	
	private static final long serialVersionUID = 1L;
	@Column(name = "name_id")
	private Long nameId;
	@Column(name = "surname_id")
	private Long surnameId;
	
	@Transient
	private NameEntity nameEntity;
	@Transient
	private SurnameEntity surnameEntity;
		
	
	public Long getNameId() {
		return nameId;
	}
	public void setNameId(Long nameId) {
				
		this.nameId = nameId;
	}
	
	public Long getSurnameId() {
		return surnameId;
	}
	public void setSurnameId(Long surnameId) {
		this.surnameId = surnameId;
	}
	
	public NameEntity getNameEntity() {
		if(nameEntity == null){
			nameEntity = new NameEntity();
		}
		nameEntity.setId(nameId);
		
		return nameEntity;
	}
	public void setNameEntity(NameEntity nameEntity) {
		this.nameEntity = nameEntity;
		this.nameId = nameEntity.getId();
	}
	
	public SurnameEntity getSurnameEntity() {
		if(surnameEntity == null){
			surnameEntity = new SurnameEntity();
		}
		surnameEntity.setId(surnameId);
		
		return surnameEntity;
	}
	public void setSurnameEntity(SurnameEntity surnameEntity) {
		this.surnameEntity = surnameEntity;
		this.surnameId = surnameEntity.getId();
	}
		

}
