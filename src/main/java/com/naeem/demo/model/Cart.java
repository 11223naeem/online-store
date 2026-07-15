package com.naeem.demo.model;

import jakarta.persistence.*;

@Entity   // ✅ VERY IMPORTANT
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private String status;

    public Cart() {
    }

    public Cart(String name, double price) {
        this.name = name;
        this.price = price;
        this.status = "CART";
         
    }

    // GETTERS & SETTERS
    public int getId() { return id; }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}