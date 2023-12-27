package lc.core;

import lc.core.commands.*;
import lc.core.configuration.LCConfig;
import lc.core.entidades.database.Database;
import lc.core.listener.ChatListener;
import lc.core.listener.PlayerListener;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class LCCore extends JavaPlugin {

    private static LCCore instance;
    public static LCConfig databaseConfig;
    public static boolean selfCrashing = false;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        databaseConfig = new LCConfig("database", this);
        Database.loadDatabaseConfig(databaseConfig.getConfig());

        try {
            Database.conectar();
        } catch (SQLException e) {
            Util.console("&c[Core] No se ha podido conectar con la base de datos");
            Util.console("&c[Core] &4Adios!");
            selfCrashing = true;
            Bukkit.shutdown();

        }
        Util.console("&a[Core] ¡Conexión realizada con la base de datos!");
        checkearConexion();
        getCommand("chgstats").setExecutor(new CHGStats());
        getCommand("lcoins").setExecutor(new LCoins());
        getCommand("rango").setExecutor(new Rango());
        getCommand("minelc").setExecutor(new LCReload());

        getCommand("setrank").setExecutor(new RankCommands());
        getCommand("setduration").setExecutor(new RankCommands());
        getCommand("setpuntos").setExecutor(new RankCommands());
        getCommand("togglehiderank").setExecutor(new RankCommands());
        getCommand("setnamecolor").setExecutor(new RankCommands());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
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
