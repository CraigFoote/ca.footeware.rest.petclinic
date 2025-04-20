package ca.footeware.rest.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.rest.petclinic.models.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
