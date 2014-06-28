package me.AliSwag.drink;

public class Drink {
	
	public int alcoholContent;
	public String displayName;
	public String name;
	public String type;
	
	public Drink(int percent, String displayName, String name, String type){
		this.alcoholContent = percent;
		this.displayName = displayName;
		this.name = name;
		if(DrinkManager.drinkCategorys.contains(type)){
			this.type = type;
		}else{
			this.type = null;
		}
	}
}
