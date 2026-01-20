 class BankAccount{
    private int balance = 0;

    public synchronized void deposit(int amount){
        balance += amount;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
    }

    public synchronized void withdraw(int amount){
        if(balance >= amount){
            balance -= amount;
            System.out.println("withdrawn: " + amount + ", New Balance: " + balance);
        }
        else{
            System.out.println("Insufficient balance for withdrawal of: " + amount);
        }
    }

    public int getBalance(){
        return balance;
    }
}

public class Synchronized{
    public static void main(String[]args){
        BankAccount a = new BankAccount();

            // thread1 to deposit money into the account

            Thread t1 = new Thread(() -> {
                for(int i = 0; i < 3; i++){
                    a.deposit(100);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            // thread2 to withdraw money from the account

            Thread t2 = new Thread(() -> {
                for(int i = 0; i < 3; i++){
                    a.withdraw(50);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Final Balance: " + a.getBalance());
        }
    }
