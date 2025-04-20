/**
 * 
 */
package ca.footeware.rest.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.rest.petclinic.models.Owner;

/**
 * 
 */
public interface OwnerRepository extends MongoRepository<Owner, String> {

}
