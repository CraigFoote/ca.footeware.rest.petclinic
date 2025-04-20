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

import ca.footeware.rest.petclinic.models.Species;
import ca.footeware.rest.petclinic.repositories.SpeciesRepository;

/**
 * Provides RESTful endpoints for managing all species.
 */
@RestController
@RequestMapping("/species")
public class SpeciesController {

	private SpeciesRepository speciesRepository;

	/**
	 * Constructor.
	 * 
	 * @param speciesRepository {@link speciesRepository}
	 */
	public SpeciesController(SpeciesRepository speciesRepository) {
		this.speciesRepository = speciesRepository;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Species> createSpecies(@RequestBody Species species) {
		Species saved = speciesRepository.save(species);
		return ResponseEntity.ok(saved);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Species>> getAllSpecies() {
		Iterable<Species> allSpecies = speciesRepository.findAll();
		return ResponseEntity.ok(allSpecies);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Species> getSpecies(@PathVariable String id) {
		return speciesRepository.findById(id).map(species -> ResponseEntity.ok(species))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Species> updateSpecies(@PathVariable String id, @RequestBody Species species) {
		return speciesRepository.findById(id).map(existingSpecies -> {
			existingSpecies.setName(species.getName());
			existingSpecies.setDescription(species.getDescription());
			Species saved = speciesRepository.save(existingSpecies);
			return ResponseEntity.ok(saved);
		}).orElse(ResponseEntity.notFound().build());
	}
}
