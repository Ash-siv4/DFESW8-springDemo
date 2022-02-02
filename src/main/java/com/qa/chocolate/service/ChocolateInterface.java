package com.qa.chocolate.service;

import java.util.List;

public interface ChocolateInterface<T> {

	T create(T createI);

	List<T> read();

	T update(Long id, T updateI);

	T delete(Long id);// boolean delete(Long id);

	T readOne(Long id);
}
