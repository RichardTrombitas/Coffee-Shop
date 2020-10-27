package com.endava.internship2020.service;

import com.endava.internship2020.dto.OrderDto;
import com.endava.internship2020.dto.mapper.OrderMapper;
import com.endava.internship2020.model.Order;
import com.endava.internship2020.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public void save(Order order){
        OrderDto orderDto = orderMapper.orderToOrderDto(order);
        orderDto.getBeverages().forEach(beverageDto -> beverageDto.setOrder(orderDto));
        orderRepository.save(orderDto);
    }
}
