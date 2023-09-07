package org.miscellaneous.frameworks.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.reflect.Field;

public class CommandRegistry {

    private final CommandMap commandMap;

    public CommandRegistry() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public void registerCommand(JavaPlugin plugin, Command command) {
        commandMap.register(plugin.getName(), command);
    }
}
