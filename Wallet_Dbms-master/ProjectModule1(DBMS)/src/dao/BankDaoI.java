package dao;

import java.sql.SQLException;

import bean.BankBean;

public interface BankDaoI {


	public boolean checkAccount(long accNo) throws ClassNotFoundException, SQLException;

	public void InsertData(long accNo, BankBean bean) throws ClassNotFoundException, SQLException;
	
	public void updateData(BankBean bean) throws ClassNotFoundException, SQLException;



}
