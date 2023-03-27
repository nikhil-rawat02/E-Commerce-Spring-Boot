package com.Ecommers.shopping.Repository;

import com.Ecommers.shopping.Model.Ordered;
import com.Ecommers.shopping.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select p.id from product p where p.seller_id=:sellerId",nativeQuery = true)
    List<Integer> findAllBySellerId(int sellerId);
}
