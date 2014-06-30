package me.AliSwag;

import me.AliSwag.commands.DrinkCommand;

import me.AliSwag.drink.DrinkManager;
import me.AliSwag.listeners.PlayerConsume;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class Main extends JavaPlugin {

    static Main main;
    public static PluginManager pm;
    public static Map<Player, InfluencedPlayer> influencedPlayers;

	@Override
	public void onEnable() {
		main = this;

        pm = this.getServer().getPluginManager();

        pm.registerEvents(new PlayerConsume(), this);

		getCommand("uberbrew").setExecutor(new DrinkCommand());

        DrinkManager.loadDrinks();
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
