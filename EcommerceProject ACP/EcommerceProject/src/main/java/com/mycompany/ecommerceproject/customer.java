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
public class customer extends user {

    static Scanner input = new Scanner(System.in);
    order orders = new order();
    orderitem orderitems = new orderitem();

    public customer(String username, String password) {
        super(username, password);
    }

    public void orderproducts(inventory Inventory) {
        boolean check = false;
        String ordername = null;
        int quantity = 0;
        ArrayList<product> products = Inventory.returnproducts();
        if (products.size() > 0) {
            System.out.println("Choose Product for order.");
        } else {
            System.out.println("No Product show");
        }
        for (int i = 0; i < products.size(); i++) {
            product Products = products.get(i);
            System.out.println("\nProduct " + i + 1);
            System.out.println("Id " + Products.getId());
            System.out.println("Name " + Products.getName());
            System.out.println("Price " + Products.getPrice());
            System.out.println("Stock Qty " + Products.getStockQuantity() + "\n");
        }
        if (products.size() > 0) {
            System.out.println("Enter Product number for order: ");
            int num = input.nextInt();
            System.out.println("Enter Quantity: ");
            quantity = input.nextInt();
            String choice = "N";

            do {
                for (int i = 0; i < products.size(); i++) {
                    product Products = products.get(i);
                    if (num == Products.getId()) {
                        if (quantity <= Products.getStockQuantity()) {
                            check = true;
                            ordername = Products.getName();
                            break;
                        } else {
                            System.out.println("Available quantiy is " + Products.getStockQuantity());
                        }
                    }
                }
                if (check == true) {
                    System.out.println("Product added to cart");
                    orders.additem(orderitems, ordername, quantity);
                    System.out.println("\nEnter \"Y\" for add more \"N\" ");
                    choice = input.next();
                }

            } while (!choice.equalsIgnoreCase("N"));
        }
    }

    public void displaymenu(inventory Inventory) {
        int choice = 0;

        do {
            System.out.println("Enter 1 to order");
            System.out.println("Enter 2 for view cart");
            System.out.println("Enter 3 for checkout");
            System.out.println("Enter 4 for exit");
            try {
                choice = input.nextInt();
                if (choice == 1) {
                    orderproducts(Inventory);
                } else if (choice == 2) {
                    orders.viewcart(orderitems);
                } else if (choice == 3) {

                } else {

                }
            } catch (InputMismatchException e) {
                System.out.println("enter a valid number.");
            }
        } while (choice != 4);
    }
}
