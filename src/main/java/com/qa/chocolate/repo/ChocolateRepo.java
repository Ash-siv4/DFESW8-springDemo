package com.qa.chocolate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.chocolate.domain.Chocolate;

@Repository
public interface ChocolateRepo extends JpaRepository<Chocolate, Long>{

}
