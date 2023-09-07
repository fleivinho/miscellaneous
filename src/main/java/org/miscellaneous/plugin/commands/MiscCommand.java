package org.miscellaneous.plugin.commands;

import org.miscellaneous.frameworks.command.BukkitCommand;
import org.miscellaneous.frameworks.command.BukkitSender;

public class MiscCommand extends BukkitCommand {

    public MiscCommand() {
        super("misc");
        setAliases("miscellaneous");
    }

    @Override
    public boolean onExecute(BukkitSender commandSender, String label, String[] args) {
        commandSender.sendMessage("§aEste servidor é aprimorado com §fMiscellaneousAPI§a!");
        commandSender.sendMessage("§aNosso projeto: §fhttps://github.com/fleivinho/miscellaneous");
        return true;
    }
}
