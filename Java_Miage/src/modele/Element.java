package modele;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Element {
	
	private SimpleStringProperty code;
	private SimpleStringProperty nom;
	private SimpleDoubleProperty qte;
	//private enum unitesMesure {LITRES, UNITES, KILOGRAMMES, PIECES};
	private SimpleStringProperty unite;
	private SimpleStringProperty prixAchat;
	private SimpleStringProperty prixVente;
	
	public Element (String code, String nom, double qte, String unite, String prixAchat, String prixVente) {
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.qte = new SimpleDoubleProperty(qte);
		this.unite = new SimpleStringProperty(unite);
		this.prixAchat = new SimpleStringProperty(prixAchat);
		this.prixVente = new SimpleStringProperty(prixVente);
		
	}
	
	public String getCode() { return this.code.get(); }	
	public void setCode(String code) { this.code.set(code); }
	
	public String getNom() { return this.nom.get(); }
	public void setNom(String nom) { this.nom.set(nom); }

	public double getQte() { return this.qte.get(); }
	public void setQte(double qte) { this.qte.set(qte);	}	

	public String getUnite() { return this.unite.get(); }
	public void setUnite(String unite) { this.unite.set(unite);	}

	public String getPrixAchat() { return this.prixAchat.get(); }
	public void setPrixAchat(String prixAchat) { this.prixAchat.set(prixAchat); }

	public String getPrixVente() { return this.prixVente.get(); }
	public void setPrixVente(String prixVente) { this.prixVente.set(prixVente); }
	
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

	public String toString() {
		return "Code : " + this.code + " Nom : " +this.nom + " Quantité : " + this.qte + " Unité : " + this.unite 
				+ " Prix d'achat : " + this.prixAchat + " Prix de vente : " + this.prixVente;
	}

}
