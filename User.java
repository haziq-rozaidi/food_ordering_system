import java.io.*;
import java.util.Scanner;

public abstract class User {
    protected int userType;
    protected String userID;
    protected String userName;
    protected String password;

    public boolean login(int userType, String userName, String password) {
        Scanner sc = null;
        try {
            if (userType == 1) {
                File file = new File("CustomerDetail.txt");
                sc = new Scanner(file);
                
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] details = line.split(",");
                    if (details[1].equals(userName) && details[2].equals(password)) {
                        System.out.println("Customer login successful!");
                        return true;
                    }
                }
                System.out.println("Customer login failed!");
            } 
            
            else if (userType == 2) {
                File file = new File("RestaurantDetail.txt");
                sc = new Scanner(file);
                
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] details = line.split(",");
                    if (details[1].equals(userName) && details[2].equals(password)) {
                        System.out.println("Restaurant login successful!");
                        return true;
                    }
                }
                System.out.println("Restaurant login failed!");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Login File not found: " + e.getMessage());
        } 
        
        finally {
            if (sc != null) {
                sc.close();
            }
        }
        return false;
    }

    public abstract void register();
}
