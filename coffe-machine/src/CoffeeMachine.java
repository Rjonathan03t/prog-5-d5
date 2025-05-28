import java.util.*;
import java.util.Scanner;

public class CoffeeMachine {
    private double balance = 0.0;
    private List<Coffee> menu = new ArrayList<>();
    private Coffee selectedCoffee = null;

    public void addCoffee(Coffee coffee) {
        menu.add(coffee);
    }

    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);
        if (amount <= 0) {
            System.out.println("Please insert a valid amount.");
            return;
        }
        balance += amount;
        System.out.println("Current balance: " + balance);
        displayMenu();
        System.out.print("Please enter the name of the coffee you want: ");
        String userChoice = scanner.nextLine();
        choose(userChoice);
    }

    public void choose(String type) {
        for (Coffee coffee : menu) {
            if (coffee.getType().equalsIgnoreCase(type)) {
                if (coffee.getQuantity() <= 0) {
                    System.out.println("Out of stock.");
                    return;
                }
                if (balance < coffee.getPrice()) {
                    System.out.println("Insufficient funds.");
                    return;
                }
                selectedCoffee = coffee;
                System.out.println(type + " selected.");
                return;
            }
        }
        System.out.println("Invalid selection.");
    }

    public Coffee getCoffee() {
        if (selectedCoffee == null) {
            System.out.println("No coffee selected.");
            return null;
        }
        if (balance < selectedCoffee.getPrice()) {
            System.out.println("Insufficient funds.");
            return null;
        }
        if (selectedCoffee.getQuantity() <= 0) {
            System.out.println("Out of stock.");
            return null;
        }
        selectedCoffee.prepare();
        balance -= selectedCoffee.getPrice();
        System.out.println("Dispensing " + selectedCoffee.getType() + ".");
        Coffee served = selectedCoffee;
        selectedCoffee = null;
        return served;
    }

    private void displayMenu() {
        System.out.println("Available coffees:");
        for (Coffee coffee : menu) {
            System.out.println("- " + coffee.getType() + " (€" + coffee.getPrice() + ") [" + coffee.getQuantity() + " left]");
        }
    }
}