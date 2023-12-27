package lc.core.listener;

import lc.core.entidades.Jugador;
import lc.core.entidades.database.RankInfoQuery;
import lc.core.utilidades.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent e){
        if(e.isCancelled()) return;
        Jugador jugador = Jugador.getJugador(e.getPlayer());

        if((jugador.isVIP() || jugador.isCreadorDeContenido()) && !jugador.getRankInfo().isHideRank())
            e.setMessage(Util.color(e.getMessage()));
        if(!jugador.getRankInfo().isHideRank())
            e.setFormat(Util.color(jugador.getRankInfo().getRango().getAsPrefix()+jugador.getRankInfo().getNameColor()+e.getPlayer().getName()+"&8 » &7")+e.getMessage());
        else
            e.setFormat(Util.color("&7"+e.getPlayer().getName()+"&8 » &7")+e.getMessage());

    }
}
