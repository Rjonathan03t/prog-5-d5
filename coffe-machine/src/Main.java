public class Main {
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        machine.addCoffee(new Coffee("Espresso", 1.5, 5));
        machine.addCoffee(new Coffee("Latte", 2.0, 3));

        machine.pay(2.0);
        machine.promptUserForCoffeeSelection();
        Coffee coffee = machine.getCoffee();
        if (coffee != null) {
            System.out.println("Enjoy your " + coffee.getType() + "!");
        }
    }
}