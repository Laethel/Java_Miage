package modele;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Chaine {
	
	private SimpleStringProperty code;
	private SimpleStringProperty nom;
	private SimpleStringProperty sEntree;
	private SimpleStringProperty sSortie;
	private ArrayList<Element> lEntree;
	private ArrayList<Element> lSortie;
	private SimpleIntegerProperty nivAct;
	
	public Chaine (String code, String nom, String sEntree, String sSortie, int nivAct) {
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.sEntree = new SimpleStringProperty(sEntree);
		this.sSortie = new SimpleStringProperty(sSortie);
		this.nivAct = new SimpleIntegerProperty(nivAct);
		
		lEntree = new ArrayList<Element>();
		lSortie = new ArrayList<Element>();
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
		
	public void addlEntree(Element elem) { this.lEntree.add(elem); }
	public ArrayList<Element> getlEntree() { return this.lEntree; };
	public void clearLEntree() { this.lEntree.clear(); }
	
	public void addlSortie(Element elem) { this.lSortie.add(elem); }
	public ArrayList<Element> getlSortie() { return this.lSortie; };
	public void clearLSortie() { this.lSortie.clear(); }
	
	@Override
    public boolean equals(Object o) { 
  
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Chaine)) { 
            return false; 
        } 
          
        Chaine ch = (Chaine) o; 
            
        return this.code.get().equals(ch.getCode());
    } 
	
	public String toString() {
		return "Code : " + this.code + " Nom : " + this.nom + " Elements en entr�e : " + this.sEntree + 
				" Elements en sortie : " + this.sSortie + " NIveau d'activation : " + this.nivAct;
	}

}
