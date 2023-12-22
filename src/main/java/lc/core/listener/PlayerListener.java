package lc.core.listener;

import lc.core.entidades.Database;
import lc.core.entidades.Jugador;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event){
        String name = event.getName();
        Jugador jugador = Jugador.getJugador(name);
        Database.load_PlayerRankInfo_ASYNC(jugador);
        Database.load_PlayerCoins_ASYNC(jugador);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Jugador.getJugador(event.getPlayer().getName()).setBukkitInstance(event.getPlayer());
    }

}
