package com.naeem.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naeem.demo.model.Cart;
import com.naeem.demo.repo.CartRepository;

@Service
public class EcommerceService {

    @Autowired
    CartRepository cartRepo;

    // Add item
    public void addToCart(Cart cart) {
        cartRepo.save(cart);
    }

    

    // Remove item
    public void removeItem(int id) {
        cartRepo.deleteById(id);
    }
}