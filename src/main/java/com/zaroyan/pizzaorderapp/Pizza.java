package com.zaroyan.pizzaorderapp;

import lombok.Data;

import java.util.List;

@Data
public class Pizza {
    private String Name;
    private List<Ingredient> ingredients;

}
