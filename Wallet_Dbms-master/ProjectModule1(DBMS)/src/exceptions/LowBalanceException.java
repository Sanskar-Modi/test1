package exceptions;

public class LowBalanceException extends Exception {
	
	public LowBalanceException()
	{
		
	}
	@Override
	public String toString() {
		return "Low Balance \nCannot Operation";
	}
	

}