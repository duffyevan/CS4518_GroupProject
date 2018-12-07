package edu.wpi.cs4518_groupproject;

public class Price {
    private String name;
    private double price;

    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public Price(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price { name: \"" + name + "\", price: \"" + price + "\"}";
    }
}
