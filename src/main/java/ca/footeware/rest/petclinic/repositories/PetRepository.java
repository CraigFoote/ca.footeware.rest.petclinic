package ca.footeware.rest.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.rest.petclinic.models.Pet;

public interface PetRepository extends MongoRepository<Pet, String> {

}
