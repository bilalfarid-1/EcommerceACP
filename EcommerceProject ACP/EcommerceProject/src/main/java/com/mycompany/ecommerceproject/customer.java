
package com.mycompany.ecommerceproject;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class customer extends user {
    private product Product = new product();
    static Scanner input = new Scanner(System.in);
    order Order = new order();

    public customer(){
        
    }
    public void menu() {
        int choice = 0;
        do {
            System.out.println("Enter 1 to order");
            System.out.println("Enter 2 for view cart");
            System.out.println("Enter 3 for checkout");
            System.out.println("Enter 4 for exit");
            try {
                choice = input.nextInt();
                if (choice == 1) {
                    Product.viewallproducts();
                    Order.addtocart();
                } else if (choice == 2) {
                    Order.viewcart();
                } else if (choice == 3) {
                    Order.checkout();
                } else {

                }
            }catch (InputMismatchException e) {
                System.out.println("enter a valid number.");
            }
        } while (choice != 4);
    }
    
    @Override
    public void login() {
        System.out.println("Enter Customer Username:");
        username=input.next();
        System.out.println("Enter Customer Password:");
        password=input.next();
        
        if (matchCredentials(username, password, "customerData.txt")) {
            System.out.println("\nYou logged in as Customer...\n");
            menu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    
    @Override
    public void register(){
        System.out.println("Enter Customer Username:");
        username=input.next();
        System.out.println("Enter Customer Password:");
        password=input.next();
        try{
        FileWriter Fw = new FileWriter("customerData.txt",true);
        Fw.write(username+","+password);
        Fw.close();
        }catch(IOException e){
            System.out.println("Error to save data "+e);
        }
    }
    public customer(String username, String password) {
        super(username, password);
    }

    public void orderproducts() {
        
    }
}
