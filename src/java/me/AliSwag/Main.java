package me.AliSwag;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.AliSwag.commands.DrinkCommand;
import me.AliSwag.drink.DrinkManager;
import me.AliSwag.listeners.PlayerConsume;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.evilmidget38.NameFetcher;
import com.evilmidget38.UUIDFetcher;

public class Main extends JavaPlugin {

    static Main main;
    public static PluginManager pm;
    public static Map<UUID, InfluencedPlayer> influencedPlayers;
    public static boolean serverOnline;
    public static Logger logger;
    
    @Override
    public void onLoad() {
    	logger = getLogger();
    }
    
    public static InfluencedPlayer getInfluencedPlayer(UUID playerUUID) {
    	return influencedPlayers.get(playerUUID);
    }

	@Override
	public void onEnable() {
		serverOnline = Bukkit.getServer().getOnlineMode();

        pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerConsume(), this);

		getCommand("uberbrew").setExecutor(new DrinkCommand());

        getConfig();
        main = this;
        DrinkManager.loadDrinks();
	}
	
	@Override
	public void onDisable() {}

    public static FileConfiguration getMainConfig() {
        return main.getConfig();
    }
    
    public static UUID getPlayerUniqueId(Player player) {
    	String playerName = player.getName();
    	if (serverOnline) {
    		return player.getUniqueId();
    	} else {
    		try {
				return UUIDFetcher.getUUIDOf(playerName);
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Error while retreiving UUID for " + playerName + "!");
				return null;
			}
    	}
    }
    
    public static String getPlayerName(UUID playerUUID) {
    	try {
			return new NameFetcher(Arrays.asList(playerUUID)).call().get(playerUUID);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to retrieve name for " + playerUUID + "!");
			return null;
		}
    }

    public static void saveMainConfig() {
        main.saveConfig();
    }

    public static void reloadMainConfig() {
        main.reloadConfig();
    }

}
