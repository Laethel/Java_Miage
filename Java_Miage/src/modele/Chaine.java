package modele;

import java.util.List;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Chaine {
	
	private SimpleStringProperty code;
	private SimpleStringProperty nom;
	private SimpleStringProperty sEntree;
	private SimpleStringProperty sSortie;
	private List<Element> lEntree;
	private List<Element> lSortie;
	private SimpleIntegerProperty nivAct;
	private SimpleFloatProperty resultat;
	
	public Chaine (String code, String nom, String sEntree, String sSortie, int nivAct) {
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.sEntree = new SimpleStringProperty(sEntree);
		this.sSortie = new SimpleStringProperty(sSortie);
		this.nivAct = new SimpleIntegerProperty(nivAct);
	}
	
	public String getCode() { return this.code.get(); }
	public void setCode(String code) { this.code.set(code); }
	
	public String getNom() { return this.nom.get(); }
	public void setNom(String nom) { this.nom.set(nom); }
	
	public String getSEntree() { return this.sEntree.get(); }
	public void setSEntree(String sEntree) { this.sEntree.set(sEntree); }
	
	public String getSSortie() { return this.sSortie.get(); }
	public void setSSortie(String sSortie) { this.sSortie.set(sSortie); }
	
	public int getNivAct() {	return this.nivAct.get(); }
	public void setNivAct(int nivAct) { this.nivAct.set(nivAct); }
	
	public float getResultat() {	return this.resultat.get(); }
	public void setResutat(float resultat) { this.resultat.set(resultat); }
	
	public String toString() {
		return "Code : " + this.code + " Nom : " + this.nom + " Elements en entr�e : " + this.sEntree + 
				" Elements en sortie : " + this.sSortie + " NIveau d'activation : " + this.nivAct + 
				" R�sultat : " + this.resultat;
	}

}
