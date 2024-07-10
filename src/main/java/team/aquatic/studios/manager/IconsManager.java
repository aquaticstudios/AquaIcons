package team.aquatic.studios.manager;

import team.aquatic.studios.AquaIcons;

/**
 * Code update
 * Created by Sxntido
 * Old-project: Emotes
 * https://github.com/Sxntido/Emotes
 */

public class IconsManager {
    private String name;
    private String trigger;
    private String icon;
    private String permission;

    public IconsManager(String name) {
        this.name = name;
        this.trigger = AquaIcons.GetIcons().getString("icons." + this.name + ".trigger");
        this.icon = AquaIcons.GetIcons().getString("icons." + this.name + ".icon");
        this.permission = AquaIcons.GetIcons().getString("icons." + this.name + ".permission");
    }

    public String getName() {
        return name;
    }

    public String getTrigger() {
        return trigger;
    }

    public String getIcon() {
        return icon;
    }

    public String getPermission() {
        return permission;
    }
}
