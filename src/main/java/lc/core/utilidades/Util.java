package lc.core.utilidades;

import com.connorlinfoot.titleapi.TitleAPI;
import lc.core.entidades.Jugador;
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
        sender.sendMessage(color(message));
    }

    public static void console(String message){
        Bukkit.getConsoleSender().sendMessage(color(message));
    }

    public static void sendTitle(Player p, int fadeIn, int stay, int fadeOut, String title, String subTitle){
        TitleAPI.sendTitle(p, fadeIn, stay, fadeOut, color(title), color(subTitle));
    }

    public static List<String> getColoredList(Jugador jugador, List<String> list){
        List<String> coloredList = new ArrayList<>();
        for(String str : list){
            if(jugador != null)
                coloredList.add(color("&r"+setPlaceholder(jugador, str)));
            else coloredList.add(color("&r"+str));
        }
        return coloredList;
    }

    public static List<String> getCenteredList(Jugador jugador, List<String> list){
        List<String> ret = new ArrayList<>();
        for(String str : list){
            if(jugador != null)
                ret.add(color("&r"+setPlaceholder(jugador, str)));
            else ret.add(color("&r"+str));
        }
        return ret;
    }

    public static String setPlaceholder(Jugador jugador, String string){
        return string.replaceAll("%prefix%", jugador.getRankInfo()
                .getRango().getAsPrefix()).replaceAll("%name%", jugador.getNombre());
    }
}
