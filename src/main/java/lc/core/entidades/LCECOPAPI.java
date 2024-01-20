package lc.core.entidades;

import lc.core.commands.LCoins;
import lc.core.entidades.database.LCoinsQuery;
import lc.core.entidades.database.VipPointsQuery;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
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
            VipPointsQuery.load_PlayerVipPoints(Jugador.getJugador(player.getName()));
            return !player.isOnline() ? "0" : String.valueOf(Jugador.getJugador(player.getName()).getVippoints()); // "name" requires the player to be valid
        }

        if(params.equalsIgnoreCase("lcoins")) {
            LCoinsQuery.load_PlayerCoins(Jugador.getJugador(player.getName()));
            return !player.isOnline() ? "0" : String.valueOf(Jugador.getJugador(player.getName()).getCoins()); // "name" requires the player to be valid
        }
        return null;
    }
}
