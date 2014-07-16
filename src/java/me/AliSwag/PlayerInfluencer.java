package me.AliSwag;

import me.AliSwag.instances.InfluencedPlayer;

import org.bukkit.entity.Player;

import java.io.File;

/**
 * @since 0.1
 */
public class PlayerInfluencer {

    public static void influencePlayer(Player player, double alcoholContent) {
    	new InfluencedPlayer(player.getUniqueId(), alcoholContent);
    }

    public static void loadInfluencedPlayers() {
        final File[] files = new File(Main.path).listFiles();
        for (final File file : files) {
            if (!file.isDirectory()) {
                final InfluencedPlayer player = InfluencedPlayer.load(file);
                if (player != null) {
                    Main.influencedPlayers.put(player.getPlayerUniqueId(), player);
                }
            }
        }
    }
}
