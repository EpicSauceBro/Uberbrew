package me.AliSwag.commands;

import java.util.ArrayList;
import java.util.List;

import me.AliSwag.drink.Drink;
import me.AliSwag.drink.DrinkManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DrinkCommand implements CommandExecutor {

    String addHelpMessage = "Usage: /uberbrew add <Alchohol Percent> <DisplayName> <Name>";
    String listHelpMessage = "Usage: /uberbrew list";
    String infoHelpMessage = "Usage: /uberbrew info [Drink id]";
    String giveHelpMessage = "Usage: /uberbrew give <Drink Name> <Litres>";
    String noPermissionMessage = "Sorry, you do not have permission for this! Contact a server admin if you believe you " +
            "should!";
    String consoleCannotRunMessage = "You cannot run this part of the command, sorry!";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("uberbrew")) {
            if (args.length < 1) {
                sender.sendMessage("See /uberbrew help!");
                return true;
            }
            if (args[0].equalsIgnoreCase("help")) {
                if (!sender.hasPermission("uberbrew.help")) {
                    sender.sendMessage(noPermissionMessage);
                    return true;
                }
                sender.sendMessage(addHelpMessage);
                sender.sendMessage(listHelpMessage);
                sender.sendMessage(infoHelpMessage);
                sender.sendMessage(giveHelpMessage);
                return true;
            }
			if(args[0].equalsIgnoreCase("add")) {
                if (!sender.hasPermission("uberbrew.add")) {
                    sender.sendMessage(noPermissionMessage);
                    return true;
                }
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
                if (args.length < 1 || args.length > 3) {
                    sender.sendMessage(infoHelpMessage);
                    return true;
                }
                if(args.length == 2) {
                    Drink d = DrinkManager.getDrink(args[1]);
                    sender.sendMessage(ChatColor.GOLD + "---------- " + ChatColor.RESET + d.displayName + ChatColor.GOLD + " ----------");
                    sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + ChatColor.WHITE + d.alcoholContent + "%");
                    sender.sendMessage(ChatColor.DARK_GRAY + "Id: " + ChatColor.WHITE + d.name);
                    return true;
                }else if(args.length == 1) {
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        List<String> lore = player.getItemInHand().getItemMeta().getLore();
                        if (lore == null || lore.size() < 2) {
                            sender.sendMessage(ChatColor.RED + "Have a valid drink in your hand!");
                            return true;
                        }
                        if(lore.get(0).equalsIgnoreCase("drink")) {
                            Drink drink = DrinkManager.getDrink(lore.get(1));
                            sender.sendMessage(ChatColor.GOLD + "---------- " + ChatColor.RESET + drink.displayName + ChatColor.GOLD + " ----------");
                            sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + drink.alcoholContent + "%");
                            sender.sendMessage(ChatColor.DARK_GRAY + "Id: " + ChatColor.WHITE + drink.name);
                            return true;
                        } else {
                            sender.sendMessage(consoleCannotRunMessage);
                            return true;
                        }
                    }
                }
			}
			if(args[0].equalsIgnoreCase("give")){
                if (!sender.hasPermission("uberbrew.give")) {
                    sender.sendMessage(noPermissionMessage);
                    return true;
                }
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
                long litres;
                try {
                    litres = Long.parseLong(args[2]);
                }
                catch (NumberFormatException ex) {
                    sender.sendMessage(ChatColor.RED + "Litres cannot be negative/invalid!");
                    return true;
                }
                if (drink == null || litres < 1) {
                    sender.sendMessage("Enter a valid drink/litre amount!");
                    return true;
                }
                DrinkManager.addDrinkToInventory(drink, player, args[2]);
			}
			return true;
		}

		return true;
	}

}
