package dao;

import java.util.ArrayList;

/**
 * @author Quentin Beaussart et Damian Riquart
 *
 * @param <T> l'objet associe a la DAO
 */
public abstract class Dao<T> {
	
	/**
	 * Methode permettant la creation d'un objet
	 * @param obj Un objet DAO
	 * @return Retourne l'objet associé à la dao
	 */
	public abstract boolean create(T obj);
	
	/**
	 * Methode permettant la suppression d'un objet
	 * @param obj Un objet DAO
	 * @return Retourne un booléen qui indique si l'objet a été supprimé ou non
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * Methode permettant la mise a jour d'un objet
	 * @param oldObj L'objet DAO a mettre à jour
	 * @param newObj L'objet DAO qui contient les nouvelles valeurs à mettre à jour
	 * @return Retourne un booléen qui indique si l'objet a été modifié ou non
	 */
	public abstract boolean update(T oldObj, T newObj);
	
	/**
	 * Methode permettant de trouver un objet
	 * @param id Id de l'objet
	 * @return Retour un objet associé à la dao
	 */
	public abstract T find(String id);
	
	/**
	 * Methode permettant de trouver toutes les instances d'un objet
	 * @return Retourne une liste d'objet associé à la dao
	 */
	public abstract ArrayList<T> findAll();
	
}
