package me.AliSwag.managers;

import me.AliSwag.instances.InfluencedPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 0.1
 */
public class AlcoholContentManager {

    public static Map<Player,InfluencedPlayer> impairedPlayers = new HashMap<Player, InfluencedPlayer>();

    public static void addAlcohol(InfluencedPlayer player, Byte alcoholToAdd) {
    	player.setAlcoholContent(Byte.parseByte("" + player.getAlcoholContent() + alcoholToAdd));
        if(!impairedPlayers.containsKey(player)){
            impairedPlayers.put(player.getPlayer(), player);
        }
    }
    public static InfluencedPlayer getOrSetInfluencedPlayer(Player player){
        if(impairedPlayers.containsKey(player)) {
            return impairedPlayers.get(player);
        }else{
            InfluencedPlayer ip = new InfluencedPlayer(player.getUniqueId());
            impairedPlayers.put(player, ip);
            return ip;
        }
    }
}
