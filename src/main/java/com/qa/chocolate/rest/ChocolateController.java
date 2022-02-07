package com.qa.chocolate.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.chocolate.domain.Chocolate;
import com.qa.chocolate.service.ChocolateService;

@RestController
public class ChocolateController {

	private ChocolateService s;

	public ChocolateController(ChocolateService s) {
		super();
		this.s = s;
	}

	@PostMapping("/createChoco")
	public ResponseEntity<Chocolate> createChoco(@RequestBody Chocolate c) {
		return new ResponseEntity<>(this.s.create(c), HttpStatus.CREATED);
	}

	@GetMapping("/getChoco")
	public List<Chocolate> getChoco() {
		return this.s.read();
	}

	@GetMapping("/getOne/{id}")
	public Chocolate getOne(@PathVariable Long id) {
		return this.s.readOne(id);
	}

	@PutMapping("/updateChoco/{id}")
	public Chocolate updateChoco(@PathVariable Long id, @RequestBody Chocolate ch) {
		return this.s.update(id, ch);
	}

	@DeleteMapping("/removeChoco/{id}")
	public Chocolate removeChoco(@PathVariable Long id) {
		return this.s.delete(id);
	}
	// alternative version of the delete method
//	public boolean remove2(@PathVariable Long id) {
//		return this.s.remove(id);
//	}

}
