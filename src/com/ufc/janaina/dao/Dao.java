package com.ufc.janaina.dao;

import java.util.List;

import com.ufc.janaina.models.Usuario;

public interface Dao<T> {
	T get(int id);
	void insert(T t);
	void update(T t);
	void delete(T t);
}
