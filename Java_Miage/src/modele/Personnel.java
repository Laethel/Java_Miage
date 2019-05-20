package modele;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Personnel {
	
	/**
	 * Le prénom d'un membre du personnel
	 */
	private SimpleStringProperty prenom;
	
	/**
	 * Le nom d'un membre du personnel
	 */
	private SimpleStringProperty nom;
	
	/**
	 * La qualification d'un membre du personnel
	 */
	private SimpleStringProperty qualif;
	
	/**
	 * Le nombre d'heures par semaine d'un membre du personnel
	 */
	private SimpleDoubleProperty heuresSemaine;
	

	/**
	 * Constructeur d'un objet Personnel
	 * @param prenom le prenom d'un membre du personnel
	 * @param nom le nom d'un membre du personnel
	 * @param qualif la qualification d'un membre du personnel
	 * @param heuresSemaine le nombre d'heures travaillees par semaine d'un membre du personnel
	 */
	public Personnel(String prenom, String nom, String qualif, double heuresSemaine) {
		this.prenom = new SimpleStringProperty(prenom);
		this.nom = new SimpleStringProperty(nom);
		this.qualif = new SimpleStringProperty(qualif);
		this.heuresSemaine = new SimpleDoubleProperty(heuresSemaine);
	}
		
	/**
	 * Methode permettant de recuperer le prenom d'un membre du personnel
	 * @return le prenom d'un membre du personnel
	 */
	public String getPrenom() { return this.prenom.get(); }	
	
	/**
	 * Methode permettant de definir le prenom d'un membre du personnel
	 * @param prenom le prenom d'un membre du personnel
	 */
	public void setPrenom(String prenom) { this.prenom.set(prenom); }
	
	/**
	 * Methode permettant de recuperer le nom d'un membre du personnel
	 * @return le nom d'un membre du personnel
	 */
	public String getNom() { return this.nom.get(); }
	
	/**
	 * Methode permettant de definir le nom d'un membre du personnel
	 * @param nom le nom d'un membre du personnel
	 */
	public void setNom(String nom) { this.nom.set(nom); }

	/**
	 * Methode permettant de recuperer la qualification d'un membre du personnel
	 * @return la qualification d'un membre du personnel
	 */
	public String getQualif() { return this.qualif.get(); }
	
	/**
	 * Methode permettant de definir la qualification d'un membre du personnel
	 * @param qualif la qualification d'un membre du personnel
	 */
	public void setQualif(String qualif) { this.qualif.set(qualif);	}	

	/**
	 * Methode permettant de recuperer le nombre d'heures travaillees par semaine pour un membre du personnel
	 * @return le nombre d'heures travaillees par semaine pour un membre du personnel
	 */
	public double getHeuresSemaine() { return this.heuresSemaine.get(); }
	
	/**
	 * Methode permettant de definir le nombre d'heures travaillees par semaine pour un membre du personnel
	 * @param heuresSemaine le nombre d'heures travaillees par semaine pour un membre du personnel
	 */
	public void setHeuresSemaine(double heuresSemaine) { this.heuresSemaine.set(heuresSemaine);	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Prenom : " + this.prenom + " Nom : " +this.nom + " Qualifié : " + this.qualif + " Heures travaillées par semaine : " + this.heuresSemaine;
	}
}
