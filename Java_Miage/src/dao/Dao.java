package dao;

import java.util.ArrayList;

/**
 * @author Quentin Beaussart et Damian Riquart
 *
 * @param <T>
 */
public abstract class Dao<T> {
	
	/**
	 * @param obj
	 * @return
	 */
	public abstract boolean create(T obj);
	
	/**
	 * @param obj
	 * @return
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * @param oldObj
	 * @param newObj
	 * @return
	 */
	public abstract boolean update(T oldObj, T newObj);
	
	/**
	 * @param id
	 * @return
	 */
	public abstract T find(String id);
	
	/**
	 * @return
	 */
	public abstract ArrayList<T> findAll();
	
}
