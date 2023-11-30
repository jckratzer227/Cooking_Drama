package com.example.cookingdrama;
import java.util.ArrayList;

public class Shop {
    private static int cookingCoins = 1000; // initial currency amount
    private ArrayList<Item> inventory;
    private ArrayList<Item> userInventory;


    public Shop() {

        inventory = new ArrayList<>();
        userInventory = new ArrayList<>();

        // initial items as of now

        // for dishes
        inventory.add(new Item("Seafood and Chicken Paella", 24));
        inventory.add(new Item("Pasta Carbonara", 20));
        inventory.add(new Item("Rosemary Mashed Tatos", 15));

        // for floorplan
        inventory.add(new Item("Retro Dining Chairs", 20));
        inventory.add(new Item("White Table", 10));
    }

    public void buyItem(Item item) {
        if (cookingCoins >= item.getPrice()) {
            inventory.remove(item);

            // subtract item price from the cookingCoins
            cookingCoins -= item.getPrice();

            // add to user's inventory
            userInventory.add(item);
        } else {
            System.out.println("Insufficient cookingCoins to buy the item.");
        }
    }

    public void removeItem(Item item) {
        // remove purchased item from the shop's inventory
        inventory.remove(item);
    }

    public ArrayList<Item> getItems() {
        return inventory;
    }

    public ArrayList<Item> getUserInventory() {
        return userInventory;
    }

}