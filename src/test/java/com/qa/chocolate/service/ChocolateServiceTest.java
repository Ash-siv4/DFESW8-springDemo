package com.qa.chocolate.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.chocolate.domain.Chocolate;
import com.qa.chocolate.repo.ChocolateRepo;

@SpringBootTest
@ActiveProfiles("test")
public class ChocolateServiceTest {

	private Chocolate newChoco;
	private Chocolate savedChoco;

	@Autowired
	private ChocolateService service;

	@MockBean
	private ChocolateRepo repo;

	@BeforeEach
	void setUp() {
		newChoco = new Chocolate("Twirl", "Cadbury", "Milk", 103, "Crumbly", 20);
		savedChoco = new Chocolate(1L, "Twirl", "Cadbury", "Milk", 103, "Crumbly", 20);
//		System.out.println("before");
	}

	@Test
	void testCreate() {
//		System.out.println("create");
		// GIVEN
		// testing data - has been setup in the "beforeEach" method
		// WHEN
		Mockito.when(this.repo.save(newChoco)).thenReturn(savedChoco);
		// THEN
		assertThat(this.service.create(newChoco)).isEqualTo(savedChoco);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).save(newChoco);
	}

	@Test
	void testUpdate() {
		// GIVEN - id, object
		Long id = 1L;
		// NEW CHOCO OBJECT FOR INPUT TO UPDATE METHOD
		Chocolate toUpdate = new Chocolate("Aero", "Cadbury", "Milk", 50, "Bubbly", 20);
		// METHOD USES AN OPTIONAL VERSION OF THE CHOCOLATE OBJECT
		Optional<Chocolate> optChoco = Optional.of(new Chocolate(id, null, null, null, 0, null, 0));
		// UPDATED VERSION:
		Chocolate updated = new Chocolate(id, toUpdate.getName(), toUpdate.getBrand(), toUpdate.getType(),
				toUpdate.getTastiness(), toUpdate.getTexture(), toUpdate.getSugarContent());
		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(optChoco);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		// THEN
		assertThat(this.service.update(id, toUpdate)).isEqualTo(updated);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
//		System.out.println("update");
	}

	// test for the alternatively used remove method - commented out since not used
	// in the application
//	@Test
//	void testRemove() {
//		// GIVEN
//		Long id = 10L;
//		// WHEN
//		Mockito.when(this.repo.existsById(id)).thenReturn(false);
//		// THEN
//		assertThat(this.service.remove(id)).isTrue();
//		// VERIFY
//		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
//		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
//	}

	@Test
	void testDelete() {
		// GIVEN
		Long id = 1L;
		// Optional Chocolate
		Optional<Chocolate> optChoco = Optional.of(new Chocolate(id, null, null, null, 0, null, 0));
		// deleted
		Chocolate deleted = optChoco.get();
		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(optChoco);
		// THEN
		assertThat(this.service.delete(id)).isEqualTo(deleted);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

}
