package com.naeem.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naeem.demo.model.Account;
import com.naeem.demo.model.Cart;
import com.naeem.demo.repo.CartRepository;
import com.naeem.demo.repo.Repositorys;
import com.naeem.demo.service.EcommerceService;

import org.springframework.ui.Model;

@Controller
public class EcomController {
	List<Cart> cartList = new ArrayList<>();
	
	@Autowired
	Repositorys repo;
	
	@Autowired
	EcommerceService service;
	
	@Autowired
	CartRepository cartRepo;
	
	@RequestMapping("/createAccount")
	public String CreateAccount() {
		return "createAccount";
		
	}
	
	@RequestMapping("/loginAccount")
	public String loginAccount() {
		return "login";
		
	}
	
	@RequestMapping("/CreateAccounts")
	public String account(@RequestParam String name,@RequestParam String Email,String pass,String Cpass) {
		Account acc = new Account(name,Email,pass,Cpass);
		repo.save(acc);
		
		return "login";
	}
	
	@RequestMapping("/loginAccounts")
	public String LoginPages(@RequestParam String name,@RequestParam String pass, Model model) {
		Account acc =repo.findByname(name);
		
		if (acc == null  ) {
            model.addAttribute("title", "Error");
            model.addAttribute("message", "Account Not Found");
            
            return "result";
        }
		if(!acc.getPass().equals(pass)) {
			 model.addAttribute("title", "Error");
	            model.addAttribute("message", "Account Not Found");
	            
	            return "result";
		}
    	else {
    		return "dashboard";
	}
	}
	
	 @RequestMapping("/addToCart")
	    public String addToCart(@RequestParam String name,
	                           @RequestParam double price) {

	        cartList.add(new Cart(name, price));
	        return "redirect:/cart";
	    }

	    // Cart page
	    @RequestMapping("/cart")
	    public String cart(Model model) {

	        double total = 0;

	        for (Cart c : cartList) {
	            if (c.getStatus().equals("CART")) {
	                total += c.getPrice();
	            }
	        }

	        model.addAttribute("cartItems", cartList);
	        model.addAttribute("total", total);

	        return "cart";
	    }

	    // Remove item
	    @RequestMapping("/remove")
	    public String remove(@RequestParam int index) {
	        cartList.remove(index);
	        return "redirect:/cart";
	    }

	    // Save for later
	    @RequestMapping("/save")
	    public String save(@RequestParam int index) {
	        cartList.get(index).setStatus("SAVED");
	        return "redirect:/cart";
	    }
	    @GetMapping("/product")
	    public String productPage(
	            @RequestParam String name,
	            @RequestParam String price,
	            @RequestParam String desc,
	            Model model) {

	        model.addAttribute("name", name);
	        model.addAttribute("price", price);
	        model.addAttribute("desc", desc);

	        return "product";
	    }
	    
	    @GetMapping("/buyNow")
	    public String buyNow(@RequestParam String name,
	                         @RequestParam double price) {

	        Cart cart = new Cart(name, price);
	        cart.setStatus("ORDERED");
	        
	        cartList.add(cart);

	        return "redirect:/cart";
	    }
	    
	    @GetMapping("/checkout")
	    public String checkout() {

	        for (Cart item : cartList) {
	            if (item.getStatus().equals("CART")) {
	                item.setStatus("ORDERED");
	            }
	        }

	        return "redirect:/cart";
	    }
	
}
