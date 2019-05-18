package dao;

import java.util.ArrayList;

/**
 * @author Quentin Beaussart et Damian Riquart
 *
 * @param <T>
 */
public abstract class Dao<T> {
	
	/**
	 * Methode permettant la creation d'un objet
	 * @param obj
	 * @return
	 */
	public abstract boolean create(T obj);
	
	/**
	 * Methode permettant la suppression d'un objet
	 * @param obj
	 * @return
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * Methode permettant la mise a jour d'un objet
	 * @param oldObj
	 * @param newObj
	 * @return
	 */
	public abstract boolean update(T oldObj, T newObj);
	
	/**
	 * Methode permettant de trouver un objet
	 * @param id
	 * @return
	 */
	public abstract T find(String id);
	
	/**
	 * Methode permettant de trouver toutes les instances d'un objet
	 * @return
	 */
	public abstract ArrayList<T> findAll();
	
}
