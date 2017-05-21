package co.exception;

public class ManagerListNotEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7498635402135455758L;
	public String getLocalizedMessage(){
		return "Mananger List Not Empty";	
	}
}
