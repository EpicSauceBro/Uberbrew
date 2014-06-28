package me.AliSwag.drink;

public enum DrinkTypes {
	WINE(1,"Wine"),
	BEER(2,"Beer"),
	LIQUOR(3,"Liquor"),
	SPIRITS(4,"Spirits");
	
	private final String name;
	private final int Id;
	DrinkTypes(int id, String displayName){
		name = displayName;
		Id = id;
	}
	
	public String getDisplayName(){
		return name;
	}
	
	public int getId(){
		return Id;
	}
}
