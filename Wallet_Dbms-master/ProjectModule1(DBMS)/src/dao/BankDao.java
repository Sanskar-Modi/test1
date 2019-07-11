package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.plaf.synth.SynthScrollBarUI;

import bean.BankBean;

public class BankDao implements BankDaoI {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean checkAccount(long accNo) throws ClassNotFoundException, SQLException {
		
		boolean res = false;
		
		String query = "Select * from bank where accountno = ?";
		con = BankDB.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, accNo);
        rs = ps.executeQuery();
        
        if(rs.next())
        {
        	res = false;
        }
        else
        {
        	res = true;
        }
        return res;
	}

	@Override
	public void InsertData(long accNo, BankBean bean) throws ClassNotFoundException, SQLException {
        
		con = BankDB.getConnection();
        ps = con.prepareStatement("Insert into bank values (?,?,?,?,?,?)");
        
        ps.setString(1, bean.getName());
        ps.setLong(2, bean.getAccNo());
        ps.setLong(3,bean.getPin());
        ps.setString(4,bean.getAdd());
        ps.setString(5, bean.getPhone());
        ps.setInt(6, bean.getBalance());
        
        int r = ps.executeUpdate();
        
        if(r == 0)
        {
        	System.out.println("Not inserted");
        }
        else
        {
        	System.out.println("Value inserted");
        }
	}

	@Override
	public void updateData(BankBean bean) throws ClassNotFoundException, SQLException {
		
		con = BankDB.getConnection();
		ps =con.prepareStatement("Update bank set balance = ? where accountno = ?");
		ps.setInt(1,bean.getBalance());
		ps.setLong(2,bean.getAccNo());
		
		int r = ps.executeUpdate();
		
		if(r==0)
		{
			System.out.println("Cannot update");
		}
		else
		{
			System.out.println("Updated");
		}
	}

}
