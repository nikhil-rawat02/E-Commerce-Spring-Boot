package com.Ecommers.shopping.Service;

import com.Ecommers.shopping.DtoRequest.SellerRequestDto;
import com.Ecommers.shopping.DtoResponse.SellerResponseDto;
import com.Ecommers.shopping.Exception.SellerNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface SellerService {
    public void addSeller(SellerRequestDto sellerRequestDto) throws SQLException;

    List<SellerResponseDto> getAllSeller();

    String deleteSeller(int sellerId) throws SellerNotFoundException;
}
