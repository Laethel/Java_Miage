package modele;

/**
 * @author Quentin Beaussart et Damian Riquart
 * 
 */
public class Chaine {
	
	private String code;
	private String nom;
	private String entree;
	private String sortie;
	private String nivAct;
	private String resultat;
	
	/**
	 * @param code 
	 * @param nom
	 * @param entree
	 * @param sortie
	 * @param nivAct
	 */
	public Chaine (String code, String nom, String entree, String sortie, String nivAct) {
		this.code = code;
		this.nom = nom;
		this.entree = entree;
		this.sortie = sortie;
		this.nivAct = nivAct;
	}
	
	/**
	 * @return
	 * Renvoie le code de la chaine
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code
	 * D�finit le code de la chaine
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return
	 * Renvoie le nom de la chaine
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom
	 * D�finit le nom de la chaine
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return
	 * Renvoie les �l�ments en entr�e de la chaine
	 */
	public String getEntree() {
		return entree;
	}
	/**
	 * @param entree
	 * D�finit les �l�ments en entr�e de la chaine
	 */
	public void setEntree(String entree) {
		this.entree = entree;
	}
	/**
	 * @return
	 * Renvoie les �l�ments en sortie de la chaine
	 */
	public String getSortie() {
		return sortie;
	}
	/**
	 * @param sortie
	 * D�finit les �l�ments en sortie de la chaine
	 */
	public void setSortie(String sortie) {
		this.sortie = sortie;
	}
	/**
	 * @return
	 * Renvoie le niveau d'activit� de la chaine
	 */
	public String getNivAct() {
		return nivAct;
	}

	/**
	 * @param nivAct
	 * D�finit le niveau d'activit� de la chaine
	 */
	public void setNivAct(String nivAct) {
		this.nivAct = nivAct;
	}
	

}
