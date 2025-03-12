package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.domain.orderItem.OrderItem;
import com.davijose.challenge_foursales.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll(){
        return orderItemRepository.findAll();
    }
    public OrderItem findById(Long id){
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(id);

        return optionalOrderItem.orElse(null);
    }
    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
    public OrderItem update(Long id, OrderItem orderItem){
        OrderItem orderItemFound = findById(id);

        if(orderItemFound != null){
            return orderItemRepository.save(orderItem);
        }else{
            return orderItem;
        }
    }
}
