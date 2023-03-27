package com.Ecommers.shopping.Controller;

import com.Ecommers.shopping.DtoRequest.SellerRequestDto;
import com.Ecommers.shopping.DtoResponse.ProductResponseDto;
import com.Ecommers.shopping.DtoResponse.SellerResponseDto;
import com.Ecommers.shopping.Exception.SellerNotFoundException;
import com.Ecommers.shopping.Service.Implementation.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerServiceImpl sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        try{
            sellerService.addSeller(sellerRequestDto);
        }catch (SQLException e){
            return e.getMessage();
        }
        return "Seller added";
    }

    @GetMapping("/getSellers")
    public List<SellerResponseDto> gelAllSeller(){
        List<SellerResponseDto> sellerResponseDtoList = sellerService.getAllSeller();
        return sellerResponseDtoList;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSeller(int sellerId){
        String message;
        try{
            message = sellerService.deleteSeller(sellerId);
        }catch (SellerNotFoundException e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }
}
