
package com.mycompany.ecommerceproject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EcommerceProject {
    static Scanner input= new Scanner(System.in);
    
    public static void main(String[] args) 
    {
        user users;
       
        
        int choice=0;
        do{
            mainmenu();
            try {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        users = new customer();
                        users.register();
                        break;
                    case 2:
                        users = new customer();
                        users.login();
                        break;
                    case 3:
                        users = new admin();
                        users.login();
                        break;
                    case 4:
                        System.out.println("Good Bye.....\n");
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
        System.out.println("Press 2 for Customer login");
        System.out.println("Press 3 for Admin Login");
        System.out.println("Press 4 for Exit");        
    }  
    
}
