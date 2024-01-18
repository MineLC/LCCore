package lc.core;

import lc.core.commands.*;
import lc.core.configuration.LCConfig;
import lc.core.entidades.LCECOPAPI;
import lc.core.entidades.database.Database;
import lc.core.listener.CommandsReplacer;
import lc.core.listener.PlayerListener;
import lc.core.utilidades.Util;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class LCCore extends JavaPlugin {

    private static LCCore instance;

    public static Permission permission = null;
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
        if (!setupPermissions()){
            Bukkit.shutdown();
            return;
        }
        Util.console("&a[Core] ¡Conexión realizada con la base de datos!");
        checkearConexion();
        getCommand("lcoins").setExecutor(new LCoins());
        getCommand("vippoints").setExecutor(new VipPoints());
        getCommand("eco").setExecutor(new Economy());
        getCommand("minelc").setExecutor(new LCReload());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new CommandsReplacer(), this);
        new LCECOPAPI().register();
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

    private boolean setupPermissions() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        permission = rsp.getProvider();
        return permission != null;
    }

    public static LCCore get() {
        return instance;
    }
}
