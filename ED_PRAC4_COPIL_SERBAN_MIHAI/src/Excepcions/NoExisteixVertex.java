package Excepcions;

public class NoExisteixVertex extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoExisteixVertex(){
		super("No existeix aquest vertex");
	}

}
