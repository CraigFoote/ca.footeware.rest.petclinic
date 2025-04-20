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

import ca.footeware.rest.petclinic.models.Pet;
import ca.footeware.rest.petclinic.repositories.PetRepository;

/**
 * Provides RESTful endpoints for managing pets.
 */
@RestController
@RequestMapping("/pets")
public class PetController {

	private PetRepository petRepository;

	/**
	 * Constructor.
	 * 
	 * @param petRepository {@link PetRepository}
	 */
	public PetController(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
		Pet saved = petRepository.save(pet);
		return ResponseEntity.ok(saved);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Pet>> getAllPets() {
		Iterable<Pet> pets = petRepository.findAll();
		return ResponseEntity.ok(pets);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> getPet(@PathVariable String id) {
		return petRepository.findById(id).map(pet -> ResponseEntity.ok(pet)).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> updatePet(@PathVariable String id, @RequestBody Pet pet) {
		return petRepository.findById(id).map(existing -> {
			existing.setName(pet.getName());
			existing.setBirthDate(pet.getBirthDate());
			existing.setGender(pet.getGender());
			existing.setSpeciesId(pet.getSpeciesId());
			existing.setOwnerId(pet.getOwnerId());
			Pet saved = petRepository.save(existing);
			return ResponseEntity.ok(saved);
		}).orElse(ResponseEntity.notFound().build());
	}
}
