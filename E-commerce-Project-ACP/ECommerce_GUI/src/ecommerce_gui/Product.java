package ecommerce_gui;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String imagePath;
    private int stock;

    public Product(int id, String name, String description, double price, String imagePath, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getStock() {
        return stock;
    }
}
