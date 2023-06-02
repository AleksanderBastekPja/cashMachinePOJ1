interface CashMachineInterface {
    double getAvailableCash();

    void setAvailableCash(double availableCash);

    boolean isBlocked();

    void setBlocked(boolean blocked);

    int withdrawCash();

    void withdrawAmountOfCash(double cashAmount);
}
