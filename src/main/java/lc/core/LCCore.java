package lc.core;

import lc.core.configuration.LCConfig;
import lc.core.entidades.Database;
import lc.core.listener.PlayerListener;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class LCCore extends JavaPlugin {

    private static LCCore instance;
    public static LCConfig databaseConfig;
    public static LCConfig minelcConfig;
    public static boolean selfCrashing = false;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        databaseConfig = new LCConfig("database", this);
        minelcConfig = new LCConfig("minelc", this);
        Database.loadDatabaseConfig(databaseConfig.getConfig());

        try {
            Database.conectar();
        } catch (SQLException e) {
            Util.console("&c[Core] No se ha podido conectar con la base de datos");
            Util.console("&c[Core] &4Adios!");
            selfCrashing = true;
            Bukkit.shutdown();

        }
        checkearConexion();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        if(!selfCrashing){
            Util.console("&c[Core] &4Adios!");
        }
    }
    private void checkearConexion() {
        Bukkit.getScheduler().runTaskTimer(this, Database::checkearConexion,  36000L, 36000L);
    }

    public static LCCore get() {
        return instance;
    }
}
