/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerceproject;

/**
 *
 * @author Abdullah
 */
public class orderitem {

    private String productname;

    public String getProductname() {
        return productname;
    }

    public int getQuantity() {
        return quantity;
    }
    private int quantity;

    public orderitem() {
    }

    public orderitem(String productname, int quantity) {
        this.productname = productname;
        this.quantity = quantity;
    }

}
