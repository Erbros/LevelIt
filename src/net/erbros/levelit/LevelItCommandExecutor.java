package net.erbros.levelit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;


public class LevelItCommandExecutor implements CommandExecutor {
    private LevelIt plugin;
    
    LevelItCommandExecutor (LevelIt instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,
            String[] args) {
    
        if(sender.hasPermission("levelit.use") == false) {
            sender.sendMessage("You do not have the perm: levelit.use");
            return true;
        }
        
        if(args.length > 2) {
            if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("set")) {
                if(args[1].equalsIgnoreCase("level") || args[1].equalsIgnoreCase("lvl") || args[1].equalsIgnoreCase("l")) {
                    Player target = null;
                    if(sender instanceof Player)
                        target = (Player) sender;
                    // check if at least another arg.
                    if(args.length > 3) {
                        // 3 args? is one a player?
                        if (plugin.getServer().getPlayer(args[2]) instanceof Player ) {
                            target = plugin.getServer().getPlayer(args[2]);
                        } else {
                            sender.sendMessage("Player " + args[2] + " not found.");
                            return true;
                        }
                    } else if(sender instanceof ConsoleCommandSender) {
                        sender.sendMessage("You need to give the levels to a player");
                        return true;
                    }
                    int level;
                    // if a player was found, then the third should be a number, if not then the second.
                    String arg_to_check;
                    if(args.length == 3) {
                        arg_to_check = args[2];
                    } else {
                        arg_to_check = args[3];
                    }
                    // Okay, is this an integer?
                    try {
                        level = Integer.parseInt(arg_to_check);
                    } catch (NumberFormatException nfe) {
                        sender.sendMessage("You need to provide an integer");
                        return true;
                    }
                    
                    // return success and give levels.
                    // but did we use set or add?
                    if(args[0].equalsIgnoreCase("add")) {
                        level = target.getLevel() + level;
                    }
                    target.setLevel(level);
                    sender.sendMessage(target.getName() + " set to level " + target.getLevel());
                    return true;
                    
                } else if (args[1].equalsIgnoreCase("xp") || args[1].equalsIgnoreCase("experience") || args[1].equalsIgnoreCase("x")) {
                    Player target = null;
                    if(sender instanceof Player) {
                        target = (Player) sender;
                    }
                    // check if at least another arg.
                    if(args.length > 3) {
                        // 3 args? is one a player?
                        if (plugin.getServer().getPlayer(args[2]) instanceof Player ) {
                            target = plugin.getServer().getPlayer(args[2]);
                        } else {
                            sender.sendMessage("Player " + args[2] + " not found.");
                            return true;
                        }
                    } else if(sender instanceof ConsoleCommandSender) {
                        sender.sendMessage("You need to give the xp to a player");
                        return true;
                    }
                    int xp;
                    // if a player was found, then the third should be a number, if not then the second.
                    String arg_to_check;
                    if(args.length == 3) {
                        arg_to_check = args[2];
                    } else {
                        arg_to_check = args[3];
                    }
                    // Okay, is this an integer?
                    try {
                        xp = Integer.parseInt(arg_to_check);
                    } catch (NumberFormatException nfe) {
                        sender.sendMessage("You need to provide an integer");
                        return true;
                    }
                    // Give out the xp.
                    // But did we use set or add?
                    if(args[0].equalsIgnoreCase("add")) {
                        xp = target.getTotalExperience() + xp;
                    }
                    target.setTotalExperience(xp);
                    sender.sendMessage(target.getName() + " set to xp " + target.getTotalExperience());
                    return true;
                }
            }
        }

        sender.sendMessage("" + label + " add level [user] <level> - Add levels");
        sender.sendMessage("" + label + " add xp [user] <xp> - Add xp");    
        sender.sendMessage("" + label + " set level [user] <level> - Set levels");
        sender.sendMessage("" + label + " set xp [user] <xp> - Set xp");
        return true;
    }
    
    
    /*
    public int getXpBetweenLevels (int fromLvl, int toLvl, Player player) {
        int i;
        // first add the xp missing to level up once.
        int xp = player.getExpToLevel();
        for(i = fromLvl+1; i <= toLvl; i++) {
            xp += xpNeededForLevel(i);
        }
        return xp;
    }
    
    public int xpNeededForLevel (int lvl) {
        // How much xp is needed to go from lvl-1 to lvl?
        if(lvl <= 15) {
            return 17;
        } else if (lvl <= 30) {
            return 17 + (lvl - 15) * 3;
        } else {
            return 62 + (lvl - 30) * 7;
        }
    }
    */
    
}
