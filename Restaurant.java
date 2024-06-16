import java.io.*;
import java.util.Scanner;

public class Restaurant extends User {
    private String restaurantName;
    private String address;

    public Restaurant(String userName, String password, String restaurantName, String address) {
        this.userType = 2;
        this.userName = userName;
        this.password = password;
        this.restaurantName = restaurantName;
        this.address = address;
    }

    @Override
    public void register() {
        try (FileWriter writer = new FileWriter("RestaurantDetail.txt", true)) {
            writer.write(userType + "," + userName + "," + restaurantName + "," + password + "," + address + "\n");
            System.out.println("Restaurant registration successful!");
        } catch (IOException e) {
            System.out.println("An error occurred during registration.");
            e.printStackTrace();
        }
    }
}
