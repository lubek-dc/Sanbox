package me.lubek.sandbox.libs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class file {
    public static FileConfiguration getConfiguration(String Path) {
        File file2 = new File("plugins/"+Path);
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file2);
        return configuration;
    }
    public static boolean checkConfiguration(String Path) {
        File file2 = new File("plugins/"+Path);
        if(file2.exists()) {
            return true;
        }
        if(!file2.exists()) {
            return false;
        }
        return false;
    }
}
