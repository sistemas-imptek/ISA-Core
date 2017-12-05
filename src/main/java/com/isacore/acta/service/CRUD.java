package com.isacore.acta.service;

import java.util.List;

public interface CRUD<T> {

	List<T> findAll();

	T create(T obj);

	T findById(String id);

	T update(T obj);

	void delete(String id);
}
