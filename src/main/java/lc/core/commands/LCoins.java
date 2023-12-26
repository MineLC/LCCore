package lc.core.commands;

import lc.core.entidades.Jugador;
import lc.core.entidades.database.LCoinsQuery;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LCoins implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            if(sender instanceof Player){

                Jugador j = Jugador.getJugador((Player) sender);
                LCoinsQuery.load_PlayerCoins(j);

                Util.sendMessage(sender, "&e&lMine&b&lLC &aTienes "+j.getCoins()+" LCoins.");
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &aTienes 69.000.000.000 LCoins.");
            }
            return true;
        }
        if(sender instanceof Player && !Jugador.getJugador((Player) sender).isHelper()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cNo puedes ver las LCoins de otro usuario.");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null || !player.isOnline()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cJugando no encontrado.");
            return true;
        }
        Jugador jugador = Jugador.getJugador(player);
        LCoinsQuery.load_PlayerCoins(jugador);

        Util.sendMessage(sender, "&e&lMine&b&lLC &e"+jugador.getNombre()+" &atiene "+jugador.getCoins()+" LCoins.");
        return true;
    }
}
