/**
 * 
 */
package ca.footeware.rest.petclinic.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.footeware.rest.petclinic.models.Vet;
import ca.footeware.rest.petclinic.repositories.VetRepository;

/**
 * Provides RESTful endpoints for managing vets.
 */
@RestController
@RequestMapping("/vets")
public class VetController {

	private VetRepository vetRepository;

	/**
	 * Constructor.
	 * 
	 * @param vetRepository {@link VetRepository}
	 */
	public VetController(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vet> createVet(@RequestBody Vet vet) {
		Vet saved = vetRepository.save(vet);
		return ResponseEntity.ok(saved);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Vet>> getAllVets() {
		Iterable<Vet> vets = vetRepository.findAll();
		return ResponseEntity.ok(vets);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vet> getVet(@PathVariable String id) {
		return vetRepository.findById(id).map(vet -> ResponseEntity.ok(vet)).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vet> updateVet(@PathVariable String id, @RequestBody Vet vet) {
		return vetRepository.findById(id).map(existingVet -> {
			existingVet.setFirstName(vet.getFirstName());
			existingVet.setLastName(vet.getLastName());
			Vet saved = vetRepository.save(existingVet);
			return ResponseEntity.ok(saved);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> deleteVet(@PathVariable String id){
		vetRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatusCode.valueOf(200));
	}
}
