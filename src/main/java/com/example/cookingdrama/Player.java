package com.example.cookingdrama;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Player {
    private static int cookingCoins;
    private static Floorplan floorplan;
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    public static int getCookingCoins() {
        return cookingCoins;
    }

    //More useful to change amount by gain/loss than to overwrite value
    //If loss, pass through a negative number
    public static void changeCookingCoins(int change){
        cookingCoins = cookingCoins + change;
    }

    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    //Useful for resetting recipes
    public static void setRecipes(ArrayList<Recipe> recipes) {
        Player.recipes = recipes;
    }
    //Recipes will often be added one at a time, making it more useful to add rather than rebuild
    public static void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public static Floorplan getFloorplan() {
        return floorplan;
    }

    public static void setFloorplan(Floorplan floorplan) {
        Player.floorplan = floorplan;
    }
}
