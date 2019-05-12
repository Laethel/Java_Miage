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
	

	public Personnel(String prenom, String nom, String qualif, double heuresSemaine) {
		this.prenom = new SimpleStringProperty(prenom);
		this.nom = new SimpleStringProperty(nom);
		this.qualif = new SimpleStringProperty(qualif);
		this.heuresSemaine = new SimpleDoubleProperty(heuresSemaine);
	}
	
	/**
	 * @return
	 */
	public String getPrenom() { return this.prenom.get(); }	
	/**
	 * @param code
	 */
	public void setPrenom(String prenom) { this.prenom.set(prenom); }
	
	/**
	 * @return
	 */
	public String getNom() { return this.nom.get(); }
	/**
	 * @param nom
	 */
	public void setNom(String nom) { this.nom.set(nom); }

	/**
	 * @return
	 */
	public String getQualif() { return this.qualif.get(); }
	/**
	 * @param qte
	 */
	public void setQualif(String qualif) { this.qualif.set(qualif);	}	

	/**
	 * @return
	 */
	public double getHeuresSemaine() { return this.heuresSemaine.get(); }
	/**
	 * @param unite
	 */
	public void setHeuresSemaine(double heuresSemaine) { this.heuresSemaine.set(heuresSemaine);	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Prenom : " + this.prenom + " Nom : " +this.nom + " Qualifié : " + this.qualif + " Heures travaillées par semaine : " + this.heuresSemaine;
	}
}
