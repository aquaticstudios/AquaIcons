package team.aquatic.studios.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

import team.aquatic.studios.AquaIcons;
import team.aquatic.studios.tools.Utils;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * New code made
 * Created by Sxntido
 * Project: AquaIcons
 */

public class Executor implements CommandExecutor {
    private AquaIcons plugin;

    public Executor(AquaIcons plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("AquaIcons Commands:");
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            if (!sender.hasPermission("aquaicons.help")) {
                sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.no-permission"))));
                return true;
            }

            sender.sendMessage("AquaIcons Commands:");
            sender.sendMessage("/aquaicons create <name> <trigger> <icon> <permission> - Create a new icon");
            sender.sendMessage("/aquaicons list - List all icons");
            sender.sendMessage("/aquaicons reload - Reload the icons configuration");
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (!sender.hasPermission("aquaicons.create")) {
                sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.no-permission"))));
                return true;
            }

            if (args.length != 5) {
                sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.how-to-create"))));
                return true;
            }

            String name = args[1];
            String trigger = args[2];
            String icon = args[3];
            String permission = args[4];

            FileConfiguration config = AquaIcons.GetIcons();

            if (config.contains("icons." + name)) {
                sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.already-name"))));
                return true;
            }

            for (String key : config.getConfigurationSection("icons").getKeys(false)) {
                String existingTrigger = config.getString("icons." + key + ".trigger");
                if (existingTrigger.equalsIgnoreCase(trigger)) {
                    sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.already-trigger"))));
                    return true;
                }
            }

            String path = "icons." + name;
            config.set(path + ".trigger", trigger);
            config.set(path + ".icon", icon);
            config.set(path + ".permission", permission);

            AquaIcons.GetIcons().Save();

            sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.new-icon").replace("%aquaicons_name%", name).replace("%aquaicons_trigger%", trigger).replace("%aquaicons.icon%", icon))));
            return true;
        }

        if (args[0].equalsIgnoreCase("list")) {
            if (!sender.hasPermission("aquaicons.list")) {
                sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.list-permission"))));
                return true;
            }

            FileConfiguration config = AquaIcons.GetIcons();
            FileConfiguration messages = AquaIcons.SetConfig();

            List<String> titleChatList = messages.getStringList("list.title-chat");
            List<String> formatList = messages.getStringList("list.format-list");

            for (String titleLine : titleChatList) {
                if (!titleLine.trim().isEmpty()) {
                    if (titleLine.equals("<empty>")) {
                        sender.sendMessage(Utils.TranslateColor("&r"));
                    } else {
                        if (titleLine.startsWith("<center>")) {
                            String centeredMessage = titleLine.replace("<center>", "");
                            sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(Utils.CenterMessage(centeredMessage))));
                        } else {
                            sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(titleLine)));
                        }
                    }
                }
            }

            if (config.contains("icons")) {
                for (String key : config.getConfigurationSection("icons").getKeys(false)) {
                    String name = key;
                    String trigger = config.getString("icons." + key + ".trigger", "N/A");
                    String icon = config.getString("icons." + key + ".icon", "N/A");
                    String permission = config.getString("icons." + key + ".permission", "N/A");

                    for (String line : formatList) {
                        if (!line.trim().isEmpty()) {
                            if (line.equals("<empty>")) {
                                sender.sendMessage(Utils.TranslateColor("&r"));
                            } else {
                                if (line.startsWith("<center>")) {
                                    String centeredMessage = line.replace("<center>", "")
                                            .replace("%aquaicons_name%", name)
                                            .replace("%aquaicons_trigger%", trigger)
                                            .replace("%aquaicons_icon%", icon)
                                            .replace("%aquaicons_permission%", permission);
                                    sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(Utils.CenterMessage(centeredMessage))));
                                } else {
                                    String message = line.replace("%aquaicons_trigger%", trigger)
                                            .replace("%aquaicons_name%", name)
                                            .replace("%aquaicons_icon%", icon)
                                            .replace("%aquaicons_permission%", permission);
                                    sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(message)));
                                }
                            }
                        }
                    }
                }
            } else {
                List<String> noIconsMessages = messages.getStringList("list.no-icons");
                for (String noIconsLine : noIconsMessages) {
                    if (!noIconsLine.trim().isEmpty()) {
                        if (noIconsLine.equals("<empty>")) {
                            sender.sendMessage(Utils.TranslateColor("&r"));
                        } else {
                            if (noIconsLine.startsWith("<center>")) {
                                String centeredMessage = noIconsLine.replace("<center>", "");
                                sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(Utils.CenterMessage(centeredMessage))));
                            } else {
                                sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(noIconsLine)));
                            }
                        }
                    }
                }
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("aquaicons.reload")) {
                sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.no-permission"))));
                return true;
            }

            AquaIcons.GetIcons().Reload();
            AquaIcons.SetConfig().Reload();
            sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.reload"))));
            return true;
        }

        sender.sendMessage(Utils.TranslateHexColor(Utils.TranslateColor(AquaIcons.SetConfig().getString("messages.unknown"))));
        return true;
    }
}
