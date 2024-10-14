/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerceproject;

/**
 *
 * @author Abdullah
 */
public class payment {
    private int id;
    private order order;
    private double amount;
    private String paymentMethod;
    private String status;

    public payment(int id, order order, double amount, String paymentMethod) {
        this.id = id;
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "Pending";
    }
}
