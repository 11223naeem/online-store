package com.naeem.demo.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.naeem.demo.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByStatus(String string);

   

}