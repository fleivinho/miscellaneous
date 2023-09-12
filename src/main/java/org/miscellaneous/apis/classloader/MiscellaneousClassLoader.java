package org.miscellaneous.apis.classloader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.miscellaneous.apis.classloader.getter.ClassGetter;
import org.miscellaneous.frameworks.command.register.CommandRegistry;
import org.miscellaneous.plugin.MiscellaneousPlugin;

@RequiredArgsConstructor
public final class MiscellaneousClassLoader {

    @Getter
    private final JavaPlugin plugin;

    private final CommandRegistry commandRegistry = new CommandRegistry();

    public void loadCommands(String packageName) {
        prepareLoad(packageName, LoaderType.COMMAND);
    }

    public void loadEvents(String packageName) {
        prepareLoad(packageName, LoaderType.LISTENER);
    }

    public void loadAll(String packageName) {
        prepareLoad(packageName, LoaderType.ALL);
    }

    public void load(String packageName, LoaderType loaderType) {
        prepareLoad(packageName, loaderType);
    }

    private void prepareLoad(String packageName, LoaderType loaderType) {
        if (plugin == null) throw new IllegalArgumentException("Plugin is not set!");
        if (packageName == null) throw new IllegalArgumentException("Package name is not set!");
        for (Class<?> classes : ClassGetter.getClassesForPackage(plugin, packageName)) {
            try {
                loadClass(classes, loaderType);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void loadClass(Class<?> classes, LoaderType preferences) throws InstantiationException, IllegalAccessException {
        Class<?> type = preferences.getClassType();

        if (!classes.getName().contains("$") && (type == null || type.isAssignableFrom(classes))) {
            Object instance = (Object) classes.newInstance();

            if (instance instanceof Listener) {
                MiscellaneousPlugin.miscellaneous().getLogger().info("Listener " + classes.getName() + " carregada!");
                Bukkit.getPluginManager().registerEvents((Listener) classes.newInstance(), plugin);
            } else if (instance instanceof Command) {
                Command command = (Command) classes.newInstance();
                MiscellaneousPlugin.miscellaneous().getLogger().info("Comando " + command.getName() + " carregado!");
                commandRegistry.registerCommand(plugin, command);
            }
        }
    }

    @RequiredArgsConstructor
    @Getter
    public enum LoaderType {
        COMMAND(Command.class), LISTENER(Listener.class), ALL(null);

        private final Class<?> classType;
    }
}
