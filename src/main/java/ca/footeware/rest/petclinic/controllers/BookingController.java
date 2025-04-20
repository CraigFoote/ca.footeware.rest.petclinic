/**
 * 
 */
package ca.footeware.rest.petclinic.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.footeware.rest.petclinic.models.Booking;
import ca.footeware.rest.petclinic.repositories.BookingRepository;

/**
 * Provides RESTful endpoints for managing bookings.
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

	private BookingRepository bookingRepository;

	/**
	 * Constructor.
	 * 
	 * @param bookingRepository {@link BookingRepository}
	 */
	public BookingController(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		Booking saved = bookingRepository.save(booking);
		return ResponseEntity.ok(saved);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Booking>> getAllBooking() {
		Iterable<Booking> bookings = bookingRepository.findAll();
		return ResponseEntity.ok(bookings);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> getBooking(@PathVariable String id) {
		return bookingRepository.findById(id).map(booking -> ResponseEntity.ok(booking))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> updateBooking(@PathVariable String id, @RequestBody Booking booking) {
		return bookingRepository.findById(id).map(existing -> {
			existing.setDate(booking.getDate());
			existing.setPetId(booking.getPetId());
			existing.setProcedureId(booking.getProcedureId());
			existing.setVetId(booking.getVetId());
			Booking saved = bookingRepository.save(existing);
			return ResponseEntity.ok(saved);
		}).orElse(ResponseEntity.notFound().build());
	}
}
