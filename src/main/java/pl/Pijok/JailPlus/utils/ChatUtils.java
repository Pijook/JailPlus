package pl.Pijok.JailPlus.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static void sendMessage(Player player, String message){
        message = ChatColor.translateAlternateColorCodes('&', message);
        player.sendMessage(message);
    }
}
