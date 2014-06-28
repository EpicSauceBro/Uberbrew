package me.AliSwag.commands;

import java.util.List;

import me.AliSwag.drink.Drink;
import me.AliSwag.drink.DrinkManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DrinkCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("uberbrew")){
			if(args[0].equalsIgnoreCase("add")){
				if(args.length == 4 ){
					DrinkManager.addDrink(Integer.parseInt(args[1]), ChatColor.translateAlternateColorCodes('&', args[2]), args[3]);
					sender.sendMessage(ChatColor.GREEN + "Successfully added the drink: "+ ChatColor.translateAlternateColorCodes('&', args[3]));
					return true;
				}
				sender.sendMessage(ChatColor.RED + "Invaild arguments! Use: /drink add <AlchohlPercent> <DisplayName> <Name>");
				return true;
			}
			if(args[0].equalsIgnoreCase("list")){
				String message = ChatColor.DARK_AQUA + "Drinks: " + ChatColor.RESET;
				for(String s : DrinkManager.getDrinksNames()){
					message += "\n" + s + ", ";
				}
				sender.sendMessage(message);
				return true;
			}
			if(args[0].equalsIgnoreCase("info")){
                if(args.length == 2) {
                    Drink d = DrinkManager.getDrink(args[1]);
                    sender.sendMessage(ChatColor.AQUA + "---------- " + d.displayName + ChatColor.AQUA + " ----------");
                    sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + d.alcoholContent + "%");
                }else if(args.length == 1){
                    if(sender instanceof Player){
                        Player p = (Player) sender;
                        if(p.getItemInHand().getItemMeta().getLore().get(0).equalsIgnoreCase("drink")){
                            Drink d = DrinkManager.getDrink(p.getItemInHand().getItemMeta().getLore().get(1));
                            sender.sendMessage(ChatColor.AQUA + "---------- " + d.displayName + ChatColor.AQUA + " ----------");
                            sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + d.alcoholContent + "%");
                        }
                    }
                }
			}
			if(args[0].equalsIgnoreCase("give")){
				Player player = (Player) sender;
                Drink drink = DrinkManager.getDrink(args[1]);
                DrinkManager.addDrinkToInventory(drink, player, args[2]);
			}
			return false;
		}
		
		return false;
	}

}
