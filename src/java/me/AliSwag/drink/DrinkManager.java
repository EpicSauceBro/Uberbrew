package me.AliSwag.drink;

import me.AliSwag.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DrinkManager {
	
	public static List<Drink> drinks = new ArrayList<Drink>();
	public static Drink getDrink(String name){
		for(Drink d : drinks){
			if(d.name.equalsIgnoreCase(name)){
				return d;
			}
		}
		return null;
	}

    public static Drink[] getDrinks() {
        return (Drink[]) drinks.toArray();
    }

    public static void loadDrinks(){
        for(String s : Main.getMainConfig().getStringList("drinks")){
            String displayName = ChatColor.translateAlternateColorCodes('&', Main.getMainConfig().getString( s + ".displayName"));
            String name = Main.getMainConfig().getString(s + ".name");
            int percent = Main.getMainConfig().getInt(s + ".alcoholContent");

            Drink d = new Drink(percent, displayName, name);

            drinks.add(d);
        }
    }

	public static void addDrink(int percent, String displayName, String name){
		Drink d = new Drink(percent, displayName, name);
		drinks.add(d);
        List<String> drinks = Main.getMainConfig().getStringList("drinks");
        if(!drinks.contains(name)) {
            drinks.add(name);
        }
        Main.getMainConfig().set("drinks", drinks);

        Main.getMainConfig().set(name + ".alcoholContent", percent);

        Main.getMainConfig().set(name + ".displayName", displayName);

        Main.getMainConfig().set(name + ".name", name);

        Main.saveMainConfig();
    }

    public static void editDrink(int percent, String displayName, String name){
        Drink d = DrinkManager.getDrink(name);
        d.name = name;
        d.displayName = displayName;
        d.alcoholContent = percent;
        drinks.remove(DrinkManager.getDrink(name));
        drinks.add(d);

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
