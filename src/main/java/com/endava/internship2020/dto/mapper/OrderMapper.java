package com.endava.internship2020.dto.mapper;

import com.endava.internship2020.dto.BeverageDto;
import com.endava.internship2020.dto.OrderDto;
import com.endava.internship2020.model.Customer;
import com.endava.internship2020.model.Order;
import com.endava.internship2020.model.beverage.Beverage;
import com.endava.internship2020.model.beverage.BeverageFactory;
import com.endava.internship2020.model.beverage.BeverageType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = Beverage.class)
public interface OrderMapper {

    Order orderDtoToOrder(OrderDto orderDto);

    OrderDto orderToOrderDto(Order order);

    default Customer stringToCustomer(String name) {
        return new Customer(name);
    }

    default String customerToString(Customer customer) {
        return customer.getName();
    }

    default Beverage beverageDtoToBeverage(BeverageDto beverageDto) {
        return BeverageFactory.createBeverage(
                BeverageType.valueOf(beverageDto.getName()
                        .toUpperCase()
                        .replaceAll(" ", "_")));
    }
}
