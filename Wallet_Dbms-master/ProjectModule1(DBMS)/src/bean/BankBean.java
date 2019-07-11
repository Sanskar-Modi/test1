package bean;

public class BankBean {
	

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public BankBean(String name2, int accNo2, int pin2, String dob2, String add2, String phone2, int bal) {
		super();
	}

	public BankBean() {
		// TODO Auto-generated constructor stub
	}

	private String name;
	private long accNo;
	private int pin;
	private String add;
	private String phone;
	private int balance;
	String trans = new String();

	public String getTrans() {
		return trans;
	}

	public void setTrans(String string) {
		trans= string;
	}

	public int getBalance() {
		return balance;
	}

	public int setBalance(int balance) {
		return this.balance = balance;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "AccountDetails name =" + name + "\n accNo =" + accNo + "\n pin =" + pin 
				+ "\n add =" + add + "\n phone =" + phone + "\nbalance =" + balance;
	}

	public void setPhone(String phone2) {
		this.phone = phone2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccNo() {
		return accNo;
	}

	public long setAccNo(long accNo2) {
		return this.accNo = accNo2;
	}


	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}




}