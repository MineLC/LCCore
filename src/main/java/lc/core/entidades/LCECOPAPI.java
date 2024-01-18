package lc.core.entidades;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LCECOPAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "lceco";
    }

    @Override
    public @NotNull String getAuthor() {
        return "OctavioFarias";
    }

    @Override
    public @NotNull String getVersion() {
        return "2.0";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if(params.equalsIgnoreCase("vippoints")) {
            return !player.isOnline() ? "0" : String.valueOf(Jugador.getJugador(player.getName()).getVippoints()); // "name" requires the player to be valid
        }

        if(params.equalsIgnoreCase("lcoins")) {
            return !player.isOnline() ? "0" : String.valueOf(Jugador.getJugador(player.getName()).getCoins()); // "name" requires the player to be valid
        }
        return null;
    }
}
