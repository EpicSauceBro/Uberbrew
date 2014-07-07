package me.AliSwag.threads;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ali on 06/07/2014.
 */
public class SoberingThread extends BukkitRunnable {
    @Override
    public void run() {
        for(Player p : AlcoholContentManager.bac.keySet()){
            AlcoholContentManager.removeAlcohol(p, Byte.parseByte("" + (AlcoholContentManager.bac.get(p) - .01)));
        }
    }
}
