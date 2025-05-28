public class Coffee {
    private String type;
    private double price;
    private int quantity;

    public Coffee(String type, double price, int quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void prepare() {
        if (quantity > 0) {
            quantity--;
            System.out.println("Preparing " + type + "...");
        } else {
            System.out.println("Out of stock.");
        }
    }
}