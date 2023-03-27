package com.Ecommers.shopping.converter;

import com.Ecommers.shopping.DtoRequest.SellerRequestDto;
import com.Ecommers.shopping.DtoResponse.ProductResponseDto;
import com.Ecommers.shopping.DtoResponse.SellerResponseDto;
import com.Ecommers.shopping.Model.Seller;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class SellerConverter {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        String sellerAddress = sellerRequestDto.getCompanyName()+ "$" +sellerRequestDto.getStreet1() +"$" + sellerRequestDto.getStreet2() + "$" + sellerRequestDto.getCity() + "$" + sellerRequestDto.getState() + "$" + sellerRequestDto.getCountry() + "$" + sellerRequestDto.getPinCode();
        return Seller.builder()
                .name(sellerRequestDto.getCompanyName())
                .age(sellerRequestDto.getAge())
                .email(sellerRequestDto.getEmail())
                .mobileNo(sellerRequestDto.getMobileNo())
                .panNo(sellerRequestDto.getPanNo())
                .sellerAddress(sellerAddress)
                .build();
    }

    public static SellerResponseDto sellerTOSellerResponseDto(Seller seller, List<ProductResponseDto> productResponseDtoList){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .mobileNo(seller.getMobileNo())
                .email(seller.getEmail())
                .panNo(seller.getPanNo())
                .sellerAddress(seller.getSellerAddress())
                .productList(productResponseDtoList)
                .build();

    }
}
