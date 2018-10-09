package TADs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import java.util.HashMap;


import Excepcions.ExisteixVertex;
import Excepcions.LlistaPlena;
import Excepcions.NoExisteixAresta;
import Excepcions.NoExisteixLliga;
import Excepcions.NoExisteixVertex;

public class Partits {
	private int maxVertex;
	private int numeroVertex;
	private Graf<String, String, Partit> graf;
	private Lliga lliga;

	/**
	 * Aquesta operació crea una estructura per a guardar els partits, l’enter que passem com a
paràmetre indica el nombre màxim d’equips (no de partits) que es podran guardar a
l’estructura.
	 * @param max maxim d'equips
	 */
	public Partits(int max) {
		graf = new Graf<String, String, Partit>();
		numeroVertex = 0;
		maxVertex = max;
		lliga = new Lliga();
	}

	/**
	 * Aquesta operació afegeix un nou equip a l’estructura. Cal que es comprovi si existeix lloc lliure
i que no existeixi ja un equip amb el mateix nom.
	 * @param equip equip
	 * @throws LlistaPlena LlistaPlena
	 * @throws ExisteixVertex ExisteixVertex
	 */
	public void afegirEquip(String equip) throws LlistaPlena, ExisteixVertex {
		if (numeroVertex >= maxVertex)
			throw new LlistaPlena();

		graf.AddVertex(equip, equip);
		numeroVertex++;

	}

	/**
	 * Aquesta operació afegeix un nou partit a l’estructura entre els dos equips que passem per
paràmetre. En la part obligatòria nomes hi pot haver un partit entre dos equips. Dóna error si
algun dels dos codis no existeix dins l’estructura.
	 * @param equip1 equip local
	 * @param equip2 equip visitant
	 * @param partit partit
	 */
	public void afegirPartit(String equip1, String equip2, Partit partit) {
		try {
			graf.AddLink(equip1, equip2, partit);
		} catch (NoExisteixVertex e) {
			e.printStackTrace();
		}

		lliga.afegirEquip(equip1, partit.getLliga());
		lliga.afegirEquip(equip2, partit.getLliga());

	}

	/**
	 * Aquesta operació elimina de l’estructura l’equip identificat pel nom que passem com a
paràmetre i tots els partits que hagi jugat.
	 * @param equip equip
	 * @param lliga2 lliga
	 */
	public void esborrarEquip(String equip, String lliga2) {
		try {
			graf.removeVertex(equip);
		} catch (NoExisteixVertex e) {
			System.out.println(e);
		}
		lliga.esborrarEquip(equip, lliga2);
		numeroVertex--;

	}

	/**
	 * Aquesta operació elimina de l’estructura els partits que han jugat els dos equips identificats
pels noms que passem com a paràmetre.
	 * @param equip1 equip local
	 * @param equip2 equip visitant
	 */
	public void esborrarPartits(String equip1, String equip2) {
		try {
			graf.removeLink(equip1, equip2);
		} catch (NoExisteixVertex e) {
			System.out.println(e);
		} catch (NoExisteixAresta e) {
			System.out.println(e);
		}
	}

	/**
	 * Aquesta operació imprimeix per pantalla la informació de tots els partits jugats per l’equip
identificat pel nom que passem com a paràmetre ordenats segons la data en que s’ha jugat
cada partit.
	 * @param equip equip
	 */
	public void imprimirPartits(String equip) {
		try{
			Collection<Partit> llistapartits = null;
			String aux = "Els partids de l'equip " + equip + " son els seguents:";
			System.out.println(aux);
			
			llistapartits = graf.getAllLinks(equip).values();
			
	
			ArrayList<Partit> aux2 = new ArrayList<Partit>();
			for (Partit partit : llistapartits) {
				aux2.add(partit);
			}
	
			Collections.sort(aux2, new Comparator<Partit>() { // Ordeno els equips de l'estructura segons la data
						public int compare(Partit partit1, Partit partit2) {
							return partit1.getData().compareTo(partit2.getData());
						}
					});
	
			for (Partit partit : aux2) {
				System.out.println(partit);
			}
		}catch(NoExisteixVertex e){
			System.out.println(e);
		};
	}

	/**
	 * Aquesta operació imprimeix per pantalla la classificació d’una lliga a partir de tots els partits
que tenim introduïts. La classificació ha d’estar ordenada pels punts aconseguits en tots els
partits jugats en la lliga corresponent. Per a calcular la classificació a cada equip se li
comptabilitzen 3 punts per un partit guanyat, 1 punt per partit empatat i 0 punts per partit
perdut.
	 * @param lligastring lliga
	 */
	public void imprimirClassificacioLliga(String lligastring) {

		ArrayList<String> nomequips = new ArrayList<String>();

		class Nodeaux {
			String nom;
			Integer punts;

			public Nodeaux(String nom, Integer punts) {
				this.nom = nom;
				this.punts = punts;
			}
		}

		ArrayList<Nodeaux> equipspartits = new ArrayList<Nodeaux>();

		try {
			nomequips = lliga.getEquips(lligastring);
		

		for (int i = 0; i < nomequips.size(); i++) {

			HashMap<String, Partit> llistapartits = null;
			try {
				llistapartits = graf.getAllLinks(nomequips.get(i));
			} catch (NoExisteixVertex e) {
				System.out.println(e);
			}
			Nodeaux aux = new Nodeaux(nomequips.get(i), 0);
			equipspartits.add(aux);
			
			for (Partit partit : llistapartits.values()) {
				if (partit.getGolslocal() == partit.getGolsvisitant()) {
					aux.punts = aux.punts + 1;
				}
				if (nomequips.get(i).equalsIgnoreCase(partit.getNomlocal()) && (partit.getGolslocal() > partit.getGolsvisitant())) {
					aux.punts = aux.punts + 3;
				}
				if (nomequips.get(i).equalsIgnoreCase(partit.getNomvisitant()) && (partit.getGolslocal() < partit.getGolsvisitant())) {
					aux.punts = aux.punts + 3;
				}

			}
			
		}
		Collections.sort(equipspartits, new Comparator<Nodeaux>() { // Ordeno els equips de l'estructura segons els punts
					public int compare(Nodeaux partit1, Nodeaux partit2) {
						return partit2.punts.compareTo(partit1.punts);
					}
				});

		for (Nodeaux equip : equipspartits) {
			System.out.println("L'equip es: " + equip.nom + " i té:"
					+ equip.punts + "punts");
		}
		
		} catch (NoExisteixLliga e) {
			System.out.println(e);
		}

	}
}
