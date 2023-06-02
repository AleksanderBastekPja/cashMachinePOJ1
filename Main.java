public class Main {
    public static void main(String[] args) {
        Account account = new Account("myCard1", 1236, 0);
        CashMachineTerminal cashMachineTerminal = new CashMachineTerminal(1000, account);
        cashMachineTerminal.terminal();
    }
}