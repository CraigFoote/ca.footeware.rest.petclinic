package ca.footeware.rest.petclinic.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.footeware.rest.petclinic.models.Vet;
import ca.footeware.rest.petclinic.repositories.VetRepository;

@Service
public class VetService {

	private VetRepository vetRepository;
	
	public VetService(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	public Vet save(Vet vet) {
		return vetRepository.save(vet);
	}

	public Iterable<Vet> findAll() {
		return vetRepository.findAll();
	}

	public Optional<Vet>  findById(String id) {
		return vetRepository.findById(id);
	}

	public void deleteById(String id) {
		vetRepository.deleteById(id);
	}
}
