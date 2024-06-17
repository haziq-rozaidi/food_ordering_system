import java.util.ArrayList;

public class Order{
    private String orderID;
    private String orderStatus;
    private double totalAmount;
    private ArrayList<MenuItem> items;

    public Order(ArrayList<MenuItem> items) {
        this.orderID = generateOrderID();
        this.orderStatus = "";
        this.items = items;
        this.totalAmount = calculateTotalAmount(items);
    }

    private String generateOrderID() {
        return "OD" + System.currentTimeMillis();
    }

    public double calculateTotalAmount(ArrayList<MenuItem> items) {
        double total = 0.00;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void placeOrder() {
        this.orderStatus = "Placed Successfully";
    }

    public void cancelOrder() {
        this.orderStatus = "Cancelled Successfully";
    }
}

