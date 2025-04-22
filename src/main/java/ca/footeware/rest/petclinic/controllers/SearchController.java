/**
 * 
 */
package ca.footeware.rest.petclinic.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.footeware.rest.petclinic.models.Booking;
import ca.footeware.rest.petclinic.models.Pet;
import ca.footeware.rest.petclinic.repositories.BookingRepository;
import ca.footeware.rest.petclinic.repositories.PetRepository;

/**
 * 
 */
@RestController
@RequestMapping("/search")
public class SearchController {

	private PetRepository petRepository;
	private BookingRepository bookingRepository;

	public SearchController(PetRepository petRepository, BookingRepository bookingRepository) {
		this.petRepository = petRepository;
		this.bookingRepository = bookingRepository;
	}

	@GetMapping("/{search}")
	public ResponseEntity<List<List<?>>> search(@PathVariable String search) {
		List<Pet> pets = petRepository.findByNameContainingIgnoreCase(search);

		List<Booking> allBookings = bookingRepository.findAll();
		List<Booking> bookings = new ArrayList<>();
		for (Booking booking : allBookings) {
			String petId = booking.getPetId();
			Optional<Pet> pet = petRepository.findById(petId);
			if (pet.isPresent() && pet.get().getName().toLowerCase().contains(search.toLowerCase())) {
				bookings.add(booking);
			}
		}

		List<List<?>> results = new ArrayList<>();
		results.add(pets);
		results.add(bookings);

		return ResponseEntity.ok(results);
	}
}
