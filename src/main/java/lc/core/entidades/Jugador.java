package lc.core.entidades;

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
    private int vippoints;
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

    public int getVippoints() {
        return vippoints;
    }

    public void setVippoints(int vippoints) {
        this.vippoints = vippoints;
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
    
    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean noTieneRango(){
        return bukkitInstance.hasPermission("DEFAULT");
    }

    public boolean isVIP(){
        return bukkitInstance.hasPermission("VIP") || isSVIP();
    }
    public boolean isSVIP(){

        return bukkitInstance.hasPermission("SVIP") || isELITE();
    }

    public boolean isELITE(){

        return bukkitInstance.hasPermission("ELITE") || isRUBY();
    }
    public boolean isRUBY(){

        return bukkitInstance.hasPermission("RUBY") || isHelper();
    }

    public boolean isHelper(){

        return bukkitInstance.hasPermission("HELPER") || isModerador();
    }

    public boolean isModerador(){

        return bukkitInstance.hasPermission("MOD") || isAdmin();
    }

    public boolean isMiniYT(){

        return bukkitInstance.hasPermission("MINIYT");
    }

    public boolean isYoutuber(){

        return bukkitInstance.hasPermission("YOUTUBER");
    }

    public boolean isCreadorDeContenido(){

        return bukkitInstance.hasPermission("YOUTUBER") || bukkitInstance.hasPermission("STREAMER") || bukkitInstance.hasPermission("MINIYT");
    }

    public boolean isStreamer(){

        return bukkitInstance.hasPermission("STREAMER");
    }

    public boolean isAdmin(){

        return bukkitInstance.hasPermission("ADMIN") ||
                bukkitInstance.hasPermission("OWNER");
    }
}
