package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    public Order findById(UUID id){
        Optional<Order> optionalOrder = orderRepository.findById(id);

        return optionalOrder.orElse(null);
    }
    public Order save(Order order){
        return orderRepository.save(order);
    }
    public Order update(UUID id, Order order){
        Order orderFound = findById(id);

        if(orderFound != null){
            return orderRepository.save(order);
        }else{
            return order;
        }
    }
}
