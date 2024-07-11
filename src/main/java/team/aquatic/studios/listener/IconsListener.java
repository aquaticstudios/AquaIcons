package team.aquatic.studios.listener;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import team.aquatic.studios.AquaIcons;
import team.aquatic.studios.manager.IconsManager;
import team.aquatic.studios.tools.Utils;

/**
 * Code update
 * Created by Sxntido
 * Old-project: Emotes
 * https://github.com/Sxntido/Emotes
 */

public class IconsListener implements Listener {
    private AquaIcons plugin = AquaIcons.getPlugin(AquaIcons.class);

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (this.plugin.status) {
            ConfigurationSection iconsConfig = AquaIcons.GetIcons().getConfigurationSection("icons");
            for (String iconName : iconsConfig.getKeys(false)) {
                IconsManager icon = new IconsManager(iconName);
                if (message.contains(icon.getTrigger())) {
                    if (player.hasPermission(icon.getPermission())) {
                        message = message.replace(icon.getTrigger(), icon.getIcon());
                        event.setMessage(Utils.TranslateHexColor(Utils.TranslateHexColor(message)));
                    } else {
                        if (message.contains(icon.getTrigger())) {
                            event.setMessage(icon.getTrigger());
                        }
                    }
                }
            }
        }
    }
}
