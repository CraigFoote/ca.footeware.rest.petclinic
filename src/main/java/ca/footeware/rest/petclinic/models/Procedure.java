/**
 * 
 */
package ca.footeware.rest.petclinic.models;

import java.util.Objects;

import org.springframework.data.annotation.Id;

/**
 * A medical procedure performed by a {@link Vet} on a {@link Pet}.
 */
public class Procedure {

	@Id
	private String id;
	private String name;
	private double cost;

	/**
	 * Constructor.
	 * 
	 * @param name {@link String}
	 * @param cost double
	 */
	public Procedure(String name, double cost) {
		super();
		this.name = name;
		this.cost = cost;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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
		Procedure other = (Procedure) obj;
		return Objects.equals(id, other.id);
	}

}
