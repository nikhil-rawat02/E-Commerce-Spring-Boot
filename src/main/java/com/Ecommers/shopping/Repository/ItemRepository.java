package com.Ecommers.shopping.Repository;

import com.Ecommers.shopping.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
