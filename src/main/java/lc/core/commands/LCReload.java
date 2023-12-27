package lc.core.commands;

import lc.core.entidades.Jugador;
import lc.core.entidades.database.LCoinsQuery;
import lc.core.entidades.database.RankInfoQuery;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LCReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player && !Jugador.getJugador(sender.getName()).isAdmin()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &c¡No tienes acceso a ese comando!");
            return true;
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            LCoinsQuery.load_PlayerCoins(Jugador.getJugador(player));
            RankInfoQuery.load_PlayerRankInfo(Jugador.getJugador(player));
        }

        Util.sendMessage(sender, "&e&lMine&b&lLC &a¡Datos recargados éxitosamente!");
        return true;
    }
}
