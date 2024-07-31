package team.aquatic.studios;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import team.aquatic.studios.bstats.Metrics;
import team.aquatic.studios.commands.Executor;
import team.aquatic.studios.commands.tab.ExcTab;
import team.aquatic.studios.folder.Files;
import team.aquatic.studios.listener.IconsListener;
import team.aquatic.studios.tools.Utils;

public final class AquaIcons extends JavaPlugin {

    private String serverType;
    String version = Bukkit.getVersion();
    String pluginVersion = getDescription().getVersion();
    String bukkitVersion = Bukkit.getBukkitVersion();

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

        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&3    _   ___ "));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&3   /_\\ |_ _|  &bAquaIcons &f(v" + pluginVersion + "&f) - &aEnabled"));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&3  / _ \\ | |   &fRunning on " + bukkitVersion));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&3 /_/ \\_\\___|"));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&r"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&c    _   ___ "));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&c   /_\\ |_ _|  &bAquaIcons &f(v" + pluginVersion + "&f) - &cDisabled"));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&c  / _ \\ | |   &fRunning on " + bukkitVersion));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&c /_/ \\_\\___|"));
        Bukkit.getConsoleSender().sendMessage(Utils.TranslateColor("&r"));
    }

    private void RegisterCommands() {
        getCommand("aquaicons").setTabCompleter(new ExcTab());
        getCommand("aquaicons").setExecutor(new Executor(this));
    }

    private void RegisterListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new IconsListener(), this);
    }
}
