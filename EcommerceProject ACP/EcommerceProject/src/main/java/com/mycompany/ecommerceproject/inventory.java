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
public class inventory {

    private ArrayList<product> products = new ArrayList<>();

    public void addproduct(product Products) {
        products.add(Products);
    }

    public ArrayList<product> returnproducts() {
        return products;
    }
}
