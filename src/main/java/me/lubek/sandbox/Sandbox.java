package me.lubek.sandbox;

import org.bukkit.plugin.java.JavaPlugin;

public final class Sandbox extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("Plugin started");
        getServer().getPluginManager().registerEvents(new events(), this);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("Plugin disabled");
    }
}
