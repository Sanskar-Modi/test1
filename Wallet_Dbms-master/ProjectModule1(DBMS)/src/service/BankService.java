package service;

import java.sql.SQLException;

import bean.BankBean;
import dao.BankDao;
import dao.BankDaoI;
import exceptions.*;

public class BankService  implements BankServiceI{

	BankDaoI dao = (BankDaoI) new BankDao();
	BankBean bank = new BankBean();
	BankBean bank1 = new BankBean();

	boolean res;

	// to get the details if account exists or not and add account

	public boolean createAccount(String name, String add, long accNo, String phone, int pin, int bal)
			throws AccountAlreadyExistsException, ClassNotFoundException, SQLException {

		BankBean bean = new BankBean();
		boolean res = false;
         res = dao.checkAccount(accNo);
   
		if (res) {
			bean.setAccNo(accNo);
			bean.setAdd(add);
			bean.setBalance(bal);
			bean.setName(name);
			bean.setPhone(phone);
			bean.setPin(pin);
			bean.setTrans("Account Created with Balance  : " + bal + "\n");

			dao.InsertData(accNo, bean);

			res = true;
		}

		else

		{
			res = false;
			throw new AccountAlreadyExistsException();
		}
		return res;

	}

	// to show balance

	public int showBalance(long accNo) throws AccountNotFoundException {

		try {
			res = dao.checkAccount(accNo);
		} 
		
		catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		int balance = 0;
		
		if (bank == null) {
			throw new AccountNotFoundException();
		} else {
			balance = bank.getBalance();
		}

		return balance;
	}

	// to deposit

	public int deposit(long accNo, int deposit_amount) throws AccountNotFoundException, ClassNotFoundException, SQLException {

		int balance = 0;
		try {
			res =  dao.checkAccount(accNo);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}

		if (bank == null) {
			throw new AccountNotFoundException();
		} else {

			balance = bank.setBalance(bank.getBalance() + deposit_amount);
			String s = bank.getTrans() + "Amount deposited :" + deposit_amount + "\n";
			bank.setTrans(s);
		//	dao.InsertData(accNo,bank);
			dao.updateData(bank);
		}

		return balance;
	}

	// to withdraw

	public int withdraw(long accNo, int withdraw_amount) throws AccountNotFoundException, LowBalanceException, ClassNotFoundException, SQLException {

		int balance = 0;
		try {
			res = dao.checkAccount(accNo);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		if (bank == null) {
			throw new AccountNotFoundException();
		} else {
			if (bank.getBalance() > withdraw_amount) {
				balance = bank.setBalance(bank.getBalance() - withdraw_amount);
				String s = bank.getTrans() + "Amount withdrawn :" + withdraw_amount + "\n";
				bank.setTrans(s);
			} else {
				throw new LowBalanceException();
			}
			//dao.InsertData(accNo, bank);
			dao.updateData(bank);
		}

		return balance;
	}

	// to transfer fund

	public boolean transferfund(long accNo, long accNo1, int transfer_amount)
			throws AccountNotFoundException, LowBalanceException, ClassNotFoundException, SQLException {

		try {
			res = dao.checkAccount(accNo);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}

		if (!(bank == null)) {

			try {
				res = dao.checkAccount(accNo1);
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println(e);
			}

			if (!(bank1 == null))

			{

				int sender_balance = bank.getBalance();

				if (sender_balance > transfer_amount) {
					int reciever_balance = bank1.getBalance();

					bank.setBalance(sender_balance - transfer_amount);
					bank1.setBalance(reciever_balance + transfer_amount);
					String s = bank.getTrans() + "Transferred to  :" + accNo1 +" Amount : "+ transfer_amount + "\n";
					bank.setTrans(s);
					String s1 = bank1.getTrans() + "Transferred from  :" + accNo +" Amount : "+ transfer_amount + "\n";
					bank1.setTrans(s1);
					dao.InsertData(accNo, bank);
					dao.InsertData(accNo1, bank1);
				} else {
					throw new LowBalanceException();
				}
			}

			else {
				throw new AccountNotFoundException();
			}
		} else {
			throw new AccountNotFoundException();
		}

		return true;
	}

	// to validateBalance

	public boolean validateBalance(long accNo, int amount) throws LowBalanceException

	{
		try {
			res = dao.checkAccount(accNo);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		if (bank == null) {
			throw new LowBalanceException();
		} else {
			return true;
		}
	}

	// to set transactions
	
	public String setTrans(long accNo) throws AccountNotFoundException {
		
		try {
			res = dao.checkAccount(accNo);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		String s;
		
		if (bank == null) {
			throw new AccountNotFoundException();
		} else {
			s = bank.getTrans();
		}
		return s;
	}
}
