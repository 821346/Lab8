import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class BankDataProcessing {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
	static ArrayList<BankAccount> Accounts = new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			Path path = Paths.get("C:\\Users\\StephanieHayden\\Downloads\\Coding Projects\\src/AccountData.csv");
			readAccounts(path, true);

			path = Paths.get("C:\\Users\\StephanieHayden\\Downloads\\Coding Projects\\src/BankData.csv");
			readTransactions(path, true, Accounts);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void readAccounts(Path Xpath, boolean bhead) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(Xpath);

        try {
                bufferedReader.readLine();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                        Accounts.add(new BankAccount(line.split(",")[0], Integer.parseInt(line.split(",")[1])));
                }
        } catch (IOException e) {
                bufferedReader.close();
                e.printStackTrace();
        } finally {
                bufferedReader.close();
        }
}
	private static void readTransactions(Path Xpath, boolean bHead, ArrayList<BankAccount> Xaccounts) 
	throws IOException  {

        for (int i = 0; i < Accounts.size(); i++) {
                System.out.println("Account for " + Accounts.get(i).getAcctname() + ", " + Accounts.get(i).getAcctnum());
                BufferedReader bufferedReader = Files.newBufferedReader(Xpath);
                try {
                        bufferedReader.readLine();
                        String line;
                        double balance = 0;
                        while ((line = bufferedReader.readLine()) != null) {
                                int acctnum = Integer.parseInt(line.split(",")[0]);
                                formatter = null;
                                double amnt = 0;
                                String type = null;
                                if (acctnum == Accounts.get(i).getAcctnum()) {
                                        TemporalAccessor date = formatter.parse(line.split(",")[1]);
                                        amnt = Double.parseDouble(line.split(",")[3]);
                                        type = "Credit";
                                        if (line.split(",")[2].equals("d")) {
                                                type = "Debit";
                                                balance -= amnt;
                                        } else {
                                                balance += amnt;
                                        }
                                        System.out.println(" Transaction date: " + date + ", " + type + "= " + amnt);
                                }
                        }
                        Accounts.get(i).setBalance(balance);
                        System.out.println(Accounts.get(i).toString());
                        System.out.println("------------------------------------------------------------------------");
                } catch (IOException ioe) {
                        bufferedReader.close();
                        ioe.printStackTrace();
                } finally {
                        bufferedReader.close();
                }
        }
}


}
