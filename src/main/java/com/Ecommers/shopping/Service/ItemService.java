package com.Ecommers.shopping.Service;

import com.Ecommers.shopping.DtoResponse.ItemResponseDto;
import com.Ecommers.shopping.Exception.ProductNotFoundException;

public interface ItemService {

    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException;
}
