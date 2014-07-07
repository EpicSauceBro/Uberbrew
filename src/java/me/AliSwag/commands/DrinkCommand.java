package me.AliSwag.commands;

import java.util.List;

import me.AliSwag.instances.Drink;
import me.AliSwag.managers.AlcoholContentManager;
import me.AliSwag.managers.DrinkManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DrinkCommand implements CommandExecutor {

    private String addHelpMessage = "Usage: /uberbrew add <Name> <DisplayName> <Alcohol Percent>";
    private String listHelpMessage = "Usage: /uberbrew list";
    private String infoHelpMessage = "Usage: /uberbrew info [Drink id]";
    private String giveHelpMessage = "Usage: /uberbrew give <Drink Name> <Litres>";
    private String editHelpMessage = "Usage: /uberbrew edit <Name> <Alcohol Percent> <DisplayName> . Put 'same' if you want it to stay the same'";
    private String noPermissionMessage = ChatColor.RED + "Sorry, you do not have permission for this! Contact a server admin if you believe you " +
            "should!";
    private String invaildDrink = ChatColor.RED + "That drink does not exist!";
    private String consoleCannotRunMessage = ChatColor.RED + "You cannot run this part of the command, sorry!";
    private String drinkAlreadyExists = ChatColor.RED + "This drink already exists";

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
                sender.sendMessage("[§6Uberbrew Help§f]");
                sender.sendMessage(addHelpMessage);
                sender.sendMessage(listHelpMessage);
                sender.sendMessage(infoHelpMessage);
                sender.sendMessage(giveHelpMessage);
                sender.sendMessage(editHelpMessage);
                return true;
            }
			if(args[0].equalsIgnoreCase("add")) {
                if (!sender.hasPermission("uberbrew.add")) {
                    sender.sendMessage(noPermissionMessage);
                    return true;
                }
                if(DrinkManager.getDrink(args[1]) != null) {
                    sender.sendMessage(drinkAlreadyExists);
                    return true;
                }
                if (args.length < 2 || args.length > 4) {
                    sender.sendMessage(ChatColor.RED + "Invaild arguments! " + addHelpMessage);
                    return true;
                }


                byte alcoholPercent;
                try {
                    alcoholPercent = Byte.parseByte(args[3]);
                }
                catch (NumberFormatException ex) {
                    sender.sendMessage(ChatColor.RED + "You must use a valid integer!");
                    return true;
                }
                if (alcoholPercent > 100 || alcoholPercent < 0) {
                    sender.sendMessage(ChatColor.RED + "Please use a integer from 0 through 100!");
                    return true;
                }
                DrinkManager.addDrink(alcoholPercent, args[2], args[1]);
                sender.sendMessage(ChatColor.GREEN + "Successfully added the drink: "+ ChatColor.translateAlternateColorCodes('&', args[2]));
                Drink d = DrinkManager.getDrink(args[1]);
                sender.sendMessage(ChatColor.GOLD + "---------- " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', d.displayName) + ChatColor.GOLD + " ----------");
                sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + ChatColor.WHITE + d.alcoholContent + "%");
                sender.sendMessage(ChatColor.DARK_GRAY + "Id: " + ChatColor.WHITE + d.name);

				return true;
			}
			if(args[0].equalsIgnoreCase("list")) {
				String message = "-------------- " + ChatColor.DARK_AQUA + "Drinks "+ ChatColor.WHITE +" --------------" + ChatColor.RESET;
				for(Drink d : DrinkManager.drinks){
                            message += ", " + "\n" + ChatColor.translateAlternateColorCodes('&', d.displayName) + "§f | " + ChatColor.DARK_GRAY + d.name;
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
                    sender.sendMessage(ChatColor.GOLD + "---------- " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', d.displayName) + ChatColor.GOLD + " ----------");
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
                            sender.sendMessage(ChatColor.GOLD + "---------- " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', drink.displayName) + ChatColor.GOLD + " ----------");
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
                    sender.sendMessage(ChatColor.RED + "Enter a valid drink/litre amount!");
                    return true;
                }
                DrinkManager.addDrinkToInventory(drink, player, args[2]);
			}

            if(args[0].equalsIgnoreCase("edit")){
                if(!isDrinkValid(args[1])){
                    sender.sendMessage(invaildDrink);
                    return true;
                }
                if (!sender.hasPermission("uberbrew.edit")) {
                    sender.sendMessage(noPermissionMessage);
                    return true;
                }
                if (!(args.length == 4)) {
                    sender.sendMessage(editHelpMessage);
                    return true;
                }

                Drink drink = DrinkManager.getDrink(args[1]);

                byte alcoholPercent;
                try {
                    alcoholPercent = Byte.parseByte(args[3]);
                }
                catch (NumberFormatException ex) {
                    sender.sendMessage(ChatColor.RED + "You must use a valid integer!");
                    return true;
                }
                if (alcoholPercent > 100 || alcoholPercent < 0) {
                    sender.sendMessage(ChatColor.RED + "Please use a integer from 0 through 100!");
                    return true;
                }
                int args3;
                String args2;

                if(args[2].equalsIgnoreCase("same")){
                    args2 = DrinkManager.getDrink(args[1]).displayName;
                }else{
                    args2 = args[2];
                }
                if(args[3].equalsIgnoreCase("same")){
                    args3 = DrinkManager.getDrink(args[1]).alcoholContent;
                }else{
                    args3 = Integer.parseInt(args[3]);
                }

                DrinkManager.editDrink(args3, args2, args[1]);

                sender.sendMessage(ChatColor.GREEN + "Successfully edited the drink: "+ ChatColor.translateAlternateColorCodes('&', drink.displayName));
                sender.sendMessage(ChatColor.GOLD + "---------- " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', drink.displayName) + ChatColor.GOLD + " ----------");
                sender.sendMessage(ChatColor.YELLOW + "Alcohol Content: " + ChatColor.WHITE + drink.alcoholContent + "%");
                sender.sendMessage(ChatColor.DARK_GRAY + "Id: " + ChatColor.WHITE + drink.name);

            }
            if(args[0].equalsIgnoreCase("bac")){
                sender.sendMessage("Blood Alcohol Content: " + String.valueOf(AlcoholContentManager.impairedPlayers.get((Player) sender).getAlcoholContent()) + "%");
            }
            else{
                sender.sendMessage(String.format(ChatColor.RED + "/uberbrew %s does not exist!", args[0]));
                sender.sendMessage("[§6Uberbrew Help§f]");
                sender.sendMessage(addHelpMessage);
                sender.sendMessage(listHelpMessage);
                sender.sendMessage(infoHelpMessage);
                sender.sendMessage(giveHelpMessage);
                sender.sendMessage(editHelpMessage);
                return true;
            }
			return true;
		}
		return true;
	}

    public boolean isDrinkValid(String Id){
        for(Drink d : DrinkManager.drinks){
            if(d.name.equalsIgnoreCase(Id)){
                return true;
            }
        }
        return false;
    }
}
