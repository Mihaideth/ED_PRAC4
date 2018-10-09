package TADs;
import java.util.Date;


public class Partit {
	private int ID;
	private String nomlocal;
	private String nomvisitant;
	private int golslocal;
	private int golsvisitant;
	private String lliga;
	private Date data;
	
	/**
	 * Constructor de Partit
	 * @param ID ID
	 * @param nomlocal nom local
	 * @param nomvisitant nom visitant
	 * @param golslocal gols local
	 * @param golsvisitant gols visitant
	 * @param lliga lliga
	 * @param data data
	 */
	public Partit(int ID, String nomlocal, String nomvisitant, int golslocal, int golsvisitant, String lliga, Date data){
		this.ID=ID;
		this.nomlocal=nomlocal;
		this.nomvisitant=nomvisitant;
		this.golslocal=golslocal;
		this.golsvisitant=golsvisitant;
		this.lliga=lliga;
		this.data=data;
		
	}


	/**
	 * Getter de ID
	 * @return ID
	 */
	public int getID() {
		return ID;
	}


	/**
	 * Getter de nom local
	 * @return nom local
	 */
	public String getNomlocal() {
		return nomlocal;
	}


	/**
	 * Getter de nom visitant
	 * @return nom visitant
	 */
	public String getNomvisitant() {
		return nomvisitant;
	}


	/**
	 * Getter de gols local
	 * @return gols local
	 */
	public int getGolslocal() {
		return golslocal;
	}


	/**
	 * Getter de gols visitant
	 * @return data gols visitant
	 */
	public int getGolsvisitant() {
		return golsvisitant;
	}


	/**
	 * Getter de lliga
	 * @return lliga
	 */
	public String getLliga() {
		return lliga;
	}

	/**
	 * Getter de data
	 * @return data
	 */
	public Date getData() {
		return data;
	}


	/**
	 * Metode toString
	 */
	public String toString() {
		return "Partit [ID=" + ID + ", nomlocal=" + nomlocal + ", nomvisitant="
				+ nomvisitant + ", golslocal=" + golslocal + ", golsvisitant="
				+ golsvisitant + ", lliga=" + lliga + ", data=" + data + "]";
	}
	
	
	
	
	
	
	
	
}
