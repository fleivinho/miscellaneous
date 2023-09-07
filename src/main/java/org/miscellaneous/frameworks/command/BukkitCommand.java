package org.miscellaneous.frameworks.command;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public abstract class BukkitCommand extends Command {

    @Getter
    private BukkitSender commandSender;

    public BukkitCommand(String name) {
        super(name);
    }

    public BukkitCommand(String name, String description, String[] aliases) {
        super(name, description, "", Arrays.asList(aliases));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        this.commandSender = new BukkitSender(sender);

        if (getPermission() != null && !getPermission().isEmpty() && !commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage("§cÉ necessário a permissão §f" + getPermission() + "§c!");
            return false;
        }

        return onExecute(commandSender, label, args);
    }

    public abstract boolean onExecute(BukkitSender commandSender, String label, String[] args);

    public void setAliases(String... aliases) {
        this.setAliases(Arrays.asList(aliases));
    }

}
