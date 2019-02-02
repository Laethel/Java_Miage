package modele;

public class Element {
	
	private String code;
	private String nom;
	private String qte;
	//private enum unitesMesure {LITRES, UNITES, KILOGRAMMES, PIECES};
	private String unite;

	private String prixAchat;
	private String prixVente;
	
	public Element (String code, String nom, String qte, String unite, String prixAchat, String prixVente) {
		this.code = code;
		this.nom = nom;
		this.qte = qte;
		this.unite = unite;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setQte(String qte) {
		this.qte = qte;
	}
	

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public void setPrixAchat(String prixAchat) {
		this.prixAchat = prixAchat;
	}

	public void setPrixVente(String prixVente) {
		this.prixVente = prixVente;
	}

	public String getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}

	public String getQte() {
		return qte;
	}
	
	public String getUnite() {
		return unite;
	}

	public String getPrixAchat() {
		return prixAchat;
	}

	public String getPrixVente() {
		return prixVente;
	}
	
	public String toString() {
		return "Code : " + this.code + " Nom : " +this.nom + " Quantité : " + this.qte + " Unité : " + this.unite 
				+ " Prix d'achat : " + this.prixAchat + " Prix de vente : " + this.prixVente;
	}

}
