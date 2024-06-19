import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Restaurant extends User implements Orderable {
    private String restaurantName;
    private String address;
    private ArrayList<Order> orders;

    public Restaurant(String restaurantName, String address) {
        this.userType = 2;
        this.userID = UUID.randomUUID().toString();
        this.restaurantName = restaurantName;
        this.address = address;
        this.orders = new ArrayList<>();
    }

    @Override
    public void register() {
        try (FileWriter writer = new FileWriter("RestaurantDetail.txt", true)) {
            writer.write(userID + ","+ userName + "," + password + "," + restaurantName + "," + address + "\n");
            System.out.println("Restaurant registration successful!");
        } catch (IOException e) {
            System.out.println("An error occurred during registration.");
            e.printStackTrace();
        }
    }

    public void addMenu() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter item ID: ");
        String itemID = input.nextLine();

        System.out.print("Enter item name: ");
        String itemName = input.nextLine();

        System.out.print("Enter item description: ");
        String description = input.nextLine();

        System.out.print("Enter item price: ");
        double price = input.nextDouble();

        try (FileWriter writer = new FileWriter(this.restaurantName + ".txt", true)) {
            writer.write(itemID + "," + itemName + "," + description + "," + price + "\n");
            System.out.println("Menu item added");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the menu item");
            e.printStackTrace();
        }
        input.close();
    }

    public void updateMenu(String itemID) {
        File file = new File(this.restaurantName + ".txt");
        if (!file.exists()) {
            System.out.println("Restaurant menu file not found!");
            return;
        }

        ArrayList<String> menuLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                menuLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the menu.");
            e.printStackTrace();
            return;
        }

        boolean itemFound = false;
        for (int i = 0; i < menuLines.size(); i++) {
            String[] menuDetails = menuLines.get(i).split(",");
            if (menuDetails[0].equals(itemID)) {
                itemFound = true;
                try(Scanner input = new Scanner(System.in)){
                System.out.println("Enter new item name:");
                String newItemName = input.nextLine();
                System.out.println("Enter new description:");
                String newDescription = input.nextLine();
                System.out.println("Enter new price:");
                double newPrice = input.nextDouble();

                menuLines.set(i, itemID + "," + newItemName + "," + newDescription + "," + newPrice);
                break;}
            }
        }

        if (itemFound) {
            // try with resource to close at the end
            try (FileWriter writer = new FileWriter(this.restaurantName + ".txt")) {
                for (String line : menuLines) {
                    writer.write(line + "\n");
                }
                System.out.println("Menu item updated.");
            } catch (IOException e) {
                System.out.println("An error occurred while updating the menu.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Menu item not found with ID: " + itemID);
        }
    }


    public void deleteMenu(String itemID) {
        File inputFile = new File(this.restaurantName + ".txt");
        if (!inputFile.exists()) {
            System.out.println("Restaurant menu file not found!");
            return;
        }
    
        ArrayList<String> menuLines = new ArrayList<>();
        boolean itemRemoved = false;
    
        // Read all lines from the file
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] menuDetails = line.split(",");
                if (menuDetails[0].equals(itemID)) {
                    itemRemoved = true;
                } else {
                    menuLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the menu.");
            e.printStackTrace();
            return;
        }
    
        // If the item was found and removed, write the remaining lines back to the file
        if (itemRemoved) {
            try (FileWriter writer = new FileWriter(inputFile)) {
                for (String line : menuLines) {
                    writer.write(line + "\n");
                }
                System.out.println("Menu item removed.");
            } catch (IOException e) {
                System.out.println("An error occurred while removing the menu item.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Menu item not found with ID: " + itemID);
        }
    }


    public Order viewOrder(String orderID) {
        for (Order order : orders) {
            if (order.getOrderID().equals(orderID)) {
                return order;
            }
        }
        System.out.println("Order not found with ID: " + orderID);
        return null;
    }

    public void browseMenu(String restaurantName) {
        File file = new File(this.restaurantName + ".txt"); 
        if (!file.exists()) {
            System.out.println("Restaurant menu file not found!");
            return;
        }
    
        try (Scanner scanner = new Scanner(file)) {
            System.out.println("Menu for " + this.restaurantName + ":");
            System.out.println("Restaurant Address " + ":" + this.address);
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] menuDetails = line.split(",");
                if (menuDetails.length == 4) {
                    System.out.println("Item ID: " + menuDetails[0] + ", Item Name: " + menuDetails[1] + 
                                       ", Description: " + menuDetails[2] + ", Item Price: " + menuDetails[3]);
                } else {
                    System.out.println("Invalid menu entry: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the menu.");
            e.printStackTrace();
        }
    }

}
