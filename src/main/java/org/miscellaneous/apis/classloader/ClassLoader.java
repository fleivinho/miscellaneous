package org.miscellaneous.apis.classloader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.miscellaneous.frameworks.command.CommandRegistry;
import org.miscellaneous.plugin.MiscellaneousPlugin;

@RequiredArgsConstructor
public class ClassLoader {

    @Getter
    private final JavaPlugin plugin;

    @Getter
    @Setter
    private String packageName;

    private final CommandRegistry commandRegistry = new CommandRegistry();

    public ClassLoader init(String packageName) {
        ClassLoader classLoader = new ClassLoader(plugin);
        classLoader.setPackageName(packageName);
        return classLoader;
    }

    public void loadCommands() {
        prepareLoad(LoaderType.COMMAND);
    }

    public void loadEvents() {
        prepareLoad(LoaderType.LISTENER);
    }

    public void loadAll() {
        prepareLoad(LoaderType.ALL);
    }

    public void load(LoaderType loaderType) {
        prepareLoad(loaderType);
    }

    private void prepareLoad(LoaderType loaderType) {
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
                MiscellaneousPlugin.getInstance().getLogger().info("Listener " + classes.getName() + " carregada!");
                Bukkit.getPluginManager().registerEvents((Listener) classes.newInstance(), plugin);
            } else if (instance instanceof Command) {
                Command command = (Command) classes.newInstance();
                MiscellaneousPlugin.getInstance().getLogger().info("Comando " + command.getName() + " carregado!");
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
