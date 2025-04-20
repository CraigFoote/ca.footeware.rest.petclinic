package ca.footeware.rest.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.rest.petclinic.models.Procedure;

public interface ProcedureRepository extends MongoRepository<Procedure, String> {

}
