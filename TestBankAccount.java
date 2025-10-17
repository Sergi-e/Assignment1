public class TestBankAccount {
    public static void main(String[] args) {
        //Creating the objects for both  accounts
        BankAccount savObj=new BankAccount(BankAccount.AccountType.SAVINGS,"Sav101");
        BankAccount currObj=new BankAccount(BankAccount.AccountType.CURRENT, "Curr202");

        //The  get methods 
        System.out.println("Here is the current ID:" +currObj.getAccountID());
        System.out.println("Account Type:"+ savObj.getAccountType());
        System.out.println("The current balance:" +currObj.getBalance());
        System.out.println("Minimum balance for savings:" +savObj.getMinBalance());

        //deposit method testing
        savObj.deposit(1000.0);

        //withdrawing  method testing
        currObj.withdraw(5000.0);
        savObj.withdraw(200.0);

        // the method for monthly maintenance
        savObj. performMonthlyMaintenance();
        currObj.performMonthlyMaintenance();

        //Testing the transfer method
        //from current to savings
        currObj.transfer(true, savObj, 300);
        //from savings to current
        currObj.transfer(false, savObj, 100); 

        //The balance at the end
        System.out.println("The Current balance:" + currObj.getBalance());
        System.out.println("The Savings balance:" +savObj.getBalance());
    }
}