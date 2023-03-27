package com.Ecommers.shopping.Service.Implementation;

import com.Ecommers.shopping.DtoResponse.ItemResponseDto;
import com.Ecommers.shopping.Exception.ProductNotFoundException;
import com.Ecommers.shopping.Model.Item;
import com.Ecommers.shopping.Model.Product;
import com.Ecommers.shopping.Repository.ProductRepository;
import com.Ecommers.shopping.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ProductRepository productRepository;

    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException {

        Product product;
        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Sorry! Invalid product id.");
        }

        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();

        // map item to product
        product.setItem(item);

        // save both item and product
        productRepository.save(product);

        // prepare the response dto
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();

        return itemResponseDto;
    }
}
