public class MenuItem{
    private String itemID;
    private String itemName;
    private String description;
    private double price;

    public MenuItem(String itemID, String itemName, String description, double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemID + ", Name: " + itemName + ", Description: " + description + ", Price: " + price;
    }
}
