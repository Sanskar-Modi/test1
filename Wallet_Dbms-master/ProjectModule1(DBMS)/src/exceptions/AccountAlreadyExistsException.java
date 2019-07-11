package exceptions;

public class AccountAlreadyExistsException extends Exception {
	
	public AccountAlreadyExistsException()
	{
		
	}

	@Override
	public String toString() {
		return "Account Already Exists";
	}

}
