package io.github.chaosdave34.ghlib;

import io.github.chaosdave34.ghlib.enchantment.CustomEnchantmentHandler;
import io.github.chaosdave34.ghlib.entity.CustomEntityHandler;
import io.github.chaosdave34.ghlib.fakeplayer.FakePlayerHandler;
import io.github.chaosdave34.ghlib.textdisplay.TextDisplayHandler;
import io.github.chaosdave34.ghlib.gui.GuiHandler;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GHLib extends JavaPlugin {
    public static JavaPlugin PLUGIN;
    @Getter
    private static final CustomEnchantmentHandler enchantmentHandler = new CustomEnchantmentHandler();
    @Getter
    private static final GuiHandler guiHandler = new GuiHandler();
    @Getter
    private static final TextDisplayHandler textDisplayHandler = new TextDisplayHandler();
    @Getter
    private static final FakePlayerHandler fakePlayerHandler = new FakePlayerHandler();
    @Getter
    private static final CustomEntityHandler entityHandler = new CustomEntityHandler();


    public static void setPlugin(final JavaPlugin plugin) {
        GHLib.PLUGIN = plugin;

        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(guiHandler, PLUGIN);
        pluginManager.registerEvents(textDisplayHandler, PLUGIN);
        pluginManager.registerEvents(fakePlayerHandler, PLUGIN);
        pluginManager.registerEvents(entityHandler, PLUGIN);
    }
}
