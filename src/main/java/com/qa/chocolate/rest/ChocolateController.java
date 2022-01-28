package com.qa.chocolate.rest;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.chocolate.domain.Chocolate;

@RestController
public class ChocolateController {

	private List<Chocolate> choco = new ArrayList<>();
	
	//CRUD
	
	//Create - POST
	@PostMapping("/createChoco")
	public void createChoco(@RequestBody Chocolate c) {
		//service -> create
		this.choco.add(c);
	}
	
	//insert into chocolate(name, brand, ...) values("dairy milk", "cadbury", ...)
	
	//Read - GET
	@GetMapping("/getChoco")
	public List<Chocolate> getChoco(){
		return this.choco;
	}
	
	//Read by ID - GET
	@GetMapping("/getOne/{id}")
	public Chocolate getOne(@PathVariable int id){
		return this.choco.get(id);
	}
	
	
	//Update - PUT/PATCH - PUT 
	// create, read by id
	@PutMapping("/updateChoco/{id}")
	public Chocolate updateChoco(@PathVariable int id, @RequestBody Chocolate ch) {
		//remove exisiting choco with id
//		this.choco.remove(id);
		//add a new choco in it's place
//		this.choco.add(id, ch);
		
		//combines remove and add
		this.choco.set(id, ch);
		//the updated choco
		return this.choco.get(id);
	}
	
	
	
	//Delete - DELETE
	@DeleteMapping("/removeChoco/{id}")
	public Chocolate removeChoco(@PathVariable int id) {
		return this.choco.remove(id);
	}
	
	
}
