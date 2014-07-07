package me.AliSwag.threads;

import me.AliSwag.drink.Drink;
import org.bukkit.entity.Player;
import me.AliSwag.Main;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 0.1
 */
public class AlcoholContentManager {

    public static Map<Player, Byte> bac = new HashMap<Player, Byte>();

    public static void addAlcohol(Player player, Drink drink, double litersDrank) {

        double alcoholicLitersDrank = (litersDrank * (drink.alcoholContent * .01));

        Byte alcoholContent = Byte.parseByte("" + alcoholicLitersDrank / 5.5);

        if(bac.containsKey(player)){
            bac.replace(player, alcoholContent);
        }else{
            bac.put(player, alcoholContent);
        }
    }

    public static void removeAlcohol(Player p, double amount){
        if(bac.containsKey(p)){
            bac.replace(p, Byte.parseByte("" +( bac.get(p) - amount)));
        }
    }

}
