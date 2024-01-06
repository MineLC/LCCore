package lc.core.listener;

import lc.core.entidades.Jugador;
import lc.core.entidades.database.VipPointsQuery;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandsReplacer implements Listener {

    @EventHandler
    public void onOpCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        Jugador j = Jugador.getJugador(p);
        if (e.getMessage().toLowerCase().startsWith("/op")) {
            e.setCancelled(true);
            if (j.isAdmin()) {
                String[] args = e.getMessage().split(" ");
                if (args.length == 1) {
                    Util.sendMessage(p, "&e&lMINE&b&lLC &cUsa /op <jugador>");
                    return;
                }
                Player player = Bukkit.getPlayer(args[1]);
                if (player == null || !player.isOnline()) {
                    Util.sendMessage(p, "&e&lMINE&b&lLC &cEse jugador no esta conectado.");
                    return;
                }
                if (player.isOp()) {
                    player.setOp(false);
                    Util.sendMessage(player, "&e&lMINE&b&lLC &fUn Administrador &cte quito &fel Operador del Servidor.");
                    Util.sendMessage(p, "&e&lMINE&b&lLC &fAhora &a" + player.getName() + "&c ya no es &fOperador del Servidor.");
                    return;
                }
                player.setOp(true);
                Util.sendMessage(player, "&e&lMINE&b&lLC &fUn Administrador &ate hizo &fOperador del Servidor.");
                Util.sendMessage(p, "&e&lMINE&b&lLC &fAhora &a" + player.getName() + "&a es &fOperador del Servidor.");

            } else {
                Util.sendMessage(p, "&e&lMINE&b&lLC &cDebes ser &4&lADMIN &cpara ejecutar este comando.");
            }
        }
    }
}
