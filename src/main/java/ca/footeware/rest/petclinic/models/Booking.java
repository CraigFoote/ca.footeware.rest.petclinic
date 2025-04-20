/**
 * 
 */
package ca.footeware.rest.petclinic.models;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;

/**
 * An appointment for a {@link Pet} with a {@link Vet} to perform a {@link Procedure}.
 */
public class Booking {

	@Id
	private String id;
	private String petId;
	private String vetId;
	private Date date;
	private String procedureId;

	/**
	 * Constructor.
	 * 
	 * @param petId      {@link String}
	 * @param vetId      {@link String}
	 * @param date       {@link Date}
	 * @param procedureId {@link String}
	 */
	public Booking(String petId, String vetId, Date date, String procedureId) {
		this.petId = petId;
		this.vetId = vetId;
		this.date = date;
		this.procedureId = procedureId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

	public String getVetId() {
		return vetId;
	}

	public void setVetId(String vetId) {
		this.vetId = vetId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
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
		Booking other = (Booking) obj;
		return Objects.equals(id, other.id);
	}
	
}
