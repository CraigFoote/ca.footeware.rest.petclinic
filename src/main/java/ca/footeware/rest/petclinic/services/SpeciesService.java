package ca.footeware.rest.petclinic.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.footeware.rest.petclinic.models.Species;
import ca.footeware.rest.petclinic.repositories.SpeciesRepository;

@Service
public class SpeciesService {

	private SpeciesRepository speciesRepository;
	
	/**
	 * Constructor.
	 * 
	 * @param speciesRepository {@link speciesRepository}
	 */
	public SpeciesService(SpeciesRepository speciesRepository) {
		this.speciesRepository = speciesRepository;
	}

	public Species save(Species species) {
		return speciesRepository.save(species);
	}

	public Iterable<Species> findAll() {
		return speciesRepository.findAll();
	}

	public Optional<Species> findById(String id) {
		return speciesRepository.findById(id);
	}
}
