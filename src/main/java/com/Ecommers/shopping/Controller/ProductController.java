package com.Ecommers.shopping.Controller;

import com.Ecommers.shopping.DtoRequest.ProductRequestDto;
import com.Ecommers.shopping.DtoResponse.ProductResponseDto;
import com.Ecommers.shopping.Exception.DimensionParameterNullException;
import com.Ecommers.shopping.Exception.ProductNotFoundException;
import com.Ecommers.shopping.Exception.SellerNotFoundException;
import com.Ecommers.shopping.Service.Implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        ProductResponseDto productResponseDto;

        try{
            productResponseDto = productService.addProduct(productRequestDto);
        }catch (SellerNotFoundException | DimensionParameterNullException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") int productId, @RequestBody ProductRequestDto productRequestDto){
        String message;
        try{
            message = productService.updateProduct(productId,productRequestDto);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }

    // search product using regex

    @GetMapping("/productsBySellerId/{id}")//for seller
    public ResponseEntity getAllProductsForSeller(@PathVariable("id") int sellerId){
        List<ProductResponseDto> productList;
        try{
            productList =productService.getAllProducts(sellerId);
        }catch (SellerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList,HttpStatus.ACCEPTED);
    }

    // delete product
    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") int productId){
        try{
            productService.deleteProductById(productId);
            return "Product having product Id : " + productId + " has been deleted.";
        }catch (ProductNotFoundException e){
            return e.getMessage();
        }
    }
}
