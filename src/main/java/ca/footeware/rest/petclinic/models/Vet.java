/**
 * 
 */
package ca.footeware.rest.petclinic.models;

import java.util.Objects;

import org.springframework.data.annotation.Id;

/**
 * A veterinarian.
 */
public class Vet {

	@Id
	private String id;
	private String firstName;
	private String lastName;

	/**
	 * Constructor.
	 * 
	 * @param firstName {@link String}
	 * @param lastName {@link String}
	 */
	public Vet(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		Vet other = (Vet) obj;
		return Objects.equals(id, other.id);
	}

}
