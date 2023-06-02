public class CashMachineTerminal extends CashMachineService {
    Account account;
    public CashMachineTerminal(double availableCash, Account account) {
        super(availableCash);
        this.account = account;
    }

    @Override
    public int withdrawCash() {
        return super.withdrawCash();
    }

    public void terminal() {
        int auth = Response.INPUT_ERROR;
        int isAuthValid;
        while (true) {
            while (auth != Response.OK) {
                auth = account.logIn();
                if (auth == Response.FAIL) return;
            }
            isAuthValid = withdrawCash();
            if (isAuthValid == 0) auth = Response.INPUT_ERROR;
        }
    }
}
