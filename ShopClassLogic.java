/**********************************************************************
 * Shop Logic
 * Images from: vecteezy.com, freepik.com
 * Creator: Kaycee Rigor
 * Modification date: 12-2-2023
 **********************************************************************/


package com.example.shopclass;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ShopClassLogic {

    static int cookingCoins = 1000; // initial currency amount
    private static List<Item> inventory = new ArrayList<>();
    private static ArrayList<Item> userInventory;

    static void Shop() {

        //List<Item> inventory = new ArrayList<>();
        //ArrayList userInventory = new ArrayList<>();

        // for dishes
        ArrayList<String> recipe1Ingredients = new ArrayList<>();
        recipe1Ingredients.add("Chicken");
        recipe1Ingredients.add("Seafood");
        recipe1Ingredients.add("Butter");

        Recipe paella = new Recipe("Seafood and Chicken Paella", 240, recipe1Ingredients, 60, 350);

        ArrayList<String> recipe2Ingredients = new ArrayList<>();
        recipe2Ingredients.add("Potatoes");
        recipe2Ingredients.add("Seasoning");
        recipe2Ingredients.add("Butter");

        Recipe mashedPotatoes = new Recipe("Rosemary Mashed Potatoes", 230, recipe2Ingredients, 35, 210);

        ArrayList<String> recipe3Ingredients = new ArrayList<>();
        recipe2Ingredients.add("Chicken");
        recipe2Ingredients.add("Cheese");
        recipe2Ingredients.add("Sauce");

        Recipe chickenParm = new Recipe("Chicken Parm", 210, recipe3Ingredients, 45, 450);

        ArrayList<String> recipe4Ingredients = new ArrayList<>();
        recipe4Ingredients.add("Chicken");
        recipe4Ingredients.add("Cheese");
        recipe4Ingredients.add("Sauce");

        Recipe crabRangoons = new Recipe("Crab Rangoons", 200, recipe4Ingredients, 40, 325);

        inventory.add(paella);
        inventory.add(mashedPotatoes);
        inventory.add(chickenParm);
        inventory.add(crabRangoons);

        // for floorplan
        Image whitetablebackground = new Image("src/main/resources/com/example/shopclass/whitetablefloorplan.jpg");
        Floorplan whiteTable = new Floorplan("White Tables", 1000, 30, whitetablebackground);

        Image retrobackground = new Image("src/main/resources/com/example/shopclass/fancyretrochairsfloorplan.png");
        Floorplan retroChairs = new Floorplan("Fancy Retro Dining Chairs", 2000, 60, retrobackground);

        inventory.add(whiteTable);
        inventory.add(retroChairs);
    }

    static void buyItem(Item item) {
        //HelloController controller = new HelloController();
        if (item instanceof Recipe) {
            if (cookingCoins >= item.getPrice()) {

                // subtract item price from the cookingCoins
                cookingCoins -= item.getPrice();

                // add the recipe to the player's recipe list
                Player.addRecipe((Recipe) item);

                inventory.remove(item);
            } else {
                HelloController.MenuConfirmationText.setText("Insufficient Funds!");
            }
        }

        else if (item instanceof Floorplan) {
            if (cookingCoins >= item.getPrice()) {

                // subtract item price from the cookingCoins
                cookingCoins -= item.getPrice();

                // set floorplan
                Player.setFloorplan((Floorplan) item);

                inventory.remove(item);
            } else {
                HelloController.FloorplanConfirmationText.setText("Insufficient Funds!");
            }
        }
    }

    static Item findItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) inventory;
    }
}


