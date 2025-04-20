/**
 * 
 */
package ca.footeware.rest.petclinic.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.footeware.rest.petclinic.models.Owner;
import ca.footeware.rest.petclinic.repositories.OwnerRepository;

/**
 * Provides RESTful endpoints for managing owners.
 */
@RestController
@RequestMapping("/owners")
public class OwnerController {

	private OwnerRepository ownerRepository;
	
	/**
	 * Constructor
	 * @param ownerRepository {@link OwnerRepository}
	 */
	public OwnerController(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}
	
	
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
		Owner saved = ownerRepository.save(owner);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Owner>> getAllOwners() {
		Iterable<Owner> owners = ownerRepository.findAll();
		return ResponseEntity.ok(owners);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Owner> getOwner(@PathVariable String id) {
		return ownerRepository.findById(id)
				.map(owner -> ResponseEntity.ok(owner))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Owner> updateOwner(@PathVariable String id, @RequestBody Owner owner) {
		return ownerRepository.findById(id)
				.map(existingOwner -> {
					existingOwner.setFirstName(owner.getFirstName());
					existingOwner.setLastName(owner.getLastName());
					existingOwner.setAddress(owner.getAddress());
					existingOwner.setCity(owner.getCity());
					existingOwner.setProvince(owner.getProvince());
					existingOwner.setPostalCode(owner.getPostalCode());
					existingOwner.setPhone(owner.getPhone());
					existingOwner.setEmail(owner.getEmail());
					Owner saved = ownerRepository.save(existingOwner);
					return ResponseEntity.ok(saved);
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
