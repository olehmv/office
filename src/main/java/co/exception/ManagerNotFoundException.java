package co.exception;

public class ManagerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3496707019224279140L;
	public String getLocalizedMessage(){
		return "Mananger Not Found";	
	}
}
