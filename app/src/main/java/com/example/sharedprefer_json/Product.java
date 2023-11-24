package com.example.sharedprefer_json;

public class Product {
    String product;
    double price;

    public Product(String product, double price){
        this.product=product;
        this.price=price;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }
}
