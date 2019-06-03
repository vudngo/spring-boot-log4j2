package io.sentry.example;


import java.util.List;

public class Order {
    private List<Item> cart;
    private String email;

    public Order() {
    }

    public Order(List<Item> cart, String email) {
        this.cart = cart;
        this.email = email;
    }

    public List<Item> getCart() {
        return cart;
    }

    public String getEmail() {
        return email;
    }
}
