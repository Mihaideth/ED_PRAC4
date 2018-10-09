package Excepcions;

public class ExisteixVertex extends Exception {

	private static final long serialVersionUID = 1L;

	public ExisteixVertex(){
		super("Ja existeix aquest vertex");
	}
	
}
