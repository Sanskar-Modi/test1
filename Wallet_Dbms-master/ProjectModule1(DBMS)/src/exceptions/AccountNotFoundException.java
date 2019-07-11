package exceptions;

public class AccountNotFoundException extends Exception{

	public AccountNotFoundException()
	{
		
	}

	public String toString() {
		
		return "Account Not Found . \nPlease Enter a valid account number!!!";
	}
}
	
	
	