import java.util.*;
import java.util.Scanner;

public class CoffeeMachine {
    private double balance = 0.0;
    private List<Coffee> menu = new ArrayList<>();
    private Coffee selectedCoffee = null;

    public void addCoffee(Coffee coffee) {
        menu.add(coffee);
    }

    public void pay() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Please enter the amount to pay: ");
                String input = scanner.nextLine();
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    System.out.println(" Please insert a valid amount.");
                    continue;
                }
                balance += amount;
                System.out.println("Current balance: " + balance);
                displayAvailableCoffeesForBalance();
                if (!hasAvailableCoffeeForBalance()) {
                    System.out.println(" Insufficient funds for any coffee. Please insert more money or cancel.");
                    balance -= amount;                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a numeric value.");
            } catch (Exception e) {
                System.out.println("Technical Error ; " + e.getMessage());
            }
        }
    }

 
    private void displayAvailableCoffeesForBalance() {
        System.out.println("Available coffees for your balance:");
        boolean found = false;
        for (Coffee coffee : menu) {
            if (coffee.getPrice() <= balance && coffee.getQuantity() > 0) {
                System.out.println("- " + coffee.getType() + " (€" + coffee.getPrice() + ") [" + coffee.getQuantity() + " left]");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No coffees available for your current balance.");
        }
    }

    private boolean hasAvailableCoffeeForBalance() {
        for (Coffee coffee : menu) {
            if (coffee.getPrice() <= balance && coffee.getQuantity() > 0) {
                return true;
            }
        }
        return false;
    }

    public void promptUserForCoffeeSelection() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter the name of the coffee you want: ");
            String userChoice = scanner.nextLine();
            boolean result = choose(userChoice);
            if (result) break;
        }
    }

    public boolean choose(String type) {
        for (Coffee coffee : menu) {
            if (coffee.getType().equalsIgnoreCase(type)) {
                if (coffee.getQuantity() <= 0) {
                    System.out.println(" Out of stock.");
                    return false;
                }
                if (balance < coffee.getPrice()) {
                    System.out.println(" Insufficient funds.");
                    return false;
                }
                selectedCoffee = coffee;
                System.out.println(type + " selected.");
                return true;
            }
        }
        System.out.println(" Invalid selection.");
        return false;
    }

    public Coffee getCoffee() {
        if (selectedCoffee == null) {
            System.out.println(" No coffee selected.");
            return null;
        }
        if (balance < selectedCoffee.getPrice()) {
            System.out.println(" Insufficient funds.");
            return null;
        }
        if (selectedCoffee.getQuantity() <= 0) {
            System.out.println(" Out of stock.");
            return null;
        }
        try {
            selectedCoffee.prepare();
        } catch (Exception e) {
            System.out.println("Technical Error ; Failed to prepare coffee: " + e.getMessage());
            selectedCoffee = null;
            return null;
        }
        balance -= selectedCoffee.getPrice();
        System.out.println("Dispensing " + selectedCoffee.getType() + ".");
        Coffee served = selectedCoffee;
        selectedCoffee = null;
        return served;
    }
}