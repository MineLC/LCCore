package lc.core.listener;

import lc.core.entidades.Jugador;
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
        e.setFormat(Util.color(jugador.getRankInfo().getRango().getAsPrefix()+jugador.getRankInfo().getNameColor()+e.getPlayer().getName()+"&7 » &f"+e.getMessage()));
    }
}
