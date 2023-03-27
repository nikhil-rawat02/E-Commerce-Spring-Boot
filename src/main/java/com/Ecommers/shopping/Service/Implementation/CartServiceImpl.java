package com.Ecommers.shopping.Service.Implementation;

import com.Ecommers.shopping.DtoRequest.OrderRequestDto;
import com.Ecommers.shopping.DtoResponse.OrderResponseDto;
import com.Ecommers.shopping.Exception.CustomerNotFoundException;
import com.Ecommers.shopping.Exception.ProductNotFoundException;
import com.Ecommers.shopping.Model.*;
import com.Ecommers.shopping.Repository.CustomerRepository;
import com.Ecommers.shopping.Repository.ProductRepository;
import com.Ecommers.shopping.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderedServiceImpl orderedService;
    @Override
    public String addToCart(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        Cart cart = customer.getCart();
        int newCost = cart.getCartTotal() + orderRequestDto.getRequiredQuantity() * product.getPrice();
        cart.setCartTotal(newCost);
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item has been added";


    }

    @Override
    public List<OrderResponseDto> checkOutCart(int customerId) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Enter a valid customer id");
        }

        List<OrderResponseDto> responseDtoList = new ArrayList<>();
        Cart cart = customer.getCart();
        for(Item item : cart.getItems()){
//            OrderRequestDto requestDto = new OrderRequestDto();
//            requestDto.setRequiredQuantity(item.getRequiredQuantity());
//            requestDto.setProductId(item.getProduct().getId());
//            requestDto.setCustomerId(customerId);
//
//            OrderResponseDto orderResponseDto = orderedService.placeOrder(requestDto);
//            responseDtoList.add(orderResponseDto);
            Ordered order = new Ordered();
            order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharges(0);
            order.setCustomer(customer);
            order.getItemList().add(item);

            Card card = customer.getCards().get(0);

        }
        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);

        return  responseDtoList;
    }


}
