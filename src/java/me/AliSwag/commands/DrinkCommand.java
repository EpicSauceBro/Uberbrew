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

    String addHelpMessage = "Usage: /uberbrew add <Alchohl Percent> <DisplayName> <Name>";
    String listHelpMessage = "Usage: /uberbrew list";
    String infoHelpMessage = "Usage: /uberbrew info [Drink id]";
    String giveHelpMessage = "Usage: /uberbrew give <Drink Name> <Litres>";
    String consoleCannotRunMessage = "You cannot run this part of the command, sorry!";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("uberbrew")){
            if (args.length < 1) {
                sender.sendMessage("See /uberbrew help!");
                return true;
            }
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(addHelpMessage);
                sender.sendMessage(listHelpMessage);
                sender.sendMessage(infoHelpMessage);
                sender.sendMessage(giveHelpMessage);
                return true;
            }
			if(args[0].equalsIgnoreCase("add")) {
                if (args.length < 2 || args.length > 4) {
                    sender.sendMessage(ChatColor.RED + "Invaild arguments! " + addHelpMessage);
                    return true;
                }
				DrinkManager.addDrink(Integer.parseInt(args[1]), ChatColor.translateAlternateColorCodes('&', args[2]), args[3]);
				sender.sendMessage(ChatColor.GREEN + "Successfully added the drink: "+ ChatColor.translateAlternateColorCodes('&', args[3]));
				return true;
			}
			if(args[0].equalsIgnoreCase("list")) {
				String message = ChatColor.DARK_AQUA + "Drinks: " + ChatColor.RESET;
				for(String s : DrinkManager.getDrinksNames()){
					message += ", " + "\n" + s;
				}
				sender.sendMessage(message.replaceFirst(", ", ""));
				return true;
			}
			if(args[0].equalsIgnoreCase("info")){
                if (args.length < 2 || args.length > 3) {
                    sender.sendMessage(infoHelpMessage);
                    return true;
                }
                if(args.length == 2) {
                    Drink d = DrinkManager.getDrink(args[1]);
                    sender.sendMessage(ChatColor.AQUA + "---------- " + d.displayName + ChatColor.AQUA + " ----------");
                    sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + d.alcoholContent + "%");
                }else if(args.length == 1){
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        List<String> lore = player.getItemInHand().getItemMeta().getLore();
                        if (lore == null || lore.size() < 2) {
                            sender.sendMessage(ChatColor.RED + "Have a valid drink in your hand!");
                            return true;
                        }
                        if(lore.get(0).equalsIgnoreCase("drink")){
                            Drink drink = DrinkManager.getDrink(lore.get(1));
                            sender.sendMessage(ChatColor.AQUA + "---------- " + drink.displayName + ChatColor.AQUA + " ----------");
                            sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + drink.alcoholContent + "%");
                        } else {
                            sender.sendMessage(consoleCannotRunMessage);
                        }
                    }
                }
			}
			if(args[0].equalsIgnoreCase("give")){
                if (!(sender instanceof Player)) {
                    sender.sendMessage(consoleCannotRunMessage);
                    return true;
                }
                if (args.length < 2 || args.length > 3) {
                    sender.sendMessage(giveHelpMessage);
                    return true;
                }
				Player player = (Player) sender;
                Drink drink = DrinkManager.getDrink(args[1]);
                DrinkManager.addDrinkToInventory(drink, player, args[2]);
			}
			return false;
		}
		
		return false;
	}

}
