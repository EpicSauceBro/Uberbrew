package me.AliSwag;

import me.AliSwag.commands.DrinkCommand;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable(){
		
		getCommand("uberbrew").setExecutor(new DrinkCommand());
		
	}
	
	@Override
	public void onDisable(){
	}

}
