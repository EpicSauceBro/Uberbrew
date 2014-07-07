package me.AliSwag;

import me.AliSwag.commands.DrinkCommand;

import me.AliSwag.drink.DrinkManager;
import me.AliSwag.listeners.PlayerConsume;
import me.AliSwag.threads.SoberingThread;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

import java.util.Map;

public class Main extends JavaPlugin {

    static Main main;
    public static PluginManager pm;
    public static Map<Player, InfluencedPlayer> influencedPlayers;

	@Override
	public void onEnable() {
		main = this;
        pm = this.getServer().getPluginManager();

        //Listeners
        pm.registerEvents(new PlayerConsume(), this);

        //Threads
        this.getServer().getScheduler().runTaskTimer(this, new SoberingThread(), 0, 6);

        //Commands
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
