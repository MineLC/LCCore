package lc.core.commands;

import lc.core.entidades.Jugador;
import lc.core.entidades.RangoInfo;
import lc.core.entidades.database.LCoinsQuery;
import lc.core.entidades.database.RankInfoQuery;
import lc.core.utilidades.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rango implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            if(sender instanceof Player){

                Jugador j = Jugador.getJugador((Player) sender);

                rankInfo(sender, null);
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &aTienes 69.000.000.000 LCoins.");
            }
            return true;
        }
        if(sender instanceof Player && !Jugador.getJugador((Player) sender).isHelper()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cNo puedes ver la información de otro jugador.");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null || !player.isOnline()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cJugando no encontrado.");
            return true;
        }
        Jugador jugador = Jugador.getJugador(player);

        rankInfo(sender, jugador);
        return true;
    }

    public void rankInfo(CommandSender sender, Jugador t){
        if(sender instanceof Player && t == null){
            Jugador jugador = Jugador.getJugador((Player) sender);
            RangoInfo rankInfo = jugador.getRankInfo();
            Util.sendMessage(sender, "&c&m================&r&cRango&c&m================");
            Util.sendMessage(sender, "&e  Nombre: &a" + StringUtils.capitalize(rankInfo.getRango().name().toLowerCase()));
            String p = "&e  Prejifo: &r";
            if(rankInfo.getRango() == lc.core.entidades.Rango.DEFAULT) p += "&7"+jugador.getNombre();
            else p += rankInfo.getRango().getAsPrefix();
            Util.sendMessage(sender, p);
            Util.sendMessage(sender, "&e  NameColor: &r" + rankInfo.getNameColor()+jugador.getNombre());
            String dur = rankInfo.getDuration() == 0 ? "&cPermanente" : "&a"+rankInfo.getDuration();
            Util.sendMessage(sender, "&e  Duración: "+dur);
            Util.sendMessage(sender, "&c&m=====================================");
            Util.sendMessage(sender, "&e  Puntos: &a" + rankInfo.getPuntos());
            Util.sendMessage(sender, "&e  Rango Ocultado: " + (rankInfo.isHideRank() ? "&aSi" : "&cNo"));
        }
        if(t != null){
            RangoInfo rankInfo = t.getRankInfo();
            int nameLenght = t.getNombre().length() + 4;
            Util.sendMessage(sender, "&c&m================&r&cRango ("+t.getNombre()+")&c&m================");
            Util.sendMessage(sender, "&e  Nombre: &a" + StringUtils.capitalize(rankInfo.getRango().name().toLowerCase()));
            String p = "&e  Prejifo: &r";
            if(rankInfo.getRango() == lc.core.entidades.Rango.DEFAULT) p += "&7"+ t.getNombre();
            else p += rankInfo.getRango().getAsPrefix();
            Util.sendMessage(sender, p);
            Util.sendMessage(sender, "&e  NameColor: &r" + rankInfo.getNameColor()+ t.getNombre());
            String dur = rankInfo.getDuration() == 0 ? "&cPermanente" : "&a"+rankInfo.getDuration();
            Util.sendMessage(sender, "&e  Duración: "+dur);
            StringBuilder lineFinal = new StringBuilder("&c&m=====================================");
            for(int i = 0 ; i < nameLenght ; i++) lineFinal.append("=");
            Util.sendMessage(sender, lineFinal.toString());
            Util.sendMessage(sender, "&e  Puntos: &a" + rankInfo.getPuntos());
            Util.sendMessage(sender, "&e  Rango Ocultado: " + (rankInfo.isHideRank() ? "&aSi" : "&cNo"));
        }
    }
}
