/**
 * 
 */
package ca.footeware.rest.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ca.footeware.rest.petclinic.controllers.VetController;
import ca.footeware.rest.petclinic.models.Vet;
import ca.footeware.rest.petclinic.repositories.VetRepository;

@SpringBootTest
@Testcontainers
class VetControllerTests {

	private static VetController vetController;

	@Autowired
	private VetRepository vetRepository;

	@Container
	static MongoDBContainer mongoContainer = new MongoDBContainer("mongo:8.0");

	@DynamicPropertySource
	static void containersProperties(DynamicPropertyRegistry registry) {
		mongoContainer.start();
		registry.add("spring.data.mongodb.host", mongoContainer::getHost);
		registry.add("spring.data.mongodb.port", mongoContainer::getFirstMappedPort);
	}

	@BeforeEach
	void setUpBeforeEach() {
		assertTrue(mongoContainer.isRunning());
		assertNotNull(vetRepository);
		vetController = new VetController(vetRepository);
	}

	/**
	 * Test method for
	 * {@link ca.footeware.rest.petclinic.controllers.VetController#createVet(ca.footeware.rest.petclinic.models.Vet)}.
	 */
	@Test
	void testCreateVet() {
		Vet vet = new Vet("Turd", "Ferguson");
		ResponseEntity<Vet> response = vetController.createVet(vet);
		assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
		assertTrue(response.hasBody());
		assertEquals("Turd", response.getBody().getFirstName());
		assertEquals("Ferguson", response.getBody().getLastName());

		// cleanup
		vetController.deleteVet(vet.getId());
	}

	/**
	 * Test method for
	 * {@link ca.footeware.rest.petclinic.controllers.VetController#getAllVets()}.
	 */
	@Test
	void testGetAllVets() {
		// confirm zero result
		ResponseEntity<Iterable<Vet>> fetchResponse = vetController.getAllVets();
		assertEquals(HttpStatusCode.valueOf(200), fetchResponse.getStatusCode());
		assertTrue(fetchResponse.hasBody());
		Iterable<Vet> body = fetchResponse.getBody();
		long size = body.spliterator().getExactSizeIfKnown();
		assertEquals(0, size);

		// create a vet
		Vet vet1 = new Vet("Turd", "Ferguson");
		ResponseEntity<Vet> createResponse = vetController.createVet(vet1);
		assertTrue(fetchResponse.hasBody());
		assertEquals(HttpStatusCode.valueOf(200), createResponse.getStatusCode());

		// fetch all returns one
		fetchResponse = vetController.getAllVets();
		assertEquals(HttpStatusCode.valueOf(200), fetchResponse.getStatusCode());
		assertTrue(fetchResponse.hasBody());
		body = fetchResponse.getBody();
		size = body.spliterator().getExactSizeIfKnown();
		assertEquals(1, size);

		// create another vet
		Vet vet2 = new Vet("Church", "Lady");
		ResponseEntity<Vet> createResponse2 = vetController.createVet(vet2);
		assertTrue(fetchResponse.hasBody());
		assertEquals(HttpStatusCode.valueOf(200), createResponse2.getStatusCode());

		// fetch all returns two
		fetchResponse = vetController.getAllVets();
		assertEquals(HttpStatusCode.valueOf(200), fetchResponse.getStatusCode());
		assertTrue(fetchResponse.hasBody());
		body = fetchResponse.getBody();
		size = body.spliterator().getExactSizeIfKnown();
		assertEquals(2, size);

		// clean up
		vetController.deleteVet(vet1.getId());
		vetController.deleteVet(vet2.getId());
	}

	/**
	 * Test method for
	 * {@link ca.footeware.rest.petclinic.controllers.VetController#getVet(java.lang.String)}.
	 */
	@Test
	void testGetVet() {
		// create a vet
		Vet vet = new Vet("Turd", "Ferguson");
		ResponseEntity<Vet> createResponse = vetController.createVet(vet);
		assertEquals(HttpStatusCode.valueOf(200), createResponse.getStatusCode());
		
		// get its id
		assertTrue(createResponse.hasBody());
		String id = createResponse.getBody().getId();

		// fetch vet and confirm properties
		ResponseEntity<Iterable<Vet>> fetchResponse = vetController.getAllVets();
		assertTrue(fetchResponse.hasBody());
		Iterable<Vet> body = fetchResponse.getBody();
		long size = body.spliterator().getExactSizeIfKnown();
		assertEquals(1, size);
		vet = body.iterator().next();
		assertEquals("Turd", vet.getFirstName());
		assertEquals("Ferguson", vet.getLastName());

		assertEquals(id, vet.getId());

		vetController.deleteVet(vet.getId());
	}

	/**
	 * Test method for
	 * {@link ca.footeware.rest.petclinic.controllers.VetController#updateVet(java.lang.String, ca.footeware.rest.petclinic.models.Vet)}.
	 */
	@Test
	void testUpdateVet() {
		// create a vet to modify and update
		ResponseEntity<Vet> response = vetController.createVet(new Vet("Turd", "Ferguson"));
		assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
		assertTrue(response.hasBody());
		assertEquals("Turd", response.getBody().getFirstName());
		assertEquals("Ferguson", response.getBody().getLastName());
		Vet vet = response.getBody();
		String id = vet.getId();
		
		// modify vet
		vet.setFirstName("Church");
		vet.setLastName("Lady");
		
		// update vet
		response = vetController.updateVet(vet.getId(), vet);
		assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
		assertTrue(response.hasBody());
		assertEquals("Church", response.getBody().getFirstName());
		assertEquals("Lady", response.getBody().getLastName());
		assertEquals(id, vet.getId());

		// cleanup
		vetController.deleteVet(vet.getId());
	}

	/**
	 * Test method for
	 * {@link ca.footeware.rest.petclinic.controllers.VetController#deleteVet(java.lang.String)}.
	 */
	@Test
	void testDeleteVet() {
		// create vet to delete by its id
		ResponseEntity<Vet> response = vetController.createVet(new Vet("Turd", "Ferguson"));
		assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
		assertTrue(response.hasBody());
		assertEquals("Turd", response.getBody().getFirstName());
		assertEquals("Ferguson", response.getBody().getLastName());
		Vet vet = response.getBody();
		String id = vet.getId();

		// delete vet
		ResponseEntity<?> deleteResponse = vetController.deleteVet(id);
		assertEquals(HttpStatusCode.valueOf(200), deleteResponse.getStatusCode());

		// confirm deletion
		response = vetController.getVet(id);
		assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
	}
}
