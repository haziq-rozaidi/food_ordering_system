// Main Class
import java.io.*;
import java.util.Scanner;

public class OPfood{
    public static void main(String[] args) {
        System.out.println("WELCOME TO OP FOOD ORDERING SYSTEM\n");
        try(Scanner scanner = new Scanner(System.in)){

            System.out.print("Enter User Type (1 : Customer, 2 : Restaurant): ");
            int userType = scanner.nextInt();

            if (userType == 1) {

                System.out.print("Enter Operation (1 : Register, 2 : Login): ");
                int operation = scanner.nextInt();

                // Customer registration
                if(operation == 1){
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
                    // return to System.out.print("Enter Operation (1 : Register, 2 : Login): ");
                }

                // Customer login
                else if (operation == 2){
                    System.out.print("Enter Username for login: ");
                    String loginUserName = scanner.nextLine();
                    System.out.print("Enter Password for login: ");
                    String loginPassword = scanner.nextLine();
                    Customer customer = new Customer(null, null, null, null);
                    customer.login(userType, loginUserName, loginPassword);

        
                    if (customer.login(userType, loginUserName, loginPassword)){
                        // Choose restaurant
                        System.out.println("Choose a Restaurant");
                        System.out.println("1. thaib");
                        System.out.println("2. pluto");

                        System.out.print("Enter the number of the restaurant: ");
                        int restaurantChoice = scanner.nextInt();

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

                        System.out.print("Enter Action (1 : Add item to cart , 2 : View Cart , 3 : View Order History, 4 : Logout): ");
                        int action = scanner.nextInt();

                        if (action == 1){
                            customer.browseMenu(restaurantName);
                            customer.addToCart(item);
                            // return to System.out.print("Enter Action (1 : Add item to cart , 2 : View Cart , 3 : View Order History): "); 
                        }
                
                        else if (action == 2){
                            customer.viewCart();

                            System.out.print("Enter cart action (1 : Delete cart item ,  2 : Place Order): ");
                            int cartAction = scanner.nextInt();

                            if (cartAction == 1){
                                customer.removeFromCart(item);
                                // return to customer.viewCart();
                            }

                            else if(cartAction == 2){
                                customer.placeOrder();
                                // return to System.out.print("Enter Action (1 : Add item to cart , 2 : View Cart , 3 : View Order History): ");
                            }   

                            else{
                                System.out.println("Invalid cart action input");
                                // return to System.out.print("Enter Action (1 : Add item to cart , 2 : View Cart , 3 : View Order History): ");
                            }   
                        }

                        else if (action == 3){
                            for (Order order : customer.viewOrderHistory()) {
                                for (MenuItem item : order.getItems()) {
                                    System.out.println("Ordered item: " + item.getItemName() + ", Price: " + item.getPrice());
                                }
                            }
                            // return to System.out.print("Enter Action (1 : Add item to cart , 2 : View Cart , 3 : View Order History): ");
                        }

                        else if (action == 4){
                            System.out.println("Logging out");
                            // return to System.out.print("Enter User Type (1 : Customer, 2 : Restaurant): ");
                        }

                        else {
                            System.out.println("Invalid action input");
                            // return to System.out.print("Enter Action (1 : Add item to cart , 2 : View Cart , 3 : View Order History, 4 : Logout): ");
                        }   
                    }

                    else {
                        // return to System.out.print("Enter Operation (1 : Register, 2 : Login): ");
                    }
                } 
            }
            
            
            else if (userType == 2) {
                System.out.print("Enter Operation (1 : Register, 2 : Login): ");
                int operation = scanner.nextInt();

                // Restaurant registration
                if(operation == 1){
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
                    // return to System.out.print("Enter Operation (1 : Register, 2 : Login): ")
                }

                // Restaurant login
                else if (operation == 2){
                    System.out.print("Enter Username for login: ");
                    String loginUserName = scanner.nextLine();
                    System.out.print("Enter Password for login: ");
                    String loginPassword = scanner.nextLine();
                    Restaurant restaurant = new Restaurant(null, null);
                    
                    if (restaurant.login(userType, loginUserName, loginPassword)) {
                        String restaurantName;
                        File file = new File("RestaurantDetail.txt");
                        scanner = new Scanner(file);
                
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            String[] details = line.split(",");
                            if (details[1].equals(loginUserName) && details[2].equals(loginPassword)) {
                                restaurantName = details[3];
                            }
                        }
                        
                        System.out.println("Enter Action (1 : Add menu , 2 : Update menu , 3 : Delete menu, 4 : Browse Menu):");
                        int action = scanner.nextInt();

                        if (action == 1){
                            restaurant.browseMenu(restaurantName);
                            restaurant.addMenu();
                            // return to System.out.println("Enter Action (1 : Add menu , 2 : Update menu , 3 : Delete menu):");
                        }

                        else if (action == 2){
                            restaurant.browseMenu(restaurantName);
                            System.out.println("Enter item ID to be updated:");
                            String itemID = scanner.nextLine();
                            restaurant.updateMenu(itemID);
                            // return to System.out.println("Enter Action (1 : Add menu , 2 : Update menu , 3 : Delete menu):");
                        }

                        else if (action == 3){
                            restaurant.browseMenu(restaurantName);
                            System.out.println("Enter item ID to be deleted:");
                            String itemID = scanner.nextLine();
                            restaurant.deleteMenu(itemID);
                            // return to System.out.println("Enter Action (1 : Add menu , 2 : Update menu , 3 : Delete menu):");
                        }

                        else if (action == 4){
                            restaurant.browseMenu(restaurantName);
                        }

                        else{
                            System.out.println("Invalid action input");
                            // return to System.out.println("Enter Action (1 : Add menu , 2 : Update menu , 3 : Delete menu):");
                        }
                    }
                }

                    else {
                        // return to System.out.print("Enter Operation (1 : Register, 2 : Login): ")
                    }
                } 

                else{
                    System.out.println("Invalid User type entered");
                    // return to System.out.print("Enter User Type (1 : Customer, 2 : Restaurant): ");
                }
            }
            
            catch (Exception e) {
            e.printStackTrace();
            }
    }
}

