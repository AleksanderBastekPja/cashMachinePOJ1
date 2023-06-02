import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CashMachineService implements CashMachineInterface {
    private double availableCash;

    private boolean blocked = false;

    public CashMachineService(double availableCash) {
        this.availableCash = availableCash;
    }

    @Override
    public double getAvailableCash() {
        return availableCash;
    }

    @Override
    public void setAvailableCash(double availableCash) {
        this.availableCash = availableCash;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public int withdrawCash() {
        if (isBlocked()) {
            System.out.println("Sorry, cash machine is blocked. Safe is empty.");
            return 0;
        };
        if (availableCash <= 0) setBlocked(true);
        System.out.println("How much money do you want to withdraw? (input the number)");
        System.out.printf("State of cash machine safe %f%n", getAvailableCash());
        System.out.println("1) 100$");
        System.out.println("2) 50$");
        System.out.println("3) 20$");
        System.out.println("4) quit");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {
            String option = bufferRead.readLine();

            switch (Integer.parseInt(option)) {
                case 1 -> withdrawAmountOfCash(100);
                case 2 -> withdrawAmountOfCash(50);
                case 3 -> withdrawAmountOfCash(20);
                default -> {
                    System.out.println("Good bye");
                    return 0;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return Response.OK;
    }

    @Override
    public void withdrawAmountOfCash(double cashAmount) {
        double actualAvailableCash = getAvailableCash();
        if (cashAmount > actualAvailableCash) {
            System.out.println("There is not enough money in safe to withdraw selected amount. Select other option with lower amount.");
            return;
        }
        setAvailableCash(actualAvailableCash - cashAmount);
        System.out.println("You withdraw your money successfully! Thank you for using our Service.");
    }
}
