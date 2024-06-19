import java.io.*;
import java.util.*;

public class OPfood {
    public static void main(String[] args) {
        System.out.println("-------------------- WELCOME TO OP FOOD ORDERING SYSTEM --------------------");
        try (Scanner scanner = new Scanner(System.in)) {
            boolean runProgram = true;

            while (runProgram) {
                System.out.println();
                System.out.print("Enter User Type (1 : Customer, 2 : Restaurant): ");
                int userType = scanner.nextInt();
                scanner.nextLine(); 

                if (userType == 1) {
                    boolean runCustomer = true;

                    while (runCustomer) {
                        System.out.println();
                        System.out.print("Enter Operation (1 : Register, 2 : Login): ");
                        int operation = scanner.nextInt();
                        scanner.nextLine(); 

                        // Customer registration
                        if (operation == 1) {
                            System.out.println();
                            System.out.print("Enter Username: ");
                            String userName = scanner.nextLine();
                            System.out.print("Enter Password: ");
                            String password = scanner.nextLine();
                            System.out.print("Enter Customer Name: ");
                            String custName = scanner.nextLine();
                            System.out.print("Enter Email: ");
                            String email = scanner.nextLine();
                            System.out.print("Enter Phone Number: ");
                            String phoneNumber = scanner.nextLine();
                            System.out.print("Enter Birthday: ");
                            String birthDay = scanner.nextLine();

                            Customer customer = new Customer(custName, email, phoneNumber, birthDay);
                            customer.userName = userName;
                            customer.password = password;
                            customer.register();
                            runProgram = false; 
                        }

                        // Customer login
                        else if (operation == 2) {
                            System.out.println();
                            System.out.print("Enter Username for login: ");
                            String loginUserName = scanner.nextLine();
                            System.out.print("Enter Password for login: ");
                            String loginPassword = scanner.nextLine();
                            Customer customer = new Customer(null, null, null, null);

                            if (customer.login(userType, loginUserName, loginPassword)) {
                                // Choose restaurant
                                System.out.println();
                                System.out.println("Choose a Restaurant:");
                                System.out.println("1. thaib");
                                System.out.println("2. pluto");

                                System.out.print("Enter the number of the restaurant: ");
                                int restaurantChoice = scanner.nextInt();
                                scanner.nextLine(); 

                                String restaurantName = "";
                                switch (restaurantChoice) {
                                    case 1:
                                        restaurantName = "thaib";
                                        break;
                                    case 2:
                                        restaurantName = "pluto";
                                        break;
                                    default:
                                        System.out.println("Invalid choice!");
                                        return;
                                }

                                boolean runAction = true;
                                while (runAction) {
                                    System.out.println();
                                    System.out.print("Enter Action (1 : Add item to cart , 2 : View Cart , 3 : View Order History, 4 : Logout): ");
                                    int action = scanner.nextInt();
                                    scanner.nextLine(); 

                                    if (action == 1) {
                                        System.out.println();
                                        customer.browseMenu(restaurantName);
                                        System.out.println();
                                        System.out.print("Enter item id to add to cart: ");
                                        String item = scanner.nextLine();
                                        customer.addToCart(item, restaurantName);
                                    } 
                                    else if (action == 2) {
                                        System.out.println();
                                        customer.viewCart();

                                        boolean runCartAction = true;
                                        while (runCartAction) {
                                            System.out.println();
                                            System.out.print("Enter cart action (1 : Delete cart item , 2 : Place Order): ");
                                            int cartAction = scanner.nextInt();
                                            scanner.nextLine();

                                            if (cartAction == 1) {
                                                System.out.println();
                                                System.out.print("Enter item id to remove from cart: ");
                                                String item = scanner.nextLine();
                                                customer.removeFromCart(item);
                                            } 
                                            else if (cartAction == 2) {
                                                System.out.println();
                                                customer.placeOrder(restaurantName);
                                                runCartAction = false;
                                            } 
                                            else {
                                                System.out.println();
                                                System.out.println("Invalid cart action input");
                                            }
                                        }
                                    } 
                                    else if (action == 3) {
                                        System.out.println();
                                        for (Order order : customer.viewOrderHistory()) {
                                            System.out.println("Order ID : " + order.getOrderID());
                                            for (MenuItem item : order.getItems()) {
                                                System.out.println("Ordered item: " + item.getItemName() + ", Price: " + item.getPrice());
                                            }
                                            System.out.println("Total Order Amount: " + order.calculateTotalAmount());
                                            System.out.println();
                                        }
                                    } 
                                    else if (action == 4) {
                                        System.out.println();
                                        System.out.println("Logging out....");
                                        runAction = false;
                                        runCustomer = false;
                                    } 
                                    else {
                                        System.out.println();
                                        System.out.println("Invalid action input");
                                    }
                                }
                            } 
                            else {
                                System.out.println();
                                System.out.println("Invalid username or password");
                            }
                        } 
                        else {
                            System.out.println();
                            System.out.println("Invalid operation input");
                        }
                    }

                } else if (userType == 2) {
                    boolean runRestaurant = true;

                    while (runRestaurant) {
                        System.out.println();
                        System.out.print("Enter Operation (1 : Register, 2 : Login): ");
                        int operation = scanner.nextInt();
                        scanner.nextLine(); 

                        // Restaurant registration
                        if (operation == 1) {
                            System.out.println();
                            System.out.print("Enter Username: ");
                            String userName = scanner.nextLine();
                            System.out.print("Enter Password: ");
                            String password = scanner.nextLine();
                            System.out.print("Enter Restaurant Name: ");
                            String restaurantName = scanner.nextLine();
                            System.out.print("Enter Address: ");
                            String address = scanner.nextLine();

                            Restaurant restaurant = new Restaurant(restaurantName, address);
                            restaurant.userName = userName;
                            restaurant.password = password;
                            restaurant.register();
                        }

                        // Restaurant login
                        else if (operation == 2) {
                            System.out.println();
                            System.out.print("Enter Username for login: ");
                            String loginUserName = scanner.nextLine();
                            System.out.print("Enter Password for login: ");
                            String loginPassword = scanner.nextLine();
                            Restaurant restaurant = new Restaurant("","");

                            if (restaurant.login(userType, loginUserName, loginPassword)) {
                                String restaurantName = null;
                                String address = null;
                                File file = new File("RestaurantDetail.txt");
                                try (Scanner fileScanner = new Scanner(file)) {
                                    while (fileScanner.hasNextLine()) {
                                        String line = fileScanner.nextLine();
                                        String[] details = line.split(",");
                                        if (details[1].equals(loginUserName) && details[2].equals(loginPassword)) {
                                            restaurantName = details[3];
                                            address = details[4];
                                            restaurant = new Restaurant(restaurantName, address);
                                            break;
                                        }
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }

                                boolean runAction = true;
                                while (runAction) {
                                    System.out.println();
                                    System.out.print("Enter Action (1 : Add menu , 2 : Delete menu, 3 : Browse Menu , 4 : View Order , 5 : Logout): ");
                                    int restaurantAction = Integer.parseInt(scanner.nextLine());  

                                    if (restaurantAction == 1) {
                                        System.out.println();
                                        restaurant.browseMenu(restaurantName);
                                        System.out.println();
                                        restaurant.addMenu();  
                                    }
                                    else if (restaurantAction == 2) {
                                        System.out.println();
                                        restaurant.browseMenu(restaurantName);
                                        System.out.println();
                                        System.out.print("Enter item ID to be deleted: ");
                                        String itemID = scanner.nextLine();
                                        restaurant.deleteMenu(itemID);
                                    } 
                                    else if (restaurantAction == 3) {
                                        System.out.println();
                                        restaurant.browseMenu(restaurantName);
                                    } 
                                    //////////////////////////////// error
                                    else if (restaurantAction == 4) {
                                        System.out.println();
                                        System.out.print("Enter restaurantName: ");
                                        String restName = scanner.nextLine();
                                        restaurant.viewOrder(restName);
                                    }
                                    else if (restaurantAction == 5) {
                                        System.out.println();
                                        System.out.println("Logging out....");
                                        runAction = false;
                                        runRestaurant = false;
                                    }
                                    else {
                                        System.out.println();
                                        System.out.println("Invalid action input");
                                    }
                                }
                            } 
                            else {
                                System.out.println();
                                System.out.println("Invalid username or password");
                            }
                        } 
                        else {
                            System.out.println();
                            System.out.println("Invalid operation input");
                        }
                    }
                } 
                else {
                    System.out.println();
                    System.out.println("Invalid User type entered");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
