package com.mycompany.ecommerceproject;

import java.util.InputMismatchException;
import java.util.Scanner;


public class EcommerceProject {
    static Scanner input= new Scanner(System.in);
    
    public static void main(String[] args) {
        inventory sharedInventory = new inventory();
        user users = new user();
        product p1 = new product(1, "Lays", 10, 25);
        product p2 = new product(2, "Pepsi", 15, 20);
        sharedInventory.addproduct(p1);
        sharedInventory.addproduct(p2);
       
        int choice=0;
        do{
            mainmenu();
            try {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        users.register();
                        break;
                    case 2:
                        users.login(sharedInventory);
                        break;
                    case 3:
                        users.adminlogin(sharedInventory);
                        break;
                    case 4:
                        System.out.println("Exit.....");
                        break;
                    default:
                        System.out.println("Enter correct choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("enter a valid number.");
                input.next(); 
            }
        }while(choice!=4);
    }
    
    public static void mainmenu(){
        System.out.println("Press 1 for Register");
        System.out.println("Press 2 for login");
        System.out.println("Press 3 for Admin login");
        System.out.println("Press 4 for exit");        
    }  
    
}
