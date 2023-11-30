package com.example.cookingdrama;
import java.util.ArrayList;
public class Recipe extends Item{
    private ArrayList<String> ingredients;
    private int cookingTime;
    private int cookingTemp;



    public Recipe(String name, int price, ArrayList<String> ingredients, int time, int temp) {
        super(name, price);
        this.ingredients = ingredients;
        this.cookingTime = time;
        this.cookingTemp = temp;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getCookingTemp() {
        return cookingTemp;
    }

    public void setCookingTemp(int cookingTemp) {
        this.cookingTemp = cookingTemp;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    /**********************************************************************************
     * Check if ingredients match up
     * Grants a range to allow for 'close enough' due to the imprecision of the slider
     * Returns true if correct, false if not
     *********************************************************************************/
    public boolean checkCorrectRecipe(Recipe recipe) {
        if(ingredients.containsAll(recipe.getIngredients())){
            if(cookingTemp-10 < recipe.getCookingTemp() && recipe.getCookingTemp() < cookingTemp+10){
                if(cookingTemp-10 < recipe.getCookingTemp() && recipe.getCookingTemp() < cookingTemp+10){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        String output = super.getName() + "\n\tIngredients: ";
        for(String n : ingredients){
            output += (n + " ");
        }
        output += ("\n\tTime: " + cookingTime);
        output += ("\n\tTemp: " + cookingTemp);
        return output;
    }
}
