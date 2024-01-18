package lc.core.entidades;

import lc.core.LCCore;
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

    public static void removeJugador(Jugador j){
        jugadores.remove(j.getNombre());
    }

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
        return LCCore.permission.has(bukkitInstance, "minelc.default");
    }

    public boolean isVIP(){
        return LCCore.permission.has(bukkitInstance,"minelc.vip") || isSVIP();
    }
    public boolean isSVIP(){

        return LCCore.permission.has(bukkitInstance, "minelc.svip") || isELITE();
    }

    public boolean isELITE(){

        return LCCore.permission.has(bukkitInstance, "minelc.elite")  || isRUBY();
    }
    public boolean isRUBY(){

        return LCCore.permission.has(bukkitInstance, "minelc.ruby");
    }

    public boolean isHelper(){
        return LCCore.permission.has(bukkitInstance, "minelc.helper") || isModerador();
    }

    public boolean isModerador(){

        return LCCore.permission.has(bukkitInstance, "minelc.mod") || isAdmin();
    }

    public boolean isMiniYT(){

        return LCCore.permission.has(bukkitInstance, "minelc.miniyt");
    }

    public boolean isYoutuber(){

        return LCCore.permission.has(bukkitInstance, "minelc.yt");
    }

    public boolean isCreadorDeContenido(){

        return isYoutuber() || isStreamer() || isMiniYT();
    }

    public boolean isStreamer() {

        return LCCore.permission.has(bukkitInstance, "minelc.streamer");
    }

    public boolean isAdmin(){

        return LCCore.permission.has(bukkitInstance, "minelc.admin") ||
                LCCore.permission.has(bukkitInstance, "minelc.owner");
    }

}
