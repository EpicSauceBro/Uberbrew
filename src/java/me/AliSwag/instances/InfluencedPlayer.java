package me.AliSwag.instances;


import java.util.UUID;

import me.AliSwag.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @since 0.1
 */
public class InfluencedPlayer {

    private double alcoholContent = 0;
    private UUID playerUUID = null;

    @SuppressWarnings("unused")
    @Deprecated
    private InfluencedPlayer() {}
    
    public InfluencedPlayer(UUID playerUUID) {
    	this(playerUUID, (Integer) 0);
    }

    public InfluencedPlayer(UUID playerUUID, double alcoholContent) {
    	if (playerUUID == null) {
    		return;
    	}
        this.playerUUID = playerUUID;
        setAlcoholContent(alcoholContent);
    }

    public void resetAlcoholContent() {
        alcoholContent = 0;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public void setAlcoholContent(double alcoholContent) {
        if (alcoholContent < 0) {
            alcoholContent = 0;
        }
        if (alcoholContent > 100) {
            alcoholContent = 100;
        }

        this.alcoholContent = alcoholContent;
        Main.influencedPlayers.put(playerUUID, this);
    }
}
