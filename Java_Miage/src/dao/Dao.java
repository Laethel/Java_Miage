package dao;

import java.util.ArrayList;

public abstract class Dao<T> {
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T oldObj, T newObj);
	
	public abstract T find(int id);
	
	public abstract ArrayList<T> findAll();
	
}
