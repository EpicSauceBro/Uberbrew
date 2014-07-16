package me.AliSwag.instances;


import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

import me.AliSwag.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

/**
 * @since 0.1
 */
public class InfluencedPlayer {

    private final FileConfiguration config;
    private final File file;
    private final UUID playerUniqueId;

    public InfluencedPlayer(UUID playerUniqueId) {
        this.playerUniqueId = playerUniqueId;
        file = new File(Main.path + playerUniqueId + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {
                Main.logger.log(Level.SEVERE, "Failed to create a new file for " + playerUniqueId);
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public InfluencedPlayer(UUID playerUniqueId, double alcoholContent) {
        this(playerUniqueId);
        setAlcoholContent(alcoholContent);
    }

    public static InfluencedPlayer load(File file) {
        final UUID playerUniqueId;
        try {
            playerUniqueId = UUID.fromString(file.getName().replaceAll(".yml", ""));
        } catch (IllegalArgumentException ex) {
            return null;
        }
        final FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        return new InfluencedPlayer(playerUniqueId, configuration.getDouble("alcoholContent"));
    }


    public void setAlcoholContent(double alcoholContent) {
        if (alcoholContent > 100) {
            alcoholContent = 100;
        }
        if (alcoholContent < 0) {
            alcoholContent = 0;
        }
        config.set("alcoholContent", alcoholContent);
        save();
    }

    public void save() {
        try {
            config.save(file);
        }
        catch (IOException ex) {
            Main.logger.log(Level.SEVERE, "Failed to save config for " + playerUniqueId);
        }
    }

    public UUID getPlayerUniqueId() {
        return playerUniqueId;
    }
}
