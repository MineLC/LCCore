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
        if(e.getMessage().toLowerCase().startsWith("/hub") || e.getMessage().toLowerCase().startsWith("/lobby")){
            e.setCancelled(true);
            boolean isop = p.isOp();
            if(!isop) p.setOp(true);
            Bukkit.dispatchCommand(p, "server Lobby");
            if(!isop) p.setOp(false);
        }
    }
}
