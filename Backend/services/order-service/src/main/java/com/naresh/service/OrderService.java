package com.naresh.service;

import com.naresh.client.CustomerClient;
import com.naresh.client.PaymentClient;
import com.naresh.client.ProductClient;
import com.naresh.dto.*;
import com.naresh.exception.BusinessException;
import com.naresh.kafka.OrderConfirmation;
import com.naresh.kafka.OrderProducer;
import com.naresh.mapper.OrderMapper;
import com.naresh.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private  final OrderMapper mapper;
    private final PaymentClient paymentClient;
    private final OrderProducer orderProducer;
private final OrderLineService orderLineService;

    public Long createOrder(OrderRequest request){
        var customer=this.customerClient.findCustomer(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order :: no customer exists with the provided ID"));

        var purchasedProduct=productClient.purchaseProduct(request.products());
        var order=this.orderRepository.save(mapper.toOrder(request));
        for(PurchaseRequest purchaseRequest:request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );}
            var paymentRequest=new PaymentRequest(
                    request.totalAmount(),
                    request.paymentMethod(),
                 order.getId(),
                   order.getReference(),
                    customer);
            paymentClient.requestOrderPayment(paymentRequest);
            orderProducer.sendOrderConfirmation(new OrderConfirmation(
                    request.reference(), request.totalAmount(),request.paymentMethod()
                    ,customer,purchasedProduct
            ));
            return order.getId();
}
    public List<OrderResponse> findAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }
    public OrderResponse findById(Long id) {
        return this.orderRepository.findById(id)
                .map(this.mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
