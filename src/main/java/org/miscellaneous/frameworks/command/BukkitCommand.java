package org.miscellaneous.frameworks.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class BukkitCommand extends Command {

    public BukkitCommand(String name) {
        super(name);
    }

    public BukkitCommand(String name, String description, String[] aliases) {
        super(name, description, "", Arrays.asList(aliases));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return false;
    }

}
