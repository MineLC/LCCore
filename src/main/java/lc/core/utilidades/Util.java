package lc.core.utilidades;

import com.connorlinfoot.titleapi.TitleAPI;
import lc.core.entidades.Jugador;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void sendMessage(CommandSender sender, String message){
        if(sender instanceof Player) sender.sendMessage(color(setPlaceholder((Player) sender, message)));
        else sender.sendMessage(color(message));
    }

    public static void console(String message){
        Bukkit.getConsoleSender().sendMessage(color(message));
    }

    public static void sendTitle(Player p, int fadeIn, int stay, int fadeOut, String title, String subTitle){
        TitleAPI.sendTitle(p, fadeIn, stay, fadeOut, color(setPlaceholder(p, title)), color(setPlaceholder(p, subTitle)));
    }

    public static List<String> getColoredList(Player jugador, List<String> list){
        List<String> coloredList = new ArrayList<>();
        for(String str : list){
            if(jugador != null)
                coloredList.add(color("&r"+setPlaceholder(jugador, str)));
            else coloredList.add(color("&r"+str));
        }
        return coloredList;
    }

    public static List<String> getCenteredList(Player jugador, List<String> list){
        List<String> ret = new ArrayList<>();
        for(String str : list){
            if(jugador != null)
                ret.add(color("&r"+setPlaceholder(jugador, str)));
            else ret.add(color("&r"+str));
        }
        return ret;
    }

    public static String setPlaceholder(Player jugador, String string){
        return PlaceholderAPI.setPlaceholders(jugador, string);
    }
}
