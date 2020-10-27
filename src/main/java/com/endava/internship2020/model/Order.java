package com.endava.internship2020.model;

import com.endava.internship2020.model.beverage.Beverage;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Order {
    private final String dateTime;
    private final Customer customer;
    private final boolean pickUp;
    private final List<Beverage> beverages;
    private final BigDecimal totalPrice;

    public Order(String dateTime, Customer customer, boolean pickUp, List<Beverage> beverages) {
        this.dateTime = dateTime;
        this.customer = customer;
        this.beverages = beverages;
        this.pickUp = pickUp;
        totalPrice = computeTotalPrice();
    }

    private BigDecimal computeTotalPrice() {
        return beverages.stream()
                .map(Beverage::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(50);
        sb.append("Customer: ").append(customer)
                .append("\n").append(pickUp ? "Order type: pick-up" : "Order type: delivery")
                .append("\nDate and time of order: ").append(dateTime)
                .append("\nSelected beverages:\n");

        beverages.forEach(beverage -> sb.append("\t").append(beverage)
                .deleteCharAt(sb.length() - 1).append(",\n\n"));

        sb.delete(sb.lastIndexOf(","), sb.length())
                .append("\n\nTotal: ").append(totalPrice).append(" RON");

        return sb.toString();
    }

}
