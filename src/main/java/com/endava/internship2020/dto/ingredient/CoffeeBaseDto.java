package com.endava.internship2020.dto.ingredient;

import com.endava.internship2020.dto.RecipeDto;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "coffee_bases")
public class CoffeeBaseDto {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(mappedBy = "coffeeBase", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private RecipeDto recipe;

    private String name;

    private double amount;
}
