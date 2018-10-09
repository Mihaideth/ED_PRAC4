package TADs;

import java.util.ArrayList;
import java.util.HashMap;

import Excepcions.NoExisteixLliga;

public class Lliga {
	
	
	private HashMap<String, ArrayList<String>> llistalliga;
	
	/**
	 * Constructor de lliga
	 */
	public Lliga(){
		llistalliga=new HashMap<String, ArrayList<String>>();
	}
	/**
	 * Metode per afegir una lliga
	 * @param lliga lliga
	 */
	private void afegirLliga(String lliga){
		llistalliga.put(lliga, new ArrayList<String>());
	}
	
	/**
	 * Metode per afegir un equip a la lliga
	 * @param equip equip
	 * @param lliga lliga
	 */
	public void afegirEquip(String equip, String lliga){
		if(!llistalliga.containsKey(lliga)){ 
		afegirLliga(lliga);
		}
		if(!llistalliga.get(lliga).contains(equip)){ 
			llistalliga.get(lliga).add(equip);
		}
	}

	/**
	 * Metode que hem retorna tots els equips d'una lliga
	 * @param lliga lliga
	 * @return tots els equips d'una lliga
	 * @throws NoExisteixLliga NoExisteixLliga
	 */
	public ArrayList<String> getEquips(String lliga) throws NoExisteixLliga{
		if(llistalliga.get(lliga)==null)throw new NoExisteixLliga();
		else
			return llistalliga.get(lliga);
		
	}
	
	/**
	 * Metode per esborrar un equip de la lliga 
	 * @param equip equip
	 * @param lliga lliga
	 */
	public void esborrarEquip(String equip, String lliga){
		if(llistalliga.get(lliga).contains(equip)){ 
			llistalliga.get(lliga).remove(equip);
		}
	}
	
}
