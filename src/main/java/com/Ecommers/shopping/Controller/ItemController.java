package com.Ecommers.shopping.Controller;

import com.Ecommers.shopping.DtoResponse.ItemResponseDto;
import com.Ecommers.shopping.Exception.ProductNotFoundException;
import com.Ecommers.shopping.Repository.ItemRepository;
import com.Ecommers.shopping.Service.Implementation.ItemServiceImpl;
import com.Ecommers.shopping.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/view/{productId}")
    public ResponseEntity viewItem(@PathVariable("productId") int productId){

        ItemResponseDto itemResponseDto;
        try{
            itemResponseDto = itemService.viewItem(productId);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(itemResponseDto,HttpStatus.ACCEPTED);
    }
}
