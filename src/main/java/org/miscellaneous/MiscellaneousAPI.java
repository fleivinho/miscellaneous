package org.miscellaneous;

import org.bukkit.plugin.java.JavaPlugin;
import org.miscellaneous.apis.classloader.MiscellaneousClassLoader;
import org.miscellaneous.plugin.MiscellaneousPlugin;

public class MiscellaneousAPI {

    public MiscellaneousClassLoader getClassLoader(JavaPlugin instance) {
        return new MiscellaneousClassLoader(instance);
    }

    public MiscellaneousPlugin getPlugin() {
        return MiscellaneousPlugin.miscellaneous();
    }
}
