/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerceproject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Abdullah
 */
public class admin extends user {

    static Scanner input = new Scanner(System.in);

    public admin() {
    }

    public admin(String username, String password) {
        super(username, password);
    }

    public void displaymenu(inventory inventorys) {
        int choice;
        do {
            System.out.println("Enter 1 for add product");
            System.out.println("Enter 2 for View Products");
            System.out.println("Enter 3 for exit");
            choice = input.nextInt();

            if (choice == 1) {
                productsadd(inventorys);
            } else if (choice == 2) {
                viewallproducts(inventorys);
            } else if (choice == 3) {
                System.out.println("Exit...\n");
            } else {
                System.out.println("Invalid choice enter again");
            }

        } while (choice != 3);

    }

    public void viewallproducts(inventory inventorys) {
        ArrayList<product> products = inventorys.returnproducts();
        for (int i = 0; i < products.size(); i++) {
            System.out.println("\nProduct " + (i + 1));
            product Products = products.get(i);
            System.out.println("Id " + Products.getId());
            System.out.println("Name " + Products.getName());
            System.out.println("Price " + Products.getPrice());
            System.out.println("Stock Qty " + Products.getStockQuantity() + "\n");

        }

    }

    public void productsadd(inventory inventorys) {
        int id, stockQuantity;
        String name, choice="Y";
        double price;
        ArrayList<product> Products = inventorys.returnproducts();
        do {
            try{
            id = Products.size() + 1;

            System.out.println("Enter Name");
            name = input.next();

            System.out.println("Enter Price");
            
            price = input.nextDouble();           

            System.out.println("Enter Stock Quantity");
            stockQuantity = input.nextInt();

            product products = new product(id, name, price, stockQuantity);
            inventorys.addproduct(products);
            
            System.out.println("\nEnter \"Y\" to add more \"N\" for exit");
            choice = input.next();
            } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter the correct data again.");
            input.next();  
            
            if (choice.equalsIgnoreCase("N")) {
                System.out.println("Bye...");
            }
            } catch (Exception ex) {
                System.out.println("An error occurred: " + ex.getMessage());
            }
            
        } while (!choice.equalsIgnoreCase("N"));
    }

}
