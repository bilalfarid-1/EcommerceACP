
package com.mycompany.ecommerceproject;

import java.io.*;
import java.util.Scanner;

public abstract class user {

    public user() {
    }
    protected String username;
    protected String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    
    public abstract void login();
    public abstract void register();
   
    public boolean matchCredentials(String username, String password,String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the credentials.");
        }
        return false;
    }

    
}
