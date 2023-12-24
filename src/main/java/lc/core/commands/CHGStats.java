package lc.core.commands;

import lc.core.LCCore;
import lc.core.entidades.Database;
import lc.core.entidades.Jugador;
import lc.core.entidades.minijuegos.CHGInfo;
import lc.core.utilidades.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CHGStats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Jugador jugador = Jugador.getJugador((Player) sender);
                Database.load_PlayerCHGInfo(jugador);

                Bukkit.getScheduler().runTaskLater(LCCore.get(), () -> {
                    CHGInfo chgInfo = jugador.getChgInfo();
                    Util.sendMessage(sender, "&e&m================&r&eCHG&e&m================");
                    Util.sendMessage(sender, "&e  Part. Jugadas: &a" + chgInfo.getPlayeds());
                    Util.sendMessage(sender, "&e  Victorias: &a" + chgInfo.getWins());
                    Util.sendMessage(sender, "&e  Asesinatos: &a" + chgInfo.getKit());
                    Util.sendMessage(sender, "&e  Racha de victorias: &a" + chgInfo.getRacha());
                    Util.sendMessage(sender, "&e&m===================================");
                    Util.sendMessage(sender, "&e  Kit: &a" + StringUtils.capitalize(chgInfo.getKit().toLowerCase()));
                    Util.sendMessage(sender, "&e  Rango: &a" + StringUtils.capitalize(chgInfo.getRank().name().toLowerCase()));
                    Util.sendMessage(sender, "&e  Fama: &a" + chgInfo.getFama());
                }, 10L);
            } else {
                Util.sendMessage(sender, "&c/chg [jugador]");
            }
            return true;

        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null || !player.isOnline()) {
            Util.sendMessage(sender, "&cJugador no encontrado");
            return true;
        }
        Jugador jugador = Jugador.getJugador(player);
        Database.load_PlayerCHGInfo(jugador);

        Bukkit.getScheduler().runTaskLater(LCCore.get(), () -> {
            CHGInfo chgInfo = jugador.getChgInfo();
            Util.sendMessage(sender, "&e&m=======&r&eCHG (" + player.getName() + ")&e&m=======");
            Util.sendMessage(sender, "&ePart. Jugadas: &a" + chgInfo.getPlayeds());
            Util.sendMessage(sender, "&eVictorias: &a" + chgInfo.getWins());
            Util.sendMessage(sender, "&eAsesinatos: &a" + chgInfo.getKit());
            Util.sendMessage(sender, "&eRacha de victorias: &a" + chgInfo.getRacha());
            Util.sendMessage(sender, "&e&m========================================");
            Util.sendMessage(sender, "&eRango: &a" + StringUtils.capitalize(chgInfo.getRank().name()));
            Util.sendMessage(sender, "&eFama: &a" + chgInfo.getFama());

        }, 10L);
        return true;
    }
}
