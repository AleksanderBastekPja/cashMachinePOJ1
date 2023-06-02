import com.sun.jdi.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Account {
    private final String accountCard;
    private final int cardPin;
    private int counter;

    private boolean isCardBlocked = false;

    public Account(String card, int pin, int counter) {
        this.accountCard = card;
        this.cardPin = pin;
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    private void setCounter(int counter) {
        this.counter = counter;
    }

    private void setCardBlocked() {
        isCardBlocked = true;
    }

    public int logIn() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.println("Input the card. (Enter \"exit\" if you want leave)");
                String card = bufferRead.readLine();

                if (card == null || card.equals("")) {
                    System.out.println("You didn't enter the name of card!");
                }
                else if (card.equals(accountCard) && isCardBlocked) {
                    System.out.println("Your card is blocked.");
                }
                else if (card.equals(accountCard) && !isCardBlocked) {
                    System.out.println("Card is valid.");
                    break;
                }
                else if (card.equals("exit")) {
                    System.out.println("Good Bye.");
                    return Response.FAIL;
                }
                else {
                    System.out.println("Card doesn't exist in our system.");
                }
            }

            while (true) {
                System.out.println("Enter your pin. (Enter \"exit\" if you want leave)");
                String pin = bufferRead.readLine();
                if (pin == null || pin.equals("")) {
                    System.out.println("You enter empty pin. Try Again.");
                }
                else if (pin.equals("exit")) {
                    System.out.println("Good bye.");
                    return Response.FAIL;
                }
                else if (cardPin == Integer.parseInt(pin)) {
                    System.out.println("Success! Your pin is valid.");
                    setCounter(0);
                    return Response.OK;
                }
                else {
                    System.out.printf("You entered wrong pin! It's %s attempt. You have at most 3. \n", getCounter() + 1);
                    counter++;
                    if (counter >= 3) {
                        System.out.println("Card is blocked you cannot withdraw money by it.");
                        setCardBlocked();
                        return Response.INPUT_ERROR;
                    }

                }
            }

        } catch (IOException ioe) {
            System.out.println(ioe);
            return Response.FAIL;
        }
    }
}
