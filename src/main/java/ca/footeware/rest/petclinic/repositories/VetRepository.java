package ca.footeware.rest.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.rest.petclinic.models.Vet;

public interface VetRepository extends MongoRepository<Vet, String> {

}
