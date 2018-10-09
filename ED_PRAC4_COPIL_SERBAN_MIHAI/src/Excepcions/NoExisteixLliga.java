package Excepcions;

public class NoExisteixLliga extends Exception {


	private static final long serialVersionUID = 1L;

	public NoExisteixLliga(){
		super("No existeix aquesta lliga");
	}
}
