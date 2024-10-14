
package com.mycompany.ecommerceproject;

import java.util.ArrayList;
import java.util.Scanner;


public class user {

    static Scanner input = new Scanner(System.in);
    ArrayList<customer> Customer = new ArrayList<>();
    ArrayList<admin> admins = new ArrayList<>();
    customer customers;
    admin Admin;

    public user() {
    }
    private String username;
    private String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public user(int id, String username, String password, String email) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void register() {
        System.out.println("Enter username");
        username = input.nextLine();
        System.out.println("Enter Password");
        password = input.nextLine();

        customer newCustomer = new customer(username, password);
        Customer.add(newCustomer);
    }

    public void adminlogin(inventory Inventory) {
        Admin = new admin("acp", "acp");
        admins.add(Admin);
        boolean match_check = false;
        System.out.println("Enter Username");
        username = input.next();
        System.out.println("Enter Password");
        password = input.next();
        for (int i = 0; i < admins.size(); i++) {
            admin admin = admins.get(i);
            System.out.println("e");
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                match_check = true;
                break;
            }
        }
        if (match_check == true) {
            System.out.println("Login Succesfully");
            Admin.displaymenu(Inventory);
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public void login(inventory Inventory) {
        boolean match_check = false;
        try{
        System.out.println("Enter Username");
        username = input.next();
        System.out.println("Enter Password");
        password = input.next();
        for (int i = 0; i < Customer.size(); i++) {
            customers = Customer.get(i);
            if (customers.getUsername().equals(username) && customers.getPassword().equals(password)) {
                match_check = true;
                break;
            }
        }
        if (match_check == true) {
            System.out.println("Login Succesfully");
            customers.displaymenu(Inventory);
        } else {
            System.out.println("Invalid username or password");
        }
        }
            catch(Exception ex){
                System.out.println("Error "+ex);
            }
    }

}
