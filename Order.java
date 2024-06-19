import java.util.ArrayList;

public class Order{
    private String orderID;
    private String orderStatus;
    private String restaurantName;
    private ArrayList<MenuItem> items;
    private static ArrayList<Order> orders = new ArrayList<>();

    private String generateOrderID() {
        return "OD" + System.currentTimeMillis();
    }

    public Order(ArrayList<MenuItem> items, String restaurantName) {
        this.orderID = generateOrderID();
        this.restaurantName = restaurantName;
        this.orderStatus = "";
        this.items = items;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public double calculateTotalAmount() {
        double total = 0.00;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void placeOrder() {
        this.orderStatus = "Order placed successfully!";
    }

    public void noOrder() {
        this.orderStatus = "No order placed successfully!";
    }
}

