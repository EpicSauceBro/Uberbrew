package me.AliSwag.drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkManager {
	
	public static List<Drink> drinks = new ArrayList<Drink>();
	public static List<String> drinkCategorys = new ArrayList<String>();
	
	public static Drink getDrink(String name){
		for(Drink d : drinks){
			if(d.name.equalsIgnoreCase(name)){
				return d;
			}
		}
		return null;
	}
	
	public static void addDrink(int percent, String displayName, String name, String category){
		Drink d = new Drink(percent, displayName, name, category);
		drinks.add(d);
	}
	
	public static List<String> getDrinksNames(){
		List<String> names = new ArrayList<String>();
		for(Drink d : drinks){
			names.add(d.displayName);
		}
		return names;
	}
}
