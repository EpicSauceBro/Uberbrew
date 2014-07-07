package me.AliSwag.threads;

import me.AliSwag.InfluencedPlayer;

import org.bukkit.entity.Player;

/**
 * @since 0.1
 */
public class PlayerInfluencer {

    public static void influencePlayer(Player player, byte alcoholContent) {
    	new InfluencedPlayer(player.getUniqueId(), alcoholContent);
    }


}
