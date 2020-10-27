package com.endava.internship2020.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ordered_beverages")
public class BeverageDto {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderDto order;

    public BeverageDto(String name){
        this.name = name;
    }
}
