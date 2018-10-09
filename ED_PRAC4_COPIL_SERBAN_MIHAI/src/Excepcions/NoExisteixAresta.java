package Excepcions;

public class NoExisteixAresta extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoExisteixAresta(){
		super("No existeix aquesta aresta");
	}
	
}
