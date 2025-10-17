public class BankAccount{
    public enum AccountType{
        CURRENT, SAVINGS
    }
    private AccountType acctType;
    private String acctID;
    private double balance;
    private int numWithdrawals;
    private boolean inTheRed;
    private double minBalance;

    //the constants
    private static final double CURRENT_ACCT_MIN_BALANCE=300.0;
    private static final double SAVINGS_ACCT_MIN_BALANCE=200.0;

    private double interestRate; // annual interest rate 
    private double maintenanceFee;
    private int withdrawalLimit;

    private static final double SAVINGS_ACCT_INTEREST_RATE=0.05;
    private static final double CURRENT_ACCT_MAINTENANCE_FEE=10.0;
    private static final double SAVINGS_WITHDRAWAL_LIMIT=2;

    //The first constructor
    public BankAccount (AccountType type, String id ){
        this.acctType=type;
        this.acctID=id;
        this.balance=0;
        this.numWithdrawals=0;
        if(type==AccountType.CURRENT){
            this.minBalance=CURRENT_ACCT_MIN_BALANCE;
            this.interestRate=0;
            this.maintenanceFee=CURRENT_ACCT_MAINTENANCE_FEE;
            this.withdrawalLimit=-1;
        }
        else{
            this.minBalance=SAVINGS_ACCT_MIN_BALANCE;
            this.interestRate=SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee=0;
            this.withdrawalLimit=(int) SAVINGS_WITHDRAWAL_LIMIT;
        }
        //In the red status
            this.inTheRed=(this.balance<this.minBalance);
    }
    //Constructor A
    public BankAccount(AccountType type, String id, double openingBalance){
        this.acctType=type;
        this.acctID=id;
        this.balance=openingBalance;
        this.numWithdrawals=0;

        if(type==AccountType.CURRENT){
            this.minBalance=CURRENT_ACCT_MIN_BALANCE;
            this.interestRate=0;
            this.maintenanceFee=CURRENT_ACCT_MAINTENANCE_FEE;
            this.withdrawalLimit=-1;
        }
        else{
            this.minBalance=SAVINGS_ACCT_MIN_BALANCE;
            this.interestRate=SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee=0;
            this.withdrawalLimit=(int) SAVINGS_WITHDRAWAL_LIMIT;
        }
        //In the red status
            this.inTheRed=(this.balance<this.minBalance);
        
        }
    //Method B
    public double getBalance(){
        return this.balance;
    }
    //Method C
    public AccountType getAccountType(){
        return this.acctType;
    }
    //Method D
    public String getAccountID(){
        return this.acctID;
    }
    //Method E
    public double getMinBalance(){
        return this.minBalance;
    }
    //Method F
    public boolean withdraw(double amount){
        // checking the amount validity
        if (amount<=0){
            System.out.println("Invalid amount");
            return false;
        }
        //checking whether it is in red
        if(inTheRed){
            System.out.println("Sorry, could not perform withdrawal: Insufficient balance");
            return  false;
        }

        //checking for the withdrawals limit
        if(withdrawalLimit !=-1 && numWithdrawals>=withdrawalLimit){
            System.out.println("Sorry. You Exceeded Withdrawals Limit");
            return false;
        }
        
        //checking for if the balance is sufficient
        if (balance-amount < minBalance){
            System.out.println("Sorry, could not perform withdrawal: Insufficient balance");
            return false;
        }

        balance-=amount;
        numWithdrawals++;
        System.out.println("Money withdrawn successfully. New balance:" + balance);
        inTheRed=(balance<minBalance);
        return true;
    }

    //Method G
    public void deposit(double amount){
        if( amount<=0){
            System.out.println("Invalid Amount");
            return; // stopping the process
        }
        balance+=amount;

        inTheRed=(balance<minBalance);
        System.out.println("Funds deposited successfully. New balance:" + balance);
    }
    //Method H
    public void performMonthlyMaintenance(){
        
        double earnedInterest=balance * (interestRate/12);
        balance+= earnedInterest;
        balance-=maintenanceFee;
        inTheRed=(balance<minBalance);

        numWithdrawals=0;

        System.out.println("Earned Interest: <" + earnedInterest + ">");
        System.out.println("Maintenance fee: <" + maintenanceFee + ">");
        System.out.println("Updated balance: <" + balance + ">");

        if (inTheRed){
            System.out.println("WARNING: This account is in the red!");
        }
     }

    //Method I
    public boolean transfer(boolean transferTo, BankAccount otherAccount, double transAmount ){
        if (transferTo){
            //from this account to otherAccount
            if (this.withdraw(transAmount)){
                otherAccount.deposit(transAmount);
                return true;
            }
            else{
                return false;
            }
        }
         else{
            //from otherAccount to this account
            if(otherAccount.withdraw(transAmount)){
                this.deposit(transAmount);
                return true;
            }
            else{
                return false;
            }
        }
    }
}

