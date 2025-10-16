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
    private static final double CURRENT_ACCT_MIN_BALANCE;
    private static final double SAVINGS_ACCT_MIN_BALANCE;

    private double interestRate;
    private double maintenanceFee;
    private int withdrawalLimit;

    private static final double SAVINGS_ACCT_INTEREST_RATE=0.05;
    private static final double CURRENT_ACCT_MAINTENANCE_FEE;
    private static final double SAVINGS_WITHDRAWAL_LIMIT=2;

    //Constructors
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
    public double getMiniBalance(){
        return this.minBalance;
    }
    //Method F
    public boolean withdraw(double amount){
        // checking the amount validity
        if (amount<=0){
            System.out.print("Invalid amount");
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
            System.out.print("Sorry, could not perform withdrawal: Insufficient balance");
            return false;
        }

        balance-=amount;
        numWithdrawals++;
        System.out.println("Money withdrawn sucessfully");
        return true;
    }

    //Method G
    public void deposit(double amount){
        balance+=amount;
    }
    //Method H
    public void performMonthlyMaintenance(){

    }
    //Method I
    public boolean transfer(){

    }
}
