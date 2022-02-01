public class BankAccount {
	private String name;
	private double balance;
	private int acctnum;
	public BankAccount(String Xname, int Xacct) {
		this.name = Xname;
		this.acctnum = Xacct;
	}
	public void setBalance(double Xbalance) {
		this.balance = Xbalance;
	}
	public String getAcctname() {
		return this.name;
	}
	public int getAcctnum(){
		return this.acctnum;
	}
	
@Override
	public String toString() {
		return this.name+", has balance: $"+this.balance;
	}
}
