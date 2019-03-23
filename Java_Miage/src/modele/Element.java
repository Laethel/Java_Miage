package modele;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Quentin Beaussart et Damian Riquart
 *"
 */
public class Element {
	
	/**
	 * Le code d'un élément
	 */
	private SimpleStringProperty code;
	
	/**
	 * Le nom d'un élément
	 */
	private SimpleStringProperty nom;
	
	/**
	 * La quantité d'un élément
	 */
	private SimpleDoubleProperty qte;
	
	//private enum unitesMesure {LITRES, UNITES, KILOGRAMMES, PIECES};
	/**
	 * L'unité d'un élément
	 */
	private SimpleStringProperty unite;
	
	/**
	 * Le prix d'achat d'un élément
	 */
	private SimpleStringProperty prixAchat;
	
	/**
	 * Le prix de vente d'un élément
	 */
	private SimpleStringProperty prixVente;
	
	/**
	 * @param code
	 * @param nom
	 * @param qte
	 * @param unite
	 * @param prixAchat
	 * @param prixVente
	 */
	public Element (String code, String nom, double qte, String unite, String prixAchat, String prixVente) {
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.qte = new SimpleDoubleProperty(qte);
		this.unite = new SimpleStringProperty(unite);
		this.prixAchat = new SimpleStringProperty(prixAchat);
		this.prixVente = new SimpleStringProperty(prixVente);
		
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
	
	public void soustraireStock(double qte) { this.qte.set(this.getQte() - qte); }
	public void ajouterStock(double qte) { this.qte.set(this.getQte() + qte); }
	
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
				+ " Prix d'achat : " + this.prixAchat + " Prix de vente : " + this.prixVente;
	}

}
