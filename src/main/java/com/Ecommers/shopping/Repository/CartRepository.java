package com.Ecommers.shopping.Repository;

import com.Ecommers.shopping.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
