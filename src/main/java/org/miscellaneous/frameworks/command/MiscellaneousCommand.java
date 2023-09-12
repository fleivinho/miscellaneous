package org.miscellaneous.frameworks.command;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public abstract class MiscellaneousCommand extends Command {

    @Getter
    private MiscellaneousSender commandSender;

    public MiscellaneousCommand(String name) {
        super(name);
    }

    public MiscellaneousCommand(String name, String description, String[] aliases) {
        super(name, description, "", Arrays.asList(aliases));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        this.commandSender = new MiscellaneousSender(sender);

        if (getPermission() != null && !getPermission().isEmpty() && !commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage("§cÉ necessário a permissão §f" + getPermission() + "§c!");
            return false;
        }

        return onExecute(commandSender, label, args);
    }

    public abstract boolean onExecute(MiscellaneousSender commandSender, String label, String[] args);

    public void setAliases(String... aliases) {
        this.setAliases(Arrays.asList(aliases));
    }

}
