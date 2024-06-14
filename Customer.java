import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User implements Orderable {
    private String email;
    private String phoneNumber;
    private String birthDay;
    private ArrayList<MenuItem> cart;
    private ArrayList<Order> orderHistory;

    public Customer(String userName, String email, String phoneNumber, String birthDay) {
        this.userType = 1;
        this.userName = userName;
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
            writer.write(userName + "," + password + "," + email + "," + phoneNumber + "," + birthDay + "\n");
            System.out.println("Customer Registration Successful!");
        } 
        catch (IOException e) {
            System.out.println("An error occurred during registration.");
            e.printStackTrace();
        }
    }

    public void addToCart(MenuItem item) {
        cart.add(item);
    }

    public ArrayList<MenuItem> viewCart() {
        return cart;
    }

    public void removeFromCart(MenuItem item) {
        cart.remove(item);
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
                    System.out.println("Number: " + menuDetails[0] + ", Name: " + menuDetails[1] + 
                                       ", Description: " + menuDetails[2] + ", Price: " + menuDetails[3]);
                } 
                else {
                    System.out.println("Invalid menu entry: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the menu.");
            e.printStackTrace();
        }
    }
}