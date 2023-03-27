package com.Ecommers.shopping.Service.Implementation;

import com.Ecommers.shopping.DtoRequest.ProductRequestDto;
import com.Ecommers.shopping.DtoResponse.ProductResponseDto;
import com.Ecommers.shopping.Enum.ProductCategory;
import com.Ecommers.shopping.Exception.DimensionParameterNullException;
import com.Ecommers.shopping.Exception.ProductNotFoundException;
import com.Ecommers.shopping.Exception.SellerNotFoundException;
import com.Ecommers.shopping.Model.Product;
import com.Ecommers.shopping.Model.Seller;
import com.Ecommers.shopping.Repository.ProductRepository;
import com.Ecommers.shopping.Repository.SellerRepository;
import com.Ecommers.shopping.Service.ProductService;
import com.Ecommers.shopping.converter.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException, DimensionParameterNullException {

        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch (NoSuchElementException e){
            throw new SellerNotFoundException("Enter a valid Seller Id!");
        }
        if(productRequestDto.getWeight() == 0 || productRequestDto.getHeight() == 0 || productRequestDto.getWidth() == 0 || productRequestDto.getLength() == 0){
            throw new DimensionParameterNullException("Dimension Parameters cannot be null");
        }

        Product product = ProductConverter.productRequestDtoToProduct(productRequestDto,seller);

        seller.getProductList().add(product);
        sellerRepository.save(seller);

        return ProductConverter.productToProductResponseDto(product);

    }

    @Override
    public String updateProduct(int productId, ProductRequestDto productRequestDto) throws ProductNotFoundException {

        Product product;
        try{
            product = productRepository.findById(productId).get();
        }catch (NoSuchElementException e){
            throw new ProductNotFoundException("Product Id does not exit, Enter a valid Product Id!");
        }

        String newName;

        int newPrice;

        int newQuantity;

        ProductCategory newProductCategory;

        String newProductDetail;

        String newDimension;

        String message = "Details has been updated: \n";
        if(productRequestDto.getName() != null){
            newName = productRequestDto.getName();
            product.setName(newName);
            message += "Product Name :" + newName + "\n";
        }
        if(productRequestDto.getPrice() != 0){
            newPrice = productRequestDto.getPrice();
            product.setPrice(newPrice);
            message += "Product Price :" + newPrice + "\n";
        }
        if(productRequestDto.getQuantity() != 0){
            newQuantity = productRequestDto.getQuantity();
            product.setQuantity(newQuantity);
            message += "Product Quantity :" + newQuantity + "\n";
        }
        if(productRequestDto.getProductCategory()!= null){
            newProductCategory = productRequestDto.getProductCategory();
            product.setProductCategory(newProductCategory);
            message += "Product Category :" + newProductCategory + "\n";
        }
        if(productRequestDto.getProductDetail()!=null){
            newProductDetail = productRequestDto.getProductDetail();
            product.setProductDetail(newProductDetail);
            message += "Product Details :" + newProductDetail + "\n";
        }
        newDimension = getNewProductDimension(product,productRequestDto);
        if(newDimension != "$$$$"){
            product.setProductDimension(newDimension);
            message += "Product Dimension :" + newDimension + "\n";
        }

        productRepository.save(product);
        return message;

    }

    @Override
    public List<ProductResponseDto> getAllProducts(int sellerId) throws SellerNotFoundException {
        Seller seller;
        try{
            seller = sellerRepository.findById(sellerId).get();
        }catch (Exception e){
            throw new SellerNotFoundException("Seller Id does not exist, Enter a valid Seller Id");
        }

        List<Integer> productIds = productRepository.findAllBySellerId(sellerId);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Integer productId : productIds){
            ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDto(productRepository.findById(productId).get());
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public void deleteProductById(int productId) throws ProductNotFoundException {
        Product product;
        try{
            product = productRepository.findById(productId).get();
        }catch (Exception e){
            throw new ProductNotFoundException("Product Id does not exist, Enter a valid product Id");
        }
    }


    private String getNewProductDimension(Product product, ProductRequestDto productRequestDto) {

        String productDimension = product.getProductDimension();// weight$height$width$length
        double weight = productRequestDto.getWeight();
        double height = productRequestDto.getHeight();
        double width = productRequestDto.getWidth();
        double length = productRequestDto.getLength();

        String[] dimension = productDimension.split("\\$");
        if(weight != 0)dimension[0] = String.valueOf(weight);
        if(height != 0)dimension[1] = String.valueOf(height);
        if(width != 0)dimension[2] = String.valueOf(width);
        if(length != 0)dimension[3] = String.valueOf(length);
        String dimes= "";
        for(int i =0; i < dimension.length; i++){
            dimes += dimension[i]+"$";
        }
        if(productDimension.equals(dimes)) return "$";

        return dimes;

    }


}
