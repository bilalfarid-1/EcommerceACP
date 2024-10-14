/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerceproject;

import java.util.ArrayList;

/**
 *
 * @author Abdullah
 */
public class order {

    private ArrayList<orderitem> Orderitems = new ArrayList<>();
    private int id;
    private customer customer;

    public order(int id, customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public order() {
    }

    public void additem(orderitem orderitems, String ordername, int quantity) {
        orderitems = new orderitem(ordername, quantity);
        Orderitems.add(orderitems);
    }

    public void viewcart(orderitem orderitems) {
        if (Orderitems.size() > 0) {
            System.out.println("Cart is empty yet");
        }
        for (int i = 0; i < Orderitems.size(); i++) {
            orderitems = Orderitems.get(i);
            System.out.println("\nProduct: " + (i + 1));
            System.out.println("Name: " + orderitems.getProductname());
            System.out.println("Stock Qty: " + orderitems.getQuantity() + "\n");
        }
    }
}
