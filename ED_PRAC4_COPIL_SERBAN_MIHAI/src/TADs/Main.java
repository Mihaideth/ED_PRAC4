package TADs;


import java.util.Date;
import java.util.Scanner;


import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import Excepcions.ExisteixVertex;
import Excepcions.LlistaPlena;

public class Main {

	static Scanner teclat = new Scanner(System.in);
	static Partits graf;
	static long start, end, res;
	
	
	public static void Menu() {
		System.out.println("\n\nMENU:");
		System.out.println("\n\t1. Afegir un equip a l'estructura");
		System.out.println("\t2. Afegir un partit a l'estructura");
		System.out.println("\t3. Esborrar un equip de l'estructura");
		System.out.println("\t4. Esborrar un partit de l'estructura");
		System.out.println("\t5. Imprimir tots els partits de l'estructura");
		System.out.println("\t6. Imprimir la classificacio de la lliga");
		System.out.println("\t7. Sortir");
		System.out.print("\n\t\t\tQuina opcio vols escollir?:\n");
	}
	
	
	public static void afegirEquip(){
		
		System.out.println("Introdueix el nom de l'equip en questio");
		String equip = teclat.nextLine();
		equip = teclat.nextLine();
		try {
			start = System.nanoTime();
			graf.afegirEquip(equip);
			end = System.nanoTime();
			res = end - start;
			System.out.println("Temps en nanosegons: "+res);
			System.out.println("S'ha afegit correctament");
		} catch (LlistaPlena e) {
			e.printStackTrace();
		} catch (ExisteixVertex e) {
			e.printStackTrace();
		}
		
		
		
	}
	public static void afegirPartit(){
		System.out.println("Introdueix el nom de l'equip local en questio");
		String equip = teclat.nextLine();
		equip = teclat.nextLine();
		System.out.println("Introdueix el nom de l'equip visitant en questio");
		String equip2 = teclat.nextLine();
		
		
		System.out.println("Introdueix l'identificador del partit");
		int id = llegirEnter();
		System.out.println("Introdueix els gols de l'equip local");
		int golslocal = llegirEnter();
		System.out.println("Introdueix els gols de l'equip visitant");
		int golsvisitant = llegirEnter();
		System.out.println("Introdueix la lliga a la qual pertany");
		String lliga = teclat.nextLine();
		lliga = teclat.nextLine();
		
		System.out.println("Introdueix l'any en que va tenir lloc");
		int any = llegirEnter();
		System.out.println("Introdueix el mes en que va tenir lloc");
		int mes = llegirEnter();
		System.out.println("Introdueix el dia en que va tenir lloc");
		int dia = llegirEnter();
		any=any-1900;
		
		@SuppressWarnings("deprecation")
		Date data = new Date(any, mes, dia);  //el metode esta obsolet pero funciona igual
		
	
		Partit partit = new Partit(id, equip, equip2, golslocal, golsvisitant, lliga, data);
		
		start = System.nanoTime();
		graf.afegirPartit(equip, equip2, partit);
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);
		
	}
	
	
	public static void esborrarEquip(){
		System.out.println("Introdueix el nom de l'equip en questio");
		String equip = teclat.nextLine();
		equip = teclat.nextLine();
		System.out.println("Introdueix la lliga a la qual pertany l'equip");
		String lliga2 = teclat.nextLine();
		
		
		
		start = System.nanoTime();
		graf.esborrarEquip(equip, lliga2);
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);
		System.out.println("S'ha esborrat correctament");
	}
	
	public static void esborrarPartits(){
		System.out.println("Introdueix el nom de l'equip local");
		String equip = teclat.nextLine();
		equip = teclat.nextLine();
		
		System.out.println("Introdueix el nom de l'equip visitant");
		String equip2 = teclat.nextLine();
		
		
		start = System.nanoTime();
		graf.esborrarPartits(equip, equip2);
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);
		System.out.println("S'ha esborrat correctament");
		
	}
	
	public static void imprimirPartits(){
		
		System.out.println("Introdueix el nom de l'equip en questio");
		String equip = teclat.nextLine();
		equip = teclat.nextLine();
		
		start = System.nanoTime();
		graf.imprimirPartits(equip);
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);
		
	}
	public static void imprimirClassificacioLliga(){
		System.out.println("Introdueix el nom de la lliga");
		String lliga = teclat.nextLine();
		lliga = teclat.nextLine();
		
		start = System.nanoTime();
		graf.imprimirClassificacioLliga(lliga);
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);
		
	}
	
	
	
	/**
	 * Mètode que carrega els jugadors des d'un fitxer
	 * @throws IOException Fitxer no trobat
	 * @throws LlistaPlena LlistaPlena
	 */
	public static void ImportarArxiu() throws IOException, LlistaPlena {
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader("file.txt"));
			String linea = "";
			linea = br.readLine();
			while (linea != null) {
				StringTokenizer st = new StringTokenizer(linea, ",");
				
				String lliga = st.nextToken();
				int idpartit = Integer.parseInt(st.nextToken());
				String equiplocal = st.nextToken();
				String equipvisitant = st.nextToken();
				int golslocal = Integer.parseInt(st.nextToken());
				int golsvisitant = Integer.parseInt(st.nextToken());
				
				StringTokenizer st2 = new StringTokenizer(st.nextToken(), "-");
				int any = Integer.parseInt(st2.nextToken());
				int mes = Integer.parseInt(st2.nextToken());
				int dia = Integer.parseInt(st2.nextToken());
				any=any-1900;
				
				@SuppressWarnings("deprecation")
				Date data = new Date(any, mes, dia);  //el metode esta obsolet pero funciona igual
				
			
				Partit partit = new Partit(idpartit, equiplocal, equipvisitant, golslocal, golsvisitant, lliga, data);
							
				
				try {
					graf.afegirEquip(equiplocal);
				} catch (ExisteixVertex e) {
						
				}
				try {
					graf.afegirEquip(equipvisitant);
				} catch (ExisteixVertex e) {
						
				}
			
				
				graf.afegirPartit(equiplocal, equipvisitant, partit);
				
				
				linea = br.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fitxer no trobat");
		} catch (LlistaPlena e) {
			System.out.println("La llista ja esta plena");	
		} finally {
			br.close();
		}

	}
	
	/**
	 * Metode que comproba que el valor introduit sigui un Enter;
	 * @return retorna el valor que estem comprobant si aquest es valid;
	 */
	public static int llegirEnter() {
		boolean comprobat = false;
		int valor = 0;
		while (!comprobat) {
			try {
				String s = teclat.next();
				valor = Integer.parseInt(s);
				comprobat = true;
			} catch (NumberFormatException e) {
				System.out.print("Error en el format del codi\n");
			}
		}
		return valor;
	}
	
	
	public static void main(String[] args) {

		int opcio;
		int capacitat=0;
		
		System.out.println("Introdueix la capacitat maxima del conjunt");
		capacitat = llegirEnter();
		
		graf = new Partits(capacitat);
		System.out.println("S'ha creat correctament");

				
		try {
			
			try {
				start = System.nanoTime();
				ImportarArxiu();
				end = System.nanoTime();
				res = end - start;
				System.out.println("Temps en nanosegons: "+res);
			} catch (LlistaPlena e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Error al tractar l'arxiu");

		}
		

		Menu();
		opcio = llegirEnter();

		while (opcio != 7) {
			switch (opcio) {
			case 1:
				afegirEquip();
				break;
			case 2:
				afegirPartit();
				break;
			case 3:
				esborrarEquip();
				break;
			case 4:
				esborrarPartits();
				break;
			case 5:
				imprimirPartits();
				break;
			case 6:
				imprimirClassificacioLliga();
				break;
			}
			

			Menu();
			opcio = llegirEnter();
		}
	}
	
}
