// main class
import java.util.Scanner;

public class OPfood{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter user type (1 for Customer, 2 for Restaurant): ");
            int userType = scanner.nextInt();

            if (userType == 1) {
                // Customer registration
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

                // Customer login
                System.out.print("Enter Username for login: ");
                String loginUserName = scanner.nextLine();
                System.out.print("Enter Password for login: ");
                String loginPassword = scanner.nextLine();
                customer.login(userType, loginUserName, loginPassword);

                // Choose restaurant and browse menu
                System.out.println("Choose a restaurant to browse its menu:");
                System.out.println("1. Thaib");
                System.out.println("2. Pluto");
                // add new menu for browse
                System.out.print("Enter the number of the restaurant: ");
                int restaurantChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                String restaurantName = "";
                switch (restaurantChoice) {
                    case 1:
                        restaurantName = "Thaib";
                        break;
                    case 2:
                        restaurantName = "Pluto";
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        return;
                }

                customer.browseMenu(restaurantName);

                /* Adding items to the cart and placing an order (Example)
                MenuItem item1 = new MenuItem("Pizza", 10.99);
                MenuItem item2 = new MenuItem("Burger", 8.99);
                customer.addToCart(item1);
                customer.addToCart(item2);
                customer.placeOrder();

                // Viewing order history
                for (Order order : customer.viewOrderHistory()) {
                    for (MenuItem item : order.getItems()) {
                        System.out.println("Ordered item: " + item.getItemName() + ", Price: " + item.getPrice());
                    }
                }

            } else if (userType == 2) {
                // Restaurant registration
                System.out.print("Enter Username: ");
                String userName = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();
                System.out.print("Enter Restaurant Name: ");
                String restaurantName = scanner.nextLine();
                System.out.print("Enter Address: ");
                String address = scanner.nextLine();

                Restaurant restaurant = new Restaurant(userName, password, restaurantName, address);
                restaurant.register();

                // Restaurant login
                System.out.print("Enter Username for login: ");
                String loginUserName = scanner.nextLine();
                System.out.print("Enter Password for login: ");
                String loginPassword = scanner.nextLine();
                restaurant.login(userType, loginUserName, loginPassword);

            } else {
                System.out.println("Invalid user type entered.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }*/
    }
}}}