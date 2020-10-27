package com.endava.internship2020.model;

import lombok.Data;

@Data
public class Customer {
    private final String name;

    @Override
    public String toString(){
        return name;
    }
}
