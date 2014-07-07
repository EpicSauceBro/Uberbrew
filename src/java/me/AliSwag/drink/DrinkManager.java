package me.AliSwag.drink;

import java.util.ArrayList;
import java.util.List;

import me.AliSwag.Main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DrinkManager {

    public static List<Drink> drinks = new ArrayList<Drink>();

    public static Drink getDrink(String name) {
        for (Drink d : drinks) {
            if (d.name.equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }
    
    public boolean isDrink(ItemStack itemstack) {
    	if (itemstack == null) {
    		return false;
    	}
    	if (itemstack.getType() != Material.POTION) {
			return false;
		}
        ItemMeta im = itemstack.getItemMeta();
        if (!im.hasLore()) {
			return false;
		}
        List<String> lore = im.getLore();
        if (lore.size() < 4) {
			return false;
		}
        if (!ChatColor.stripColor(lore.get(0)).equalsIgnoreCase("drink")) {
			return false;
		}
        String alcoholPercentString = lore.get(2);
        double alcoholPercent;
        try {
            alcoholPercent = Double.parseDouble(alcoholPercentString);
        }
        catch (NumberFormatException ex) { return false; }
        if (alcoholPercent > 100 || alcoholPercent < 1) {
			return false;
		}
        String litresString = lore.get(3);
        long litres;
        try {
            litres = Long.parseLong(litresString);
        }
        catch (NumberFormatException ex) { return false; }
        if (litres < 1) {
			return false;
		}
        return true;
	}
    

    public static Drink[] getDrinks() {
        return (Drink[]) drinks.toArray();
    }

    public static boolean isRegistered(String drinkName) { return getDrink(drinkName) == null ? false : true; }


    public static void loadDrinks(){
        for(String s : Main.getMainConfig().getStringList("drinks")){
            String displayName = ChatColor.translateAlternateColorCodes('&', Main.getMainConfig().getString( s + ".displayName"));
            String name = Main.getMainConfig().getString(s + ".name");
            int percent = Main.getMainConfig().getInt(s + ".alcoholContent");

            Drink d = new Drink(percent, displayName, name);

            drinks.add(d);
        }
    }

	public static void addDrink(int percent, String displayName, String name) {

            Drink d = new Drink(percent, displayName, name);
            drinks.add(d);
            List<String> derpyDrink = Main.getMainConfig().getStringList("drinks");

            derpyDrink.add(name);

            Main.getMainConfig().set("drinks", derpyDrink);

            Main.getMainConfig().set(name + ".alcoholContent", percent);

            Main.getMainConfig().set(name + ".displayName", displayName);

            Main.getMainConfig().set(name + ".name", name);

            Main.saveMainConfig();
    }

    public static void editDrink(int percent, String displayName, String name) {
        Drink drink = DrinkManager.getDrink(name);
        if (drink == null) { return; }
        int drinkIndex = getDrinkIndex(drink);
        if (drinkIndex == -1) {
            return;
        }
        drink.name = name;
        drink.displayName = displayName;
        drink.alcoholContent = percent;
        drinks.set(drinkIndex, drink);
    }

    private static int getDrinkIndex(Drink drink) {
        for (int i = 0; i < drinks.size(); i++){
            Drink possibleDrink = drinks.get(i);
            if (possibleDrink == drink) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteDrink(String name){
        drinks.remove(getDrink(name));
        Main.getMainConfig().set(name, null);
        List<String> drinkList = Main.getMainConfig().getStringList("drinks");
        drinkList.remove(name);
        Main.getMainConfig().set("drinks", drinkList);
        Main.saveMainConfig();
    }
	
	public static List<String> getDrinksNames(){
		List<String> names = new ArrayList<String>();
		for(Drink d : drinks){
			names.add(d.displayName);
		}
		return names;
	}

    public static void addDrinkToInventory(Drink drink, Player player, String litres) {

        ItemStack is = new ItemStack(Material.POTION);

        ItemMeta im = is.getItemMeta();

        List<String> lore = new ArrayList<String>();

        lore.add("Drink");
        lore.add(drink.name);
        lore.add(drink.alcoholContent + "%");
        lore.add(litres + " L");

        im.setLore(lore);

        im.setDisplayName(Main.getMainConfig().getString("drinkPrefix") + " " + drink.displayName);

        is.setItemMeta(im);

        player.getInventory().addItem(is);
    }
}
