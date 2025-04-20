/**
 * 
 */
package ca.footeware.rest.petclinic.models;

import java.util.Objects;

import org.springframework.data.annotation.Id;

/**
 * A person who owns a {@link Pet}.
 */
public class Owner {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private Province province;
	private String postalCode;
	private String phone;
	private String email;

	/**
	 * Constructor.
	 * 
	 * @param firstName  {@link String}
	 * @param lastName   {@link String}
	 * @param address    {@link String}
	 * @param city       {@link String}
	 * @param province   {@link Province}
	 * @param postalCode {@link String}
	 * @param phone      {@link String}
	 * @param email      {@link String}
	 */
	public Owner(String firstName, String lastName, String address, String city, Province province, String postalCode,
			String phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.phone = phone;
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Owner other = (Owner) obj;
		return Objects.equals(id, other.id);
	}

}
