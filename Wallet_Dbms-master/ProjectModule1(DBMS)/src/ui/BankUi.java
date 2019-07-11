package ui;

import java.sql.SQLException;
import java.util.Scanner;

import exceptions.*;
import service.BankService;
import service.BankServiceI;



public class BankUi {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws AccountNotFoundException, ClassNotFoundException, SQLException {

		// variables

		long accNo, accNo1;
		int withdraw_amount, deposit_amount = 0, transfer_amount = 0;
		int pin;
		int amount = 0;
		int balance = 0;
		boolean res = false;
		String cont = "yes";

		BankServiceI service =  new BankService();


		// To ask choice from users and perform operations
		while (cont.equalsIgnoreCase("yes")) {
			switch (menu()) {

			// to create account

			case 1:

				System.out.println("Enter name");
				String name = sc.nextLine();
				name += sc.nextLine();

				String regexUserName = "[A-Za-z\\s]+$";
				while (!name.matches(regexUserName)) {

					System.out.println("Invalid Format");
					System.out.println("Enter name");
					name = sc.next();
				}

				System.out.println("Enter address ");
				String add = sc.next();
				add += sc.nextLine();

				while (!add.matches(regexUserName)) {
					System.out.println("Invalid Format");
					System.out.println("Enter address ");
					add = sc.nextLine();

				}

				System.out.println("Enter phone number");
				String phone = sc.next();

				while (phone.length() < 10 || phone.length()> 10) {

					System.out.println("Phone number should be 0f 10 digits");
					System.out.println("Enter phone number");
					phone = sc.next();
				}

				accNo = Long.parseLong(phone) - 10000;

				System.out.println("Enter Pin");
				pin = sc.nextInt();

				while (String.valueOf(pin).length() < 4 || String.valueOf(pin).length() > 4) {

					System.out.println("Pin number should be 0f 4 digits");
					System.out.println("Enter Pin");
					pin = sc.nextInt();
				}

				System.out.println("Enter Balance");
				int bal = sc.nextInt();

				while (bal < 1000) {
					System.out.println("Minimum Balance should be 1000");
					System.out.println("Enter Balance");
					bal = sc.nextInt();

				}
				try {
					res = service.createAccount(name, add, accNo, phone, pin, bal);
				} catch (AccountAlreadyExistsException e) {

					System.out.println(e);
					break;
				}

				if (res == true) {
					System.out.println("Account Created Successfully !!!");
					System.out.println("Account Number : " + accNo);

				} else {

					System.out.println("Cannot Create Account");
				}

				break;

				// to show balance
			case 2:

				System.out.println("Enter account number");
				accNo = sc.nextLong();


				try {
					balance = service.showBalance(accNo);
				} catch (AccountNotFoundException e) {
					System.out.println(e);
					break;
				}

				System.out.println("Balance :" + balance);

				break;

				// to deposit

			case 3:

				System.out.println("Enter account no");
				accNo = sc.nextLong();

				System.out.println("Enter amount to be deposited");
				deposit_amount = sc.nextInt();

				try {
					amount = service.deposit(accNo, deposit_amount);
					
					balance = service.showBalance(accNo);
				}

				catch (AccountNotFoundException e) {

					System.out.println(e);
					break;
				}

				System.out.println("Amount Deposited : " + deposit_amount);
				System.out.println("Updated Balance : " + balance);
				

				break;

				// to withdraw

			case 4:

				System.out.println("Enter account no");
				accNo = sc.nextLong();

				System.out.println("Enter amount to withdraw");
				withdraw_amount = sc.nextInt();

				try {
					amount = service.withdraw(accNo, withdraw_amount);
					res = service.validateBalance(accNo, withdraw_amount);
					balance = service.showBalance(accNo);

				} catch (AccountNotFoundException e) {

					System.out.println(e);
					break;

				} catch (LowBalanceException e) {
					
					System.out.println(e);
					break;
				}

				System.out.println("Amount Withdrawn : " + withdraw_amount);
				System.out.println("Updated Balance : " + balance);

				break;

				// to transfer fund

			case 5:

				int senders_balance = 0;
				int recievers_balance = 0;

				System.out.println("Enter account no");
				accNo = sc.nextLong();

				System.out.println("Enter account to which you want to transfer fund");
				accNo1 = sc.nextLong();

				System.out.println("Enter amount to transfer");
				transfer_amount = sc.nextInt();

				try {
					res = service.validateBalance(accNo, transfer_amount);
					res = service.transferfund(accNo, accNo1, transfer_amount);

					senders_balance = service.showBalance(accNo);
					recievers_balance = service.showBalance(accNo1);

				} catch (AccountNotFoundException e) {

					System.out.println(e);
					break;
				} catch (LowBalanceException e) {
					System.out.println(e);
					break;
				}

				System.out.println("Amount transferred Successfully");
				System.out.println("Updated balance for Account " + accNo + " : " + senders_balance);
				System.out.println("Updated balance for Account " + accNo1 + " : " + recievers_balance);

				break;

				// to show transactions
			case 6:
				
				String s = null;
				  System.out.println("Enter account number"); accNo = sc.nextLong();
				  System.out.println("Enter pin"); pin = sc.nextInt(); 
				  try {
				   s= service.setTrans(accNo);
				   System.out.println(s);
				  }catch(AccountNotFoundException e )
				  {
					  System.out.println(e);
					  break;
				  }
				 
		
				  // to exit
			case 7:
				System.exit(0);
				cont = "no";
				break;
			default:
				System.out.println("Please Enter choice between 1 - 7 ");
				menu();

			}
		}

	}

	// to display menu
	public static int menu() {

		System.out.println("------------Welcome to Capgemini Bank----------");
		System.out.println("Press 1 to Create Account");
		System.out.println("Press 2 to Show Balance");
		System.out.println("Press 3 to Deposit");
		System.out.println("Press 4 to Withdraw");
		System.out.println("Press 5 to Transer Fund");
		System.out.println("Press 6 to Print Transcations");
		System.out.println("Press 7 to Exit");

		System.out.println("Enter Choice");
		int choice = sc.nextInt();

		return choice;
	}

}
