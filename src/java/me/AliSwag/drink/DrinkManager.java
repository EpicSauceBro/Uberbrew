package me.AliSwag.drink;

import me.AliSwag.Main;
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
	
	public static void addDrink(int percent, String displayName, String name){
		Drink d = new Drink(percent, displayName, name);
		drinks.add(d);
        List<String> drinks = Main.getMainConfig().getStringList("drinks");
        if(!drinks.contains(name)) {
            drinks.add(name);
        }
        Main.getMainConfig().set("drinks", drinks);
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

        ItemStack is = new ItemStack(Material.GLASS_BOTTLE);

        ItemMeta im = is.getItemMeta();

        List<String> lore = im.getLore();

        lore.add("Drink");
        lore.add(drink.name);
        lore.add(drink.alcoholContent + "%");
        lore.add(litres + " L");

        im.setLore(lore);

        im.setDisplayName(drink.displayName);

        is.setItemMeta(im);

        player.getInventory().addItem(is);
    }

}
