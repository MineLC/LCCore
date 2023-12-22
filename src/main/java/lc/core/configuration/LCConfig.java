package lc.core.configuration;

import lc.core.LCCore;
import lc.core.utilidades.Util;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LCConfig {

    private final String nombre;
    private FileConfiguration config;
    private File file;
    private final LCCore plugin;
    public LCConfig(String nombre, LCCore plugin){
        this.nombre = nombre;
        this.plugin = plugin;
        registerConfig();
    }

    public void registerConfig(){
        file = new File(plugin.getDataFolder(), nombre+".yml");

        if(!file.exists()){
            plugin.saveResource(nombre+".yml", false);

        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Util.console("&c[Core] No se ha podido cargar el archivo "+nombre+".yml");
        }
    }
    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            Util.console("&c[Core] No se ha podido guardar el archivo "+nombre+".yml");
        }
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    public void reloadConfig() {
        if (config == null)
            file = new File(plugin.getDataFolder(), nombre+".yml");

        config = YamlConfiguration.loadConfiguration(file);

        if(file != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(file);
            config.setDefaults(defConfig);
        }
    }
}
