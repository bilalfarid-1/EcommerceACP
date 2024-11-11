
package com.mycompany.ecommerceproject;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class admin  extends user {
    private product Product = new product();
    static Scanner input = new Scanner(System.in);
    
    public admin() {        
    }
    
    public void menu() {
        int choice;
        do {
            System.out.println("Enter 1 for add product");
            System.out.println("Enter 2 for View Products");
            System.out.println("Enter 3 for exit");
            choice = input.nextInt();

            if (choice == 1) {
                saveProducts();
            } else if (choice == 2) {
                Product.viewallproducts();
            } else if (choice == 3) {
                System.out.println("Exit...\n");
            } else {
                System.out.println("Invalid choice enter again");
            }

        } while (choice != 3);
    }
    
    @Override
     public void login() {
        System.out.println("Enter Admin Username:");
        username=input.next();
        System.out.println("Enter Admin Password:");
        password=input.next();
        
        if (matchCredentials(username, password, "adminData.txt")) {
            System.out.println("\nYou logged in as Admin...\n");
            menu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    
    @Override
    public void register(){
        
    } 
    


    
    public void saveProducts() {
        int id = 0, stockQuantity;
        String name, choice;
        double price;

        try (Scanner fileread = new Scanner(new File("productsData.txt"))) {
            boolean hasData = false; 

            while (fileread.hasNextLine()) {
                hasData = true;
                String line = fileread.nextLine();
                String[] parts = line.split(",");
                id = Integer.parseInt(parts[0]);
            }

            if (!hasData) {
                id = 0;
            }
        } catch (IOException e) {
            id = 0; 
        }

        try (FileWriter fw = new FileWriter("productsData.txt", true)) {
            do {
                id++;
                System.out.println("Enter Name");
                input.nextLine();  
                name = input.nextLine(); 

                System.out.println("Enter Price");
                price = input.nextDouble();

                System.out.println("Enter Stock Quantity");
                stockQuantity = input.nextInt();

                fw.write(id + "," + name + "," + price + "," + stockQuantity + "\n");

                System.out.println("\nEnter \"Y\" to add more or \"N\" for exit");
                choice = input.next();
            } while (!choice.equalsIgnoreCase("N"));

        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter the correct data again.");
            input.next(); 
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }
}
