package com.zaroyan.pizzaorderapp.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import com.zaroyan.pizzaorderapp.PizzaOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import com.zaroyan.pizzaorderapp.Ingredient;
import com.zaroyan.pizzaorderapp.Ingredient.Type;
import com.zaroyan.pizzaorderapp.Pizza;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FTDO", "Fthin dough", Type.WRAP),
                new Ingredient("CLDO", "Classik dough", Type.WRAP),
                new Ingredient("CHKN", "Chiken", Type.MEAT),
                new Ingredient("POHA", "Pork ham", Type.MEAT),
                new Ingredient("PEPE", "Pepperoni", Type.MEAT),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("OLIV", "Olives", Type.VEGGIES),
                new Ingredient("MUSH", "Mushrooms", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("MOZZ", "Mozzarella", Type.CHEESE),
                new Ingredient("DOBL", "Dor blue", Type.CHEESE),
                new Ingredient("PARM", "Parmesan", Type.CHEESE),
                new Ingredient("TOMA", "Tomato sauce", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }


    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
