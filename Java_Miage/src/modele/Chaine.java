package modele;

public class Chaine {
	
	private String code;
	private String nom;
	private String entree;
	private String sortie;
	private String nivAct;
	private String resultat;
	
	public Chaine (String code, String nom, String entree, String sortie) {
		this.code = code;
		this.nom = nom;
		this.entree = entree;
		this.sortie = sortie;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEntree() {
		return entree;
	}
	public void setEntree(String entree) {
		this.entree = entree;
	}
	public String getSortie() {
		return sortie;
	}
	public void setSortie(String sortie) {
		this.sortie = sortie;
	}
	

}
