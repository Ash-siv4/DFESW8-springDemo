package com.qa.chocolate.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

		// given
		// testing data

		// when
		Mockito.when(this.repo.save(newChoco)).thenReturn(savedChoco);

		// then
		assertThat(this.service.create(newChoco)).isEqualTo(savedChoco);

		// verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newChoco);

	}

	@Test
	void testUpdate() {
		// given - id, object
		Long id = 1L;
		// New choco to update
		Chocolate toUpdate = new Chocolate("Aero", "Cadbury", "Milk", 50, "Bubbly", 20);
		// Optional Chocolate
		Optional<Chocolate> optChoco = Optional.of(new Chocolate(id, null, null, null, 0, null, 0));
		// Updated Choco
		Chocolate updated = new Chocolate(id, toUpdate.getName(), toUpdate.getBrand(), toUpdate.getType(),
				toUpdate.getTastiness(), toUpdate.getTexture(), toUpdate.getSugarContent());

		// when
		Mockito.when(this.repo.findById(id)).thenReturn(optChoco);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);

		// then
		assertThat(this.service.update(id, toUpdate)).isEqualTo(updated);

		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);

//		System.out.println("update");
	}

	@Test
	void testRemove() {
		// given
		Long id = 10L;

		// when
		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		// then
		assertThat(this.service.remove(id)).isTrue();

		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

	@Test
	void testDelete() {
		// given
		Long id = 1L;
		// Optional Chocolate
		Optional<Chocolate> optChoco = Optional.of(new Chocolate(id, null, null, null, 0, null, 0));
		//deleted
		Chocolate deleted = optChoco.get();
		
		//when
		Mockito.when(this.repo.findById(id)).thenReturn(optChoco);
		
		//then
		assertThat(this.service.delete(id)).isEqualTo(deleted);
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);

	
	}

}
