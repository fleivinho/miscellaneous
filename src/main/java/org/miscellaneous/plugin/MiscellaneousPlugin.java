package org.miscellaneous.plugin;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.miscellaneous.MiscellaneousAPI;
import org.miscellaneous.apis.classloader.ClassLoader;

public class MiscellaneousPlugin extends JavaPlugin {

    @Getter
    private static MiscellaneousPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        MiscellaneousAPI miscellaneousAPI = MiscellaneousAPI.getAPI();
        //                                = new MiscellaneousAPI();

        ClassLoader classLoader = miscellaneousAPI.getClassLoader(this);
        classLoader.init("org.miscellaneous.plugin.commands").loadCommands();
        classLoader.init("org.miscellaneous.plugin.events").loadEvents();
        //                                    ("org.miscellaneous.plugin").loadAll();


    }

    @Override
    public void onDisable() {

    }

}
