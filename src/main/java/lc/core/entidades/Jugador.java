package lc.core.entidades;

import lc.core.entidades.database.RankInfoQuery;
import lc.core.entidades.minijuegos.CHGInfo;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Jugador {
    private static final Map<String, Jugador> jugadores = new HashMap<>();

    private final String nombre;
    private UUID uuid;
    private Player bukkitInstance;
    private int coins;
    private RangoInfo rankInfo;
    private CHGInfo chgInfo;

    public static Jugador getJugador(String nombre){
        if(jugadores.containsKey(nombre)) return jugadores.get(nombre);
        Jugador j = new Jugador(nombre);
        jugadores.put(nombre, j);
        return j;
    }

    public void setChgInfo(CHGInfo chgInfo) {
        this.chgInfo = chgInfo;
    }

    public static Jugador getJugador(Player player){
        return getJugador(player.getName());
    }

    public CHGInfo getChgInfo() {
        return chgInfo;
    }

    private Jugador(String nombre){
        this.nombre = nombre;
    }

    private Jugador(Player bukkitPlayer){
        setBukkitInstance(bukkitPlayer);
        this.nombre = bukkitPlayer.getName();
        setUuid(bukkitPlayer.getUniqueId());
    }

    public String getNombre() {
        return nombre;
    }

    public Player getBukkitInstance() {
        return bukkitInstance;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setBukkitInstance(Player bukkitInstance) {
        this.bukkitInstance = bukkitInstance;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public RangoInfo getRankInfo() {
        return rankInfo;
    }

    public void setRankInfo(RangoInfo rankInfo) {
        this.rankInfo = rankInfo;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean noTieneRango(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.DEFAULT);
    }

    public boolean isVIP(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.VIP) || isSVIP();
    }
    public boolean isSVIP(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.SVIP) || isELITE();
    }

    public boolean isELITE(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.ELITE) || isRUBY();
    }
    public boolean isRUBY(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.RUBY) || isHelper();
    }

    public boolean isHelper(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.HELPER) || isModerador();
    }

    public boolean isModerador(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.MOD) || isAdmin();
    }

    public boolean isMiniYT(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.MINIYT);
    }

    public boolean isYoutuber(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.YOUTUBER);
    }

    public boolean isCreadorDeContenido(){
        RankInfoQuery.load_PlayerRankInfo(this);
        Rango r = getRankInfo().getRango();
        return r == Rango.YOUTUBER || r == Rango.STREAMER || r == Rango.MINIYT;
    }

    public boolean isStreamer(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.STREAMER);
    }

    public boolean isAdmin(){
        RankInfoQuery.load_PlayerRankInfo(this);
        return getRankInfo().getRango().equals(Rango.ADMIN) ||
                getRankInfo().getRango().equals(Rango.OWNER);
    }
}
