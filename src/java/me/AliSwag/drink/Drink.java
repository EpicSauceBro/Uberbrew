package me.AliSwag.drink;

public class Drink {
	
	public int alcoholContent;
	public String displayName;
	public String name;
	
	public Drink(int percent, String displayName, String name){
		this.alcoholContent = percent;
		this.displayName = displayName;
		this.name = name;
	}

    public int getAlcoholContent() { return this.alcoholContent; }
    public String getName () {return this.name; }
    public String getDisplayName() { return this.displayName; }

}
