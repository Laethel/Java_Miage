package modele;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Chaine {
	
	/**
	 * Le code d'une chaine
	 */
	private SimpleStringProperty code;
	
	/**
	 * Le nom d'une chaine
	 */
	private SimpleStringProperty nom;
	
	/**
	 * La liste visuelle des elements en entree d'une chaine
	 */
	private SimpleStringProperty sEntree;
	
	/**
	 * La liste visuelle des elements en sortie d'une chaine
	 */
	private SimpleStringProperty sSortie;
	
	/**
	 * La liste des elements en entree d'une chaine
	 */
	private ArrayList<Element> lEntree;
	
	/**
	 * La liste des elements en sortie d'une chaine
	 */
	private ArrayList<Element> lSortie;
	
	/**
	 * Le temps de production d'une chaine
	 */
	private SimpleIntegerProperty tempsProd;
	
	/**
	 * Le nombre d'ouvriers qualifies requis pour une chaine
	 */
	private SimpleIntegerProperty nbQualif;
	
	/**
	 * Le nombre d'ouvriers non qualifies requis pour une chaine
	 */
	private SimpleIntegerProperty nbNonQualif;
	
	/**
	 * Le niveau d'activite d'une chaine
	 */
	private SimpleIntegerProperty nivAct;
	
	
	/**
	 * Constructeur d'un objet Chaine
	 * @param code
	 * @param nom
	 * @param sEntree
	 * @param sSortie
	 * @param tempsProd
	 * @param nbQualif
	 * @param nbNonQualif
	 * @param nivAct
	 */
	public Chaine (String code, String nom, String sEntree, String sSortie,int tempsProd, int nbQualif, int nbNonQualif, int nivAct) {
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.sEntree = new SimpleStringProperty(sEntree);
		this.sSortie = new SimpleStringProperty(sSortie);
		this.tempsProd = new SimpleIntegerProperty(tempsProd);
		this.nbQualif = new SimpleIntegerProperty(nbQualif);
		this.nbNonQualif = new SimpleIntegerProperty(nbNonQualif);
		this.nivAct = new SimpleIntegerProperty(nivAct);
		
		lEntree = new ArrayList<Element>();
		lSortie = new ArrayList<Element>();
	}
		
	/**
	 * Methode permettant de recuperer le code d'une chaine
	 * @return
	 */
	public String getCode() { return this.code.get(); }
	
	/**
	 * Methode permettant de definir le code d'une chaine
	 * @param code
	 */
	public void setCode(String code) { this.code.set(code); }
	
	/**
	 * Methode permettant de recuperer le nom d'une chaine
	 * @return
	 */
	public String getNom() { return this.nom.get(); }
	
	/**
	 * Methode permettant de definir le nom d'une chaine
	 * @param nom
	 */
	public void setNom(String nom) { this.nom.set(nom); }
	
	/**
	 * Methode permettant de recuperer les elements en entree d'une chaine
	 * @return
	 */
	public String getSEntree() { return this.sEntree.get(); }
	
	/**
	 * Methode permettant de definir les elements en entree d'une chaine
	 * @param sEntree
	 */
	public void setSEntree(String sEntree) { this.sEntree.set(sEntree); }
	
	/**
	 * Methode permettant de recuperer les elements en sortie d'une chaine
	 * @return
	 */
	public String getSSortie() { return this.sSortie.get(); }
	
	/**
	 * Methode permettant de definir les elements en sortie d'une chaine
	 * @param sSortie
	 */
	public void setSSortie(String sSortie) { this.sSortie.set(sSortie); }
	
	/**
	 * Methode permettant de recuperer le niveau d'activite d'une chaine
	 * @return
	 */
	public int getNivAct() {	return this.nivAct.get(); }
	
	/**
	 * Methode permettant de definir le niveau d'activite d'une chaine
	 * @param nivAct
	 */
	public void setNivAct(int nivAct) { this.nivAct.set(nivAct); }
		
	/**
	 * Methode permettant d'ajouter un element en entree dans une chaine
	 * @param elem
	 */
	public void addlEntree(Element elem) { this.lEntree.add(elem); }
	
	/**
	 * Methode permettant de recuperer les elements en entree dans une chaine
	 * @return
	 */
	public ArrayList<Element> getlEntree() { return this.lEntree; };
	
	/**
	 * Methode permettant de vider les elements en entree dans une chaine
	 */
	public void clearLEntree() { this.lEntree.clear(); }
	
	/**
	 * Methode permettant d'ajouter un element en sortie dans une chaine
	 * @param elem
	 */
	public void addlSortie(Element elem) { this.lSortie.add(elem); }
	
	/**
	 * Methode permettant de recuperer les elements en sortie dans une chaine
	 * @return
	 */
	public ArrayList<Element> getlSortie() { return this.lSortie; };
	
	/**
	 * Methode permettant de vider les elements en sortie dans une chaine
	 */
	public void clearLSortie() { this.lSortie.clear(); }
	
	/**
	 * Methode permettant de recuperer le temps de production d'une chaine
	 * @return
	 */
	public int getTempsProd() {	return this.tempsProd.get(); }
	
	/**
	 * Methode permettant de definir le temps de production d'une chaine
	 * @param tempsProd
	 */
	public void setTempsProd(int tempsProd) { this.tempsProd.set(tempsProd); }
	
	/**
	 * Methode permettant de recuperer le nombre de personnels qualifies necessaires d'une chaine
	 * @return
	 */
	public int getNbQualif() {	return this.nbQualif.get(); }
	
	/**
	 * Methode permettant de definir le nombre de personnels qualifies necessaires d'une chaine
	 * @param nbQualif
	 */
	public void setNbQualif(int nbQualif) { this.nbQualif.set(nbQualif); }
	
	/**
	 * Methode permettant de recuperer le nombre de personnels non qualifies necessaires d'une chaine
	 * @return
	 */
	public int getNbNonQualif() {	return this.nbNonQualif.get(); }
	
	/**
	 * Methode permettant de definir le nombre de personnels non qualifies necessaires d'une chaine
	 * @param nbNonQualif
	 */
	public void setNbNonQualif(int nbNonQualif) { this.nbQualif.set(nbNonQualif); }

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Code : " + this.code + " Nom : " + this.nom + " Elements en entrée : " + this.sEntree + 
				" Elements en sortie : " + this.sSortie + "Temps de production : " + this.tempsProd + 
				"Personnel qualifié requis : " +nbQualif +"Personnel non qualifié requis : " + nbNonQualif +
				" Niveau d'activation : " + this.nivAct;
	}

}
