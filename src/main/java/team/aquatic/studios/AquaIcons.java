package team.aquatic.studios;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import team.aquatic.studios.bstats.Metrics;

import team.aquatic.studios.commands.Executor;
import team.aquatic.studios.folder.Files;
import team.aquatic.studios.listener.IconsListener;

public final class AquaIcons extends JavaPlugin {

    public static AquaIcons instance;
    public static AquaIcons getInstance() {
        return instance;
    }

    private static Files icons;
    public static Files GetIcons() {
        return icons;
    }

    private static Files config;
    public static Files SetConfig() {
        return config;
    }

    public boolean status = true;

    @Override
    public void onEnable() {
        instance = this;
        int pluginId = 22572;
        Metrics metrics = new Metrics(this, pluginId);

        icons = new Files("icons");
        config = new Files("config");
        RegisterCommands();
        RegisterListeners();

    }

    @Override
    public void onDisable() {
    }

    private void RegisterCommands() {
        getCommand("aquaicons").setExecutor(new Executor(this));
    }

    private void RegisterListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new IconsListener(), this);
    }
}
