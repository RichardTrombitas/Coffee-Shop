package com.endava.internship2020.dto.ingredient;

import com.endava.internship2020.dto.RecipeDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "toppings")
public class ToppingDto {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private RecipeDto recipe;

    private String name;

    private double amount;
}
