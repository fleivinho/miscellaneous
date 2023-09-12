package org.miscellaneous.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.miscellaneous.apis.itemcreator.MiscellaneousItemCreator;
import org.miscellaneous.frameworks.command.MiscellaneousCommand;
import org.miscellaneous.frameworks.command.MiscellaneousSender;

public class MiscCommand extends MiscellaneousCommand {

    public MiscCommand() {
        super("misc");
        setAliases("miscellaneous");
    }

    @Override
    public boolean onExecute(MiscellaneousSender commandSender, String label, String[] args) {
        commandSender.sendMessage("§aEste servidor é aprimorado com §fMiscellaneousAPI§a!");
        commandSender.sendMessage("§aNosso projeto: §fhttps://github.com/fleivinho/miscellaneous");
        return true;
    }
}
