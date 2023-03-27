package com.Ecommers.shopping.Service.Implementation;

import com.Ecommers.shopping.Communication.MobileCommunication;
import com.Ecommers.shopping.DtoRequest.OrderRequestDto;
import com.Ecommers.shopping.DtoResponse.OrderResponseDto;
import com.Ecommers.shopping.Enum.ProductStatus;
import com.Ecommers.shopping.Exception.CustomerNotFoundException;
import com.Ecommers.shopping.Exception.ProductNotFoundException;
import com.Ecommers.shopping.Model.*;
import com.Ecommers.shopping.Repository.CustomerRepository;
import com.Ecommers.shopping.Repository.ProductRepository;
import com.Ecommers.shopping.Service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedServiceImpl implements OrderedService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    EmailCommunication emailCommunication;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
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

        // Prepare Order
        int totalCost = orderRequestDto.getRequiredQuantity()*product.getPrice();
        int deliveryCharge = 0;
        if(totalCost<500){
            deliveryCharge = 50;
            totalCost += deliveryCharge;
        }
        Ordered order = Ordered.builder()
                .totalCost(totalCost)
                .deliveryCharges(deliveryCharge)
                .build();

        // prepare the Card String;
        Card card = customer.getCards().get(0);
        String cardUsed = "";
        int cardNo = card.getCardNo().length();
        for(int i = 0;i<cardNo-4;i++){
            cardUsed += 'X';
        }
        cardUsed += card.getCardNo().substring(cardNo-4);
        order.setCardUsedForPayment(cardUsed);
        // update item
        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .build();
        // update customer's current order list
        customer.getOrderedList().add(order);
        order.setCustomer(customer);
        order.getItemList().add(item);

        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrderedList().get(savedCustomer.getOrderedList().size()-1);

        // update the quantity of product left in warehouse
        int leftQuantity = product.getQuantity()- orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        // update item in order

        item.setOrder(order);
        product.setItem(item);
        item.setProduct(product);

        // save product-item and customer-order
        customerRepository.save(customer);


        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .orderDate(order.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardUsed)
                .ItemCost(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(deliveryCharge)
                .build();

//        emailCommunication.sendSimpleMessage(customer.getEmail(),"This is mail subject","This is mail text");
        MobileCommunication.orderStatus(customer.getMobileNo(),orderResponseDto.getProductName());
        return orderResponseDto;
    }
}
