package modele;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Quentin Beaussart et Damian Riquart
 *"
 */
public class Element {
	
	/**
	 * Le code d'un �l�ment
	 */
	private SimpleStringProperty code;
	
	/**
	 * Le nom d'un �l�ment
	 */
	private SimpleStringProperty nom;
	
	/**
	 * La quantit� d'un �l�ment
	 */
	private SimpleDoubleProperty qte;
	
	//private enum unitesMesure {LITRES, UNITES, KILOGRAMMES, PIECES};
	/**
	 * L'unit� d'un �l�ment
	 */
	private SimpleStringProperty unite;
	
	/**
	 * Le prix d'achat d'un �l�ment
	 */
	private SimpleStringProperty prixAchat;
	
	/**
	 * Le prix de vente d'un �l�ment
	 */
	private SimpleStringProperty prixVente;
	
	/**
	 * Minimum d'unit�s � produire et � vendre d'un �l�ment
	 */
	private SimpleDoubleProperty demande;
	
	/**
	 * Variable de calcul pour le compte rendu
	 */
	private double qteConsommee;
	private double qteProduite;
	
	/**
	 * @param code
	 * @param nom
	 * @param qte
	 * @param unite
	 * @param prixAchat
	 * @param prixVente
	 */
	public Element (String code, String nom, double qte, String unite, String prixAchat, String prixVente, double demande) {
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.qte = new SimpleDoubleProperty(qte);
		this.unite = new SimpleStringProperty(unite);
		this.prixAchat = new SimpleStringProperty(prixAchat);
		this.prixVente = new SimpleStringProperty(prixVente);
		this.demande = new SimpleDoubleProperty(demande);
		this.qteConsommee = 0;
		this.qteProduite = 0;
	}
	
	/**
	 * @return
	 */
	public String getCode() { return this.code.get(); }	
	/**
	 * @param code
	 */
	public void setCode(String code) { this.code.set(code); }
	
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
	public double getQte() { return this.qte.get(); }
	/**
	 * @param qte
	 */
	public void setQte(double qte) { this.qte.set(qte);	}	

	/**
	 * @return
	 */
	public String getUnite() { return this.unite.get(); }
	/**
	 * @param unite
	 */
	public void setUnite(String unite) { this.unite.set(unite);	}

	/**
	 * @return
	 */
	public String getPrixAchat() { return this.prixAchat.get(); }
	/**
	 * @param prixAchat
	 */
	public void setPrixAchat(String prixAchat) { this.prixAchat.set(prixAchat); }

	/**
	 * @return
	 */
	public String getPrixVente() { return this.prixVente.get(); }
	/**
	 * @param prixVente
	 */
	public void setPrixVente(String prixVente) { this.prixVente.set(prixVente); }
	
	/**
	 * @return
	 */
	public double getDemande() { return this.demande.get(); }
	/**
	 * @param demande
	 */
	public void setDemande(double demande) { this.demande.set(demande); }
		
	public double getQteConsommee() {
		return qteConsommee;
	}

	public void setQteConsommee(double qteConsommee) {
		this.qteConsommee = qteConsommee;
	}

	public double getQteProduite() {
		return qteProduite;
	}

	public void setQteProduite(double qteProduite) {
		this.qteProduite = qteProduite;
	}

	public void soustraireStock(double qte) { this.qte.set(this.getQte() - qte); }
	public void ajouterStock(double qte) { this.qte.set(this.getQte() + qte); }
	
	public void ajouterQteConsommee(double qte) { this.qteConsommee = this.getQteConsommee() + qte; }
	public void ajouterQteProduite(double qte) { this.qteProduite = this.getQteProduite() + qte; }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object o) { 
  
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Element)) { 
            return false; 
        } 
          
        Element elem = (Element) o; 
            
        return this.code.get().equals(elem.getCode());
    } 

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Code : " + this.code + " Nom : " +this.nom + " Quantité : " + this.qte + " Unité : " + this.unite 
				+ " Prix d'achat : " + this.prixAchat + " Prix de vente : " + this.prixVente + " Demande : " + this.demande;
	}

}
