package pl.Pijok.JailPlus.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.Pijok.JailPlus.Main;
import pl.Pijok.JailPlus.jail.Jail;
import pl.Pijok.JailPlus.utils.ChatUtils;
import pl.Pijok.JailPlus.utils.JailUtils;

public class JailCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("stworz")){
                if(!player.hasPermission("jailplus.admin")){
                    ChatUtils.sendMessage(player, "&cNie masz dostepu do tej komendy!");
                    return true;
                }

                JailUtils.addJail(player.getLocation(), args[1]);
                ChatUtils.sendMessage(player, "&aStworzono wiezienia z nazwa " +  args[1]);
                return true;
            }
            if(args[0].equalsIgnoreCase("usun")){
                if(!player.hasPermission("jailplus.admin")){
                    ChatUtils.sendMessage(player, "&cNie masz dostepu do tej komendy!");
                    return true;
                }

                if(!JailUtils.jailExists(args[1])){
                    ChatUtils.sendMessage(player, "&cTakie wiezienie nie istnieje!");
                    return true;
                }

                JailUtils.removeJail(args[1]);
                ChatUtils.sendMessage(player, "&cUsunieto wiezienie z nazwa" +  args[1]);
                return true;
            }
            if(args[0].equalsIgnoreCase("zwolnij")){
                if(!player.hasPermission("jailplus.free")){
                    ChatUtils.sendMessage(player, "&cNie masz dostepu do tej komendy!");
                    return true;
                }

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null || !target.isOnline()){
                    ChatUtils.sendMessage(player, "&cTen gracz jest offline");
                    return true;
                }

                if(!JailUtils.isInJail(target)){
                    ChatUtils.sendMessage(player, "&cTen gracz nie jest w wiezieniu!");
                    return true;
                }

                JailUtils.removeFromJail(target);
                ChatUtils.sendMessage(player, "&aUwolniono wieznia &e" + target.getName());
                return true;
            }
            if(args[0].equalsIgnoreCase("info")){
                if(!player.hasPermission("jailplus.info")){
                    ChatUtils.sendMessage(player, "&cNie masz dostepu do tej komendy!");
                    return true;
                }

                if(!JailUtils.jailExists(args[1])){
                    ChatUtils.sendMessage(player, "&cTo wiezienie nie istnieje!");
                    return true;
                }

                Jail jail = Main.jails.get(args[1]);

                ChatUtils.sendMessage(player, "&a&lLokacja:");
                ChatUtils.sendMessage(player, "&7- X:" + jail.getLocation().getX());
                ChatUtils.sendMessage(player, "&7- Y:" + jail.getLocation().getY());
                ChatUtils.sendMessage(player, "&7- Z:" + jail.getLocation().getZ());
                ChatUtils.sendMessage(player, "&7- Swiat:" + jail.getLocation().getWorld().getName());
                ChatUtils.sendMessage(player, "&a&lLiczba osadzonych: &e" + jail.getPrisoners().size());
                return true;
            }
        }

        if(args.length == 5){
            if(player.hasPermission("jailplus.ban")){
                
            }
        }

        ChatUtils.sendMessage(player, "&7/" + label + " stworz <nazwa>"); //Done
        ChatUtils.sendMessage(player, "&7/" + label + " usun <nazwa>"); //Done
        ChatUtils.sendMessage(player, "&7/" + label + " zwolnij <nick>"); //Done
        ChatUtils.sendMessage(player, "&7/" + label + " info <nazwa>");
        ChatUtils.sendMessage(player, "&7/" + label + " wrzuc <nick> <nazwa wiezienia> <czas> <powod>");

        return true;
    }
}
