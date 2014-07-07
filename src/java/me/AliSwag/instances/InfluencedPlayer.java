package me.AliSwag.instances;


import java.util.UUID;

import me.AliSwag.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @since 0.1
 */
public class InfluencedPlayer {

    private Byte alcoholContent = 0;
    private UUID playerUUID = null;

    @SuppressWarnings("unused")
    @Deprecated
    private InfluencedPlayer() {}
    
    public InfluencedPlayer(UUID playerUUID) {
    	this(playerUUID, (byte) 0);
    }

    public InfluencedPlayer(UUID playerUUID, byte alcoholContent) {
    	if (playerUUID == null) {
    		return;
    	}
        this.playerUUID = playerUUID;
        setAlcoholContent(alcoholContent);
    }

    public void resetAlcoholContent() {
        alcoholContent = 0;
    }

    public byte getAlcoholContent() {
        return alcoholContent;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public void setAlcoholContent(byte alcoholContent) {
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
