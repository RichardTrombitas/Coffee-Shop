package com.endava.internship2020.repository;

import com.endava.internship2020.dto.OrderDto;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderDto, Long> {
}
