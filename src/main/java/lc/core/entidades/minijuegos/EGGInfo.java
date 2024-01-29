package lc.core.entidades.minijuegos;

import java.util.ArrayList;
import java.util.List;

public class EGGInfo {

    private int level = 0;
    private int kills = 0;
    private List<String> kitsPropios = new ArrayList<>();
    private int muertes = 0;
    private int wins = 0;
    private int playeds = 0;
    private String kit;
    private int racha;

    public EGGInfo(int level, int kills, int muertes, int wins, int playeds, String kit, int racha) {
        this.level = level;
        this.muertes = muertes;
        this.kills = kills;
        this.wins = wins;
        this.playeds = playeds;
        this.kit = kit;
        this.racha = racha;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getKills() {
        return kills;
    }

    public int getMuertes() {
        return muertes;
    }

    public void setMuertes(int muertes) {
        this.muertes = muertes;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getPlayeds() {
        return playeds;
    }

    public void setPlayeds(int playeds) {
        this.playeds = playeds;
    }

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public int getRacha() {
        return racha;
    }

    public void setRacha(int racha) {
        this.racha = racha;
    }

    public List<String> getKitsPropios() {
        return kitsPropios;
    }

    public void setKitsPropios(List<String> kitsPropio) {
        this.kitsPropios = kitsPropio;
    }
}