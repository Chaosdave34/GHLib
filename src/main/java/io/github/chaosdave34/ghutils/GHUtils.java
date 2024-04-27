package io.github.chaosdave34.ghutils;

import io.github.chaosdave34.ghutils.entity.CustomEntityHandler;
import io.github.chaosdave34.ghutils.fakeplayer.FakePlayerHandler;
import io.github.chaosdave34.ghutils.textdisplay.TextDisplayHandler;
import io.github.chaosdave34.ghutils.gui.GuiHandler;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GHUtils extends JavaPlugin {
    public static JavaPlugin PLUGIN;
    @Getter
    private static final GuiHandler guiHandler = new GuiHandler();
    @Getter
    private static final TextDisplayHandler textDisplayHandler = new TextDisplayHandler();
    @Getter
    private static final FakePlayerHandler fakePlayerHandler = new FakePlayerHandler();
    @Getter
    private static final CustomEntityHandler entityHandler = new CustomEntityHandler();

    public static void setPlugin(final JavaPlugin plugin) {
        GHUtils.PLUGIN = plugin;

        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(guiHandler, PLUGIN);
        pluginManager.registerEvents(textDisplayHandler, PLUGIN);
        pluginManager.registerEvents(fakePlayerHandler, PLUGIN);
        pluginManager.registerEvents(entityHandler, PLUGIN);
    }
}
