package me.AliSwag.threads;

import me.AliSwag.InfluencedPlayer;

/**
 * @since 0.1
 */
public class AlcoholContentManager {

    public static void addAlcohol(InfluencedPlayer player, byte alcoholToAdd) {
    	player.setAlcoholContent((byte) (player.getAlcoholContent() + alcoholToAdd));
    }

}
