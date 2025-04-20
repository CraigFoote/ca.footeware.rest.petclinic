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

import ca.footeware.rest.petclinic.models.Procedure;
import ca.footeware.rest.petclinic.repositories.ProcedureRepository;

/**
 * Provides RESTful endpoints for managing procedures.
 */
@RestController
@RequestMapping("/procedures")
public class ProcedureController {

	private ProcedureRepository procedureRepository;

	/**
	 * Constructor.
	 * 
	 * @param procedureRepository {@link ProcedureRepository}
	 */
	public ProcedureController(ProcedureRepository procedureRepository) {
		this.procedureRepository = procedureRepository;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Procedure> createProcedure(@RequestBody Procedure procedure) {
		Procedure saved = procedureRepository.save(procedure);
		return ResponseEntity.ok(saved);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Procedure>> getAllProcedures() {
		Iterable<Procedure> procedures = procedureRepository.findAll();
		return ResponseEntity.ok(procedures);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Procedure> getProcedure(@PathVariable String id) {
		return procedureRepository.findById(id).map(procedure -> ResponseEntity.ok(procedure))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Procedure> updateProcedure(@PathVariable String id, @RequestBody Procedure procedure) {
		return procedureRepository.findById(id).map(existing -> {
			existing.setName(procedure.getName());
			existing.setCost(procedure.getCost());
			Procedure saved = procedureRepository.save(existing);
			return ResponseEntity.ok(saved);
		}).orElse(ResponseEntity.notFound().build());
	}
}
