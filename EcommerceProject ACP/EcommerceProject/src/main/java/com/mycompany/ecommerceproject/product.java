
package com.mycompany.ecommerceproject;

import java.util.ArrayList;


public class product {

    private inventory inventorys;
    private int id;
    private String name;
    private double price;
    private int stockQuantity;

    public product(int id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public inventory getInventorys() {
        return inventorys;
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
