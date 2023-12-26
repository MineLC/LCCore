package lc.core.commands;

import lc.core.LCCore;
import lc.core.entidades.database.CHGInfoQuery;
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
                CHGInfoQuery.load_PlayerCHGInfo(jugador);

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
        if(sender instanceof Player && !Jugador.getJugador((Player) sender).isHelper()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cNo puedes ver las estadisticas de otro usuario.");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null || !player.isOnline()) {
            Util.sendMessage(sender, "&cJugador no encontrado");
            return true;
        }
        Jugador jugador = Jugador.getJugador(player);
        CHGInfoQuery.load_PlayerCHGInfo(jugador);

        CHGInfo chgInfo = jugador.getChgInfo();
        int nameLenght = player.getName().length() + 4;
        Util.sendMessage(sender, "&e&m=============&r&eCHG (" + player.getName() + ")&e&m=============");
        Util.sendMessage(sender, "&e  Part. Jugadas: &a" + chgInfo.getPlayeds());
        Util.sendMessage(sender, "&e  Victorias: &a" + chgInfo.getWins());
        Util.sendMessage(sender, "&e  Asesinatos: &a" + chgInfo.getKit());
        Util.sendMessage(sender, "&e  Racha de victorias: &a" + chgInfo.getRacha());
        StringBuilder lineFinal = new StringBuilder("&e&m==========================");
        for(int i = 0 ; i < nameLenght ; i++) lineFinal.append("=");
        Util.sendMessage(sender, lineFinal.toString());
        Util.sendMessage(sender, "&e  Rango: &a" + StringUtils.capitalize(chgInfo.getRank().name()));
        Util.sendMessage(sender, "&e  Fama: &a" + chgInfo.getFama());

        return true;
    }
}
