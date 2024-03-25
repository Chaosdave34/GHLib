package net.gamershub.ghlib;

import org.bukkit.plugin.java.JavaPlugin;

public class GHLib extends JavaPlugin {
    public static JavaPlugin PLUGIN;


    public static void setPlugin(final JavaPlugin plugin) {
        GHLib.PLUGIN = plugin;
    }
}
