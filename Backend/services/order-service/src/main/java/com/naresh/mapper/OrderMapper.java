package com.naresh.mapper;

import com.naresh.dto.OrderRequest;
import com.naresh.dto.OrderResponse;
import com.naresh.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest){
        if(orderRequest==null)return null;
        return Order.builder()
                .reference(orderRequest.reference())
                .paymentMethod(orderRequest.paymentMethod())
                .customerId(orderRequest.customerId()).build();
    }
    public OrderResponse fromOrder(Order order){
        return new OrderResponse(order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                 order.getCustomerId());
    }
}
