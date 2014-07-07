package me.AliSwag;

import me.AliSwag.instances.InfluencedPlayer;

import org.bukkit.entity.Player;

/**
 * @since 0.1
 */
public class PlayerInfluencer {

    public static void influencePlayer(Player player, byte alcoholContent) {
    	new InfluencedPlayer(player.getUniqueId(), alcoholContent);
    }


}
