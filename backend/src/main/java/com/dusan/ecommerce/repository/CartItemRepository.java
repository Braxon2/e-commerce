package com.dusan.ecommerce.repository;


import com.dusan.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
