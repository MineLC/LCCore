package lc.core.commands;

import lc.core.entidades.Jugador;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VipPoints implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            if(sender instanceof Player){

                Jugador j = Jugador.getJugador((Player) sender);

                Util.sendMessage(sender, "&e&lMine&b&lLC &aTienes "+j.getVippoints()+" VipPoints.");
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &aTienes 69.000.000.000 VipPoints.");
            }
            return true;
        }
        if(sender instanceof Player && !Jugador.getJugador((Player) sender).isHelper()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cNo puedes ver las VipPoints de otro usuario.");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null || !player.isOnline()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cJugando no encontrado.");
            return true;
        }
        Jugador jugador = Jugador.getJugador(player);

        Util.sendMessage(sender, "&e&lMine&b&lLC &e"+jugador.getNombre()+" &atiene "+jugador.getVippoints()+" VipPoints.");
        return true;
    }
}
