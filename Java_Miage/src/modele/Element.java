package modele;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Quentin Beaussart et Damian Riquart
 *"
 */
public class Element {
	
	/**
	 * Le code d'un element
	 */
	private SimpleStringProperty code;
	
	/**
	 * Le nom d'un element
	 */
	private SimpleStringProperty nom;
	
	/**
	 * La quantit� d'un element
	 */
	private SimpleDoubleProperty qte;
	
	/**
	 * L'unite d'un element
	 */
	private SimpleStringProperty unite;
	
	/**
	 * Le prix d'achat d'un element
	 */
	private SimpleStringProperty prixAchat;
	
	/**
	 * Le prix de vente d'un element
	 */
	private SimpleStringProperty prixVente;
	
	/**
	 * Minimum d'unites a produire et a vendre d'un element
	 */
	private SimpleDoubleProperty demande;
	
	/**
	 * Variable de calcul pour le compte rendu
	 */
	private double qteConsommee;
	private double qteProduite;
	
	/**
	 * Constructeur d'un objet Element
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
	 * Methode permettant de recuperer le code d'un element
	 * @return
	 */
	public String getCode() { return this.code.get(); }	
	
	/**
	 * Methode permettant de definir le code d'un element
	 * @param code
	 */
	public void setCode(String code) { this.code.set(code); }
	
	/**
	 * Methode permettant de recuperer le nom d'un element
	 * @return
	 */
	public String getNom() { return this.nom.get(); }
	
	/**
	 * Methode permettant de definir le nom d'un element
	 * @param nom
	 */
	public void setNom(String nom) { this.nom.set(nom); }

	/**
	 * Methode permettant de recuperer la quantite d'un element
	 * @return
	 */
	public double getQte() { return this.qte.get(); }
	
	/**
	 * Methode permettant de definir la quantite d'un element
	 * @param qte
	 */
	public void setQte(double qte) { this.qte.set(qte);	}	

	/**
	 * Methode permettant de recuperer l'unite d'un element
	 * @return
	 */
	public String getUnite() { return this.unite.get(); }
	
	/**
	 * Methode permettant de definir l'unite d'un element
	 * @param unite
	 */
	public void setUnite(String unite) { this.unite.set(unite);	}

	/**
	 * Methode permettant de recuperer le prix d'achat d'un element
	 * @return
	 */
	public String getPrixAchat() { return this.prixAchat.get(); }
	
	/**
	 * Methode permettant de definir le prix d'achat d'un element
	 * @param prixAchat
	 */
	public void setPrixAchat(String prixAchat) { this.prixAchat.set(prixAchat); }

	/**
	 * Methode permettant de recuperer le prix de vente d'un element
	 * @return
	 */
	public String getPrixVente() { return this.prixVente.get(); }
	
	/**
	 * Methode permettant de definir le prix de vente d'un element
	 * @param prixVente
	 */
	public void setPrixVente(String prixVente) { this.prixVente.set(prixVente); }
	
	/**
	 * Methode permettant de recuperer la demande d'un element
	 * @return
	 */
	public double getDemande() { return this.demande.get(); }
	
	/**
	 * Methode permettant de definir la demande d'un element
	 * @param demande
	 */
	public void setDemande(double demande) { this.demande.set(demande); }
		
	/**
	 * Methode permettant de recuperer la quantite consommee d'un element
	 * @return
	 */
	public double getQteConsommee() {
		return qteConsommee;
	}

	/**
	 * Methode permettant de definir la quantite consommee d'un element
	 * @param qteConsommee
	 */
	public void setQteConsommee(double qteConsommee) {
		this.qteConsommee = qteConsommee;
	}

	/**
	 * Methode permettant de recuperer la quantite produite d'un element
	 * @return
	 */
	public double getQteProduite() {
		return qteProduite;
	}

	/**
	 * Methode permettant de definir la quantite produite d'un element
	 * @param qteProduite
	 */
	public void setQteProduite(double qteProduite) {
		this.qteProduite = qteProduite;
	}

	/**
	 * Methode permettant de soustraire du stock une quantite d'un element
	 * @param qte
	 */
	public void soustraireStock(double qte) { this.qte.set(this.getQte() - qte); }
	
	/**
	 * Methode permettant d'ajouter au stock une quantite d'un element
	 * @param qte
	 */
	public void ajouterStock(double qte) { this.qte.set(this.getQte() + qte); }
	
	/**
	 * Methode permettant d'ajouter la quantite consommee d'un element lors de la production
	 * @param qte
	 */
	public void ajouterQteConsommee(double qte) { this.qteConsommee = this.getQteConsommee() + qte; }
	
	/**
	 * Methode permettant d'ajouter la quantite produite d'un element lors de la production
	 * @param qte
	 */
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
