import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Customer extends User implements Orderable {
    private String custName;
    private String email;
    private String phoneNumber;
    private String birthDay;
    private ArrayList<MenuItem> cart;
    private ArrayList<Order> orderHistory;

    public Customer(String custName, String email, String phoneNumber, String birthDay) {
        this.userType = 1;
        //generates a unique identifier
        this.userID = UUID.randomUUID().toString();
        this.custName = custName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    @Override
    public void register() {
        // appends to file
        try (FileWriter writer = new FileWriter("CustomerDetail.txt", true)) {
            writer.write(userID + ","+ userName + "," + password + "," + custName + "," + email + "," + phoneNumber + "," + birthDay + "\n");
            System.out.println("Customer Registration Successful!");
            System.out.println();
        } 
        catch (IOException e) {
            System.out.println("An error occurred during registration.");
            e.printStackTrace();
        }
    }

    public void addToCart(String itemId, String restaurantName) {
        try (Scanner scanner = new Scanner(new File(restaurantName + ".txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");
                if (details[0].equals(itemId)) {
                    MenuItem item = new MenuItem(details[0], details[1], details[2], Double.parseDouble(details[3]));
                    cart.add(item);
                    System.out.println("Added to cart");
                    return;
                }
            }
            System.out.println("Item ID not found.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MenuItem> viewCart() {
        return cart;
    }

    public void removeFromCart(String itemId) {
        boolean itemRemoved = cart.removeIf(item -> item.getItemID().equals(itemId));
        if (itemRemoved) {
            System.out.println("Removed item with ID: " + itemId);
        } else {
            System.out.println("Item ID not found in cart.");
        }
    }

    public ArrayList<Order> viewOrderHistory() {
        return orderHistory;
    }

    public void placeOrder() {
        Order newOrder = new Order(new ArrayList<>(cart));
        orderHistory.add(newOrder);
        cart.clear();
    }

    public void browseMenu(String restaurantName) {
        File file = new File(restaurantName + ".txt");
        if (!file.exists()) {
            System.out.println("Restaurant menu file not found!");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            System.out.println("Menu for " + restaurantName + ":");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] menuDetails = line.split(",");
                if (menuDetails.length == 4) {
                    System.out.println("Item ID: " + menuDetails[0] + ", Item Name: " + menuDetails[1] + 
                                       ", Description: " + menuDetails[2] + ", Item Price: " + menuDetails[3]);
                } 
                else {
                    System.out.println("Invalid menu entry: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the menu");
            e.printStackTrace();
        }
    }
}