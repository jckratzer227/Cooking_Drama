package com.example.cookingdrama;

public class Customer {
    private static Recipe RecipeOrder;

    public Customer () {};

    public Customer (Recipe RecipeOrder) {
        Customer.RecipeOrder = RecipeOrder;
    }

    public static void setOrder(Recipe RecipeOrder) {
        Customer.RecipeOrder = RecipeOrder;
    }

    public static Recipe getOrder() {
        return RecipeOrder;
    }
}
