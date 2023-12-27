package lc.core.commands;

import lc.core.entidades.Jugador;
import lc.core.entidades.Rango;
import lc.core.entidades.database.RankInfoQuery;
import lc.core.utilidades.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RankCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof ConsoleCommandSender)){
            if(Jugador.getJugador(sender.getName()).isAdmin()){
                Util.sendMessage(sender, "&e&lMine&b&lLC &cSolo desde la consola mivida.");
                return true;
            }
            ((Player) sender).kickPlayer("&4¿Sos o te haces?");
            for(Player player : Bukkit.getOnlinePlayers()){
                if(Jugador.getJugador(player).isAdmin()){
                    Util.sendMessage(player, "&e&lDebug&b&lLC &4"+sender.getName()+"&c: /"+label);
                }
            }
            return true;
        }

        if(command.getName().equalsIgnoreCase("setrank")){
            if(args.length > 1){
                Player player = Bukkit.getPlayer(args[1]);
                if(player == null || !player.isOnline()){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cJugador no encontrado.");
                    return true;
                }
                Rango rango;
                try {
                    rango = Rango.valueOf(args[0].toUpperCase());
                }catch (IllegalArgumentException e){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cRango invalido.");
                    return true;
                }

                Jugador jugador = Jugador.getJugador(player);
                jugador.getRankInfo().setRango(rango);
                if(jugador.isVIP()) jugador.getRankInfo().setDuration(200L);
                RankInfoQuery.savePlayerRango(jugador);
                Util.sendMessage(sender, "&e&lMine&b&lLC &aEstableciste el rango de &e"+jugador.getNombre()+"&a a &e"+ StringUtils.capitalize(jugador.getRankInfo().getRango().name().toLowerCase()) +"&a.");
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &c/setrank [Rango] [Jugador]");
            }
        }
        else if(command.getName().equalsIgnoreCase("togglehiderank")){
            if(args.length > 0){
                Player player = Bukkit.getPlayer(args[0]);
                if(player == null || !player.isOnline()){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cJugador no encontrado.");
                    return true;
                }

                Jugador jugador = Jugador.getJugador(player);
                jugador.getRankInfo().setHideRank(!jugador.getRankInfo().isHideRank());
                RankInfoQuery.savePlayerRango(jugador);
                Util.sendMessage(sender, "&e&lMine&b&lLC "+(jugador.getRankInfo().isHideRank() ? "&aActivaste" : "&CDesactivaste")+" el modo rango escondido de &e"+jugador.getNombre()+"&a.");
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &c/togglehiderank [Jugador]");
            }
        }
        else if(command.getName().equalsIgnoreCase("setpuntos")){
            if(args.length > 1){
                Player player = Bukkit.getPlayer(args[1]);
                if(player == null || !player.isOnline()){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cJugador no encontrado.");
                    return true;
                }
                int puntos;
                try {
                    puntos = Integer.parseInt(args[0]);
                }catch (NumberFormatException e){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cNúmero invalido.");
                    return true;
                }

                Jugador jugador = Jugador.getJugador(player);
                jugador.getRankInfo().setPuntos(puntos);
                RankInfoQuery.savePlayerRango(jugador);
                Util.sendMessage(sender, "&e&lMine&b&lLC &aEstableciste los RankPoints de &e"+jugador.getNombre()+"&a en &e"+ puntos+"&a.");
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &c/setpuntos [PuntosI] [Jugador]");
            }
        }
        else if(command.getName().equalsIgnoreCase("setduration")){
            if(args.length > 1){
                Player player = Bukkit.getPlayer(args[1]);
                if(player == null || !player.isOnline()){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cJugador no encontrado.");
                    return true;
                }
                long duration;
                try {
                    duration = Long.parseLong(args[0]);
                }catch (NumberFormatException e){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cNúmero invalido.");
                    return true;
                }

                Jugador jugador = Jugador.getJugador(player);
                jugador.getRankInfo().setDuration(duration);
                RankInfoQuery.savePlayerRango(jugador);
                Util.sendMessage(sender, "&e&lMine&b&lLC &aEstableciste la duración del rango de &e"+jugador.getNombre()+"&a en &e"+ duration+"&a.");
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &c/setduration [DuraciónL] [Jugador]");
            }
        }
        else if(command.getName().equalsIgnoreCase("setnamecolor")){
            if(args.length > 1){
                Player player = Bukkit.getPlayer(args[1]);
                if(player == null || !player.isOnline()){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cJugador no encontrado.");
                    return true;
                }
                String str = args[0];
                if(!str.startsWith("&")){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cFormato: &xColor&xDecorate");
                }
                Jugador jugador = Jugador.getJugador(player);
                jugador.getRankInfo().setNameColor(str);
                RankInfoQuery.savePlayerRango(jugador);
                Util.sendMessage(sender, "&e&lMine&b&lLC &aEstableciste el color del nombre de &e"+jugador.getNombre()+"&a en &r"+ str+jugador.getNombre()+"&a.");
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &c/setnamecolor [&x&x] [Jugador]");
            }
        }
        return true;
    }
}
