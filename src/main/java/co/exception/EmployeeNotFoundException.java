package co.exception;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300998537877386873L;

	@Override
	public String getMessage(){
		return "Employee Not Found";
	}
}
