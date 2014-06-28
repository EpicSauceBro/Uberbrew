package me.AliSwag.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerConsume implements Listener {
	@EventHandler
	public void onPICE(PlayerItemConsumeEvent e){
		if(e.getItem().getItemMeta().getLore().contains("Drink")){
			e.getPlayer().sendMessage("U drank shit");
		}
	}
}
