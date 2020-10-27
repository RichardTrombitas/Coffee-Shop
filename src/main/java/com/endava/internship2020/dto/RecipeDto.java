package com.endava.internship2020.dto;

import com.endava.internship2020.dto.ingredient.CoffeeBaseDto;
import com.endava.internship2020.dto.ingredient.SweetenerDto;
import com.endava.internship2020.dto.ingredient.ToppingDto;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "recipes")
public class RecipeDto {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_base_id")
    private CoffeeBaseDto coffeeBase;

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<ToppingDto> toppings = new ArrayList<>();

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<SweetenerDto> sweeteners = new ArrayList<>();

    @Override
    public String toString() {
        return "RecipeDto(id=" + id + ", name=" + name + ")";
    }
}
