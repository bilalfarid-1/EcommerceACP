
package com.mycompany.ecommerceproject;

import java.io.*;
import java.util.Scanner;


public class product {

    private int id;

    public product(){
        
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    private String name;
    private double price;
    private int stockQuantity;

    public product(int id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

        public void viewallproducts() {
        try (Scanner fileread = new Scanner(new File("productsData.txt"))) {
            int i = 1; 
            while (fileread.hasNextLine()) {
                String line = fileread.nextLine();
                String[] parts = line.split(",");

                System.out.println("\n******** Product " + i + " ********");
                System.out.println("ID: " + parts[0]);
                System.out.println("Name: " + parts[1]);
                System.out.println("Price: " + parts[2]);
                System.out.println("Stock: " + parts[3] + "\n");
                i++; 
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the product file: " + e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

}
