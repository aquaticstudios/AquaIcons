package team.aquatic.studios.commands.tab;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;

public class ExcTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("aquaicons.list")) {
                completions.add("list");
            }

            if (sender.hasPermission("aquaicons.admin")) {
                completions.add("help");
                completions.add("create");
                completions.add("reload");
            }
        }

        return completions;
    }
}
