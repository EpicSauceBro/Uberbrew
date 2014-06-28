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
				if(args.length == 6 && args[1].equalsIgnoreCase("drink")){
					DrinkManager.addDrink(Integer.parseInt(args[2]), ChatColor.translateAlternateColorCodes('&', args[3]), args[4], args[5]);
					sender.sendMessage(ChatColor.GREEN + "Successfully added the drink: "+ ChatColor.translateAlternateColorCodes('&', args[3]));
					return true;
				}if(args.length == 3 && args[1].equalsIgnoreCase("category")){
					DrinkManager.drinkCategorys.add(args[2]);
					return true;
				}
				sender.sendMessage(ChatColor.RED + "Invaild arguments! Use: /drink add <AlchohlPercent> <DisplayName> <Name>");
				return true;
			}
			if(args[0].equalsIgnoreCase("list")){
				String message = ChatColor.DARK_AQUA + "Drinks: " + ChatColor.RESET;
				for(String s : DrinkManager.getDrinksNames()){
					message += s + ", ";
				}
				sender.sendMessage(message);
				return true;
			}
			if(args[0].equalsIgnoreCase("info") && args.length == 2){
				Drink d = DrinkManager.getDrink(args[1]);
				sender.sendMessage(ChatColor.AQUA + "---------- " + d.displayName + ChatColor.AQUA + " ----------");
				sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + d.alcoholContent + "%");
				sender.sendMessage(ChatColor.DARK_BLUE + "Category: " + d.type);
			}
			if(args[0].equalsIgnoreCase("give")){
				Player player = (Player) sender;
				
				ItemStack is = new ItemStack(Material.GLASS_BOTTLE);
				
				ItemMeta im = is.getItemMeta();
				
				List<String> lore = im.getLore();
				
				lore.add("Drink");
				lore.add(DrinkManager.getDrink(args[1]).alcoholContent + "%");
				
				im.setLore(lore);
				
				is.setItemMeta(im);
				
				player.getInventory().addItem(is);
			}
			return false;
		}
		
		return false;
	}

}
