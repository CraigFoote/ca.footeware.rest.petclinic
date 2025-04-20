/**
 * 
 */
package ca.footeware.rest.petclinic.models;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;

/**
 * An animal of a given {@link Species} that belongs to an {@link Owner}.
 */
public class Pet {

	@Id
	private String id;
	private String name;
	private String birthDate;
	private String speciesId;
	private String ownerId;
	private Gender gender;

	/**
	 * Constructor.
	 * 
	 * @param name      {@link String}
	 * @param birthDate {@link Date}
	 * @param speciesId {@link String}
	 * @param ownerId   {@link String}
	 * @param neutered  boolean
	 * @param gender    {@link Gender}
	 */
	public Pet(String name, String birthDate, String speciesId, String ownerId, Gender gender) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.speciesId = speciesId;
		this.ownerId = ownerId;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return Objects.equals(id, other.id);
	}
	
}
