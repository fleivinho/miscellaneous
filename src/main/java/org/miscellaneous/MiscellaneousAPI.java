package org.miscellaneous;

import org.bukkit.plugin.java.JavaPlugin;
import org.miscellaneous.apis.classloader.ClassLoader;
import org.miscellaneous.plugin.MiscellaneousPlugin;

public class MiscellaneousAPI {

    public ClassLoader getClassLoader(JavaPlugin instance) {
        return new ClassLoader(instance);
    }

    public static MiscellaneousAPI getAPI() {
        return new MiscellaneousAPI();
    }

    public static MiscellaneousPlugin getPlugin() {
        return MiscellaneousPlugin.getInstance();
    }
}
