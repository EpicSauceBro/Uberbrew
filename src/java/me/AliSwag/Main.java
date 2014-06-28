package me.AliSwag;

import me.AliSwag.commands.DrinkCommand;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    static Main main;

	@Override
	public void onEnable() {
		main = this;

		getCommand("uberbrew").setExecutor(new DrinkCommand());
	}
	
	@Override
	public void onDisable() {}

    public static FileConfiguration getMainConfig() {
        return main.getConfig();
    }

    public static void saveMainConfig() {
        main.saveConfig();
    }

    public static void reloadMainConfig() {
        main.reloadConfig();
    }

}
