package org.miscellaneous.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.miscellaneous.MiscellaneousAPI;
import org.miscellaneous.apis.classloader.MiscellaneousClassLoader;

public class MiscellaneousPlugin extends JavaPlugin {


    @Override
    public void onEnable() {
        MiscellaneousAPI miscellaneousAPI = new MiscellaneousAPI();
        MiscellaneousClassLoader classLoader = miscellaneousAPI.getClassLoader(this);
        classLoader.load("org.miscellaneous.plugin.commands",
                MiscellaneousClassLoader.LoaderType.COMMAND);
        classLoader.load("org.miscellaneous.plugin.events",
                MiscellaneousClassLoader.LoaderType.LISTENER);
    }

    public static @NotNull MiscellaneousPlugin miscellaneous() {
        return getPlugin(MiscellaneousPlugin.class);
    }

}
