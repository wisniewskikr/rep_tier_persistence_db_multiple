package pl.kwi.entities;

public class UserEntity {
	
	
	private Long id;
	private NameEntity nameEntity;
	private SurnameEntity surnameEntity;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public NameEntity getNameEntity() {
		return nameEntity;
	}
	public void setNameEntity(NameEntity nameEntity) {
		this.nameEntity = nameEntity;
	}
	
	public SurnameEntity getSurnameEntity() {
		return surnameEntity;
	}
	public void setSurnameEntity(SurnameEntity surnameEntity) {
		this.surnameEntity = surnameEntity;
	}
		

}
