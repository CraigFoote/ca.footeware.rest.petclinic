/**
 * 
 */
package ca.footeware.rest.petclinic.models;

import java.util.Objects;

import org.springframework.data.annotation.Id;

/**
 * A species of animal.
 */
public class Species {
	
	@Id
	private String id;
	private String name;
	private String description;

	/**
	 * Constructor.
	 * 
	 * @param name {@link String}
	 * @param description {@link String}
	 */
	public Species(String name, String description) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Species other = (Species) obj;
		return Objects.equals(description, other.description);
	}
 
}