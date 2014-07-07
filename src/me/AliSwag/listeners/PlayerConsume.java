package me.AliSwag.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PlayerConsume implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled =  true)
	public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack itemstack = event.getItem();
        if (itemstack.getType() != Material.POTION) return;
        ItemMeta im = itemstack.getItemMeta();
        if (!im.hasLore()) return;
        List<String> lore = im.getLore();
        if (lore.size() < 4) return;
        if (!ChatColor.stripColor(lore.get(0)).equalsIgnoreCase("drink")) return;
        String drinkName = lore.get(1);
        String alcoholPercentString = lore.get(2);
        double alcoholPercent;
        try {
            alcoholPercent = Double.parseDouble(alcoholPercentString);
        }
        catch (NumberFormatException ex) { return; }
        if (alcoholPercent > 100 || alcoholPercent < 1) return;
        String litresString = lore.get(3);
        long litres;
        try {
            litres = Long.parseLong(litresString);
        }
        catch (NumberFormatException ex) { return; }
        if (litres < 1) return;
        player.sendMessage("You drank the drink " + drinkName + " that contained " + alcoholPercentString + " alcohol, and the drink was " +
                 litresString + " litres!");
	}
}
