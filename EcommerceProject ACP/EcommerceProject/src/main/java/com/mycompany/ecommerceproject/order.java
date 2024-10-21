
package com.mycompany.ecommerceproject;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class order {

    Scanner input = new Scanner(System.in);
    private int id;

    public order(int id) {
        this.id = id;
    }

    public order() {
    }

    public void addtocart() {
        String choice;
        do {
            try {
                boolean idMatch = false;
                FileWriter Fw = new FileWriter("cartData.txt");
                Scanner fileread = new Scanner(new File("productsData.txt"));

                System.out.println("\nEnter Product id to add in cart: ");
                id = input.nextInt();

                while (fileread.hasNextLine()) {
                    String line = fileread.nextLine();
                    String[] parts = line.split(",");
                    int ID = Integer.parseInt(parts[0]);
                    int stock = Integer.parseInt(parts[3]);
                    int qty;
                    if (id == ID) {
                    do{
                    System.out.println("\nEnter Qty to add in cart: ");
                     qty = input.nextInt();
                    if (qty <= stock) {
                        input.nextLine();
                    }
                    else{
                        System.out.println("Availale Stock: "+stock);
                    }
                    }while(qty>stock);
                    idMatch = true;
                    double price = Double.parseDouble(parts[2]);
                    double total = price * qty;
                    Fw.write(id + "," + parts[1] + "," + qty + "," + total + "\n");
                    System.out.println("Enter \"Y\" to add more \"N\" for exit.");
                        break;
                    }
                }
                Fw.close();
                fileread.close();

                if (!idMatch) {
                    System.out.println("Product Id not found");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter Valid Input");
            } catch (IOException e) {
                System.out.println("Product fail to add");
            } catch (Exception e) {
                System.out.println("Error " + e);
            }

            choice = input.nextLine();
        } while (!choice.equalsIgnoreCase("n"));
    }

    public void viewcart() {
        try {
            Scanner fileread = new Scanner(new File("cartData.txt"));
            int i = 1;
            while (fileread.hasNextLine()) {
                String line = fileread.nextLine();
                String[] parts = line.split(",");

                System.out.println("\n******** Product " + i + " ********");
                System.out.println("Name: " + parts[1]);
                System.out.println("Qty: " + parts[2]);
                System.out.println("Total: " + parts[3] + "\n");
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error to read " + e);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void checkout() {

    }
}
