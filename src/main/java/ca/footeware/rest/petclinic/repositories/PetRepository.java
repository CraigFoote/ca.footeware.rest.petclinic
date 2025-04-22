package ca.footeware.rest.petclinic.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.rest.petclinic.models.Pet;

public interface PetRepository extends MongoRepository<Pet, String> {

	List<Pet> findByNameContainingIgnoreCase(String searchterm);
	
}
