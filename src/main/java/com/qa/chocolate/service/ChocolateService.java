package com.qa.chocolate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.chocolate.domain.Chocolate;
import com.qa.chocolate.repo.ChocolateRepo;

@Service
public class ChocolateService implements ChocolateInterface<Chocolate> {

	private ChocolateRepo r;

	public ChocolateService(ChocolateRepo repo) {
		super();
		this.r = repo;
	}

	@Override
	public Chocolate create(Chocolate createI) {
		// TODO Auto-generated method stub
		return this.r.save(createI);
	}

	@Override
	public List<Chocolate> read() {
		// TODO Auto-generated method stub
		return this.r.findAll();
	}

	@Override
	public Chocolate update(Long id, Chocolate updateI) {
		// TODO Auto-generated method stub
		Optional<Chocolate> toFind = this.r.findById(id);
		Chocolate found = toFind.get();
		// set all the individual attributes
		found.setName(updateI.getName());
		found.setBrand(updateI.getBrand());
		found.setType(updateI.getType());
		found.setTastiness(updateI.getTastiness());
		found.setTexture(updateI.getTexture());
		found.setSugarContent(updateI.getSugarContent());
		return this.r.save(found);
	}

	@Override
	public Chocolate delete(Long id) {
		// TODO Auto-generated method stub
		Optional<Chocolate> toDelete = this.r.findById(id);
		this.r.deleteById(id);
		return toDelete.orElse(null);
	}

	public boolean remove(Long id) {
		this.r.deleteById(id);
		return !this.r.existsById(id);

	}

	@Override
	public Chocolate readOne(Long id) {
		// TODO Auto-generated method stub
		Optional<Chocolate> optionalC = this.r.findById(id);
		return optionalC.orElse(null);
	}

}
