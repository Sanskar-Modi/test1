package service;

import java.sql.SQLException;

import exceptions.*;

public interface BankServiceI {
		
		public boolean createAccount(String name, String add, long accNo, String phone, int pin, int bal) throws AccountAlreadyExistsException, ClassNotFoundException, SQLException;
		
		public int showBalance(long accNo) throws AccountNotFoundException;
		
		public int deposit(long accNo, int deposit_amount) throws AccountNotFoundException, ClassNotFoundException, SQLException;
		
		public int withdraw(long accNo, int withdraw_amount) throws AccountNotFoundException, LowBalanceException, ClassNotFoundException, SQLException;
		
		public boolean transferfund(long accNo , long accNo1,int transfer_amount) throws AccountNotFoundException, LowBalanceException, ClassNotFoundException, SQLException;

		public boolean validateBalance(long accNo,int amount) throws LowBalanceException;

		public String setTrans(long accNo) throws AccountNotFoundException;

	}


