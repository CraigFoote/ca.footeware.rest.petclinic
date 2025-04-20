package ca.footeware.rest.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.rest.petclinic.models.Species;

public interface SpeciesRepository extends MongoRepository<Species, String> {

}
