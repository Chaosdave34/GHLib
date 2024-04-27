package io.github.chaosdave34.ghutils.textdisplay;

import lombok.Getter;
import io.github.chaosdave34.ghutils.GHUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class TextDisplay {
    protected List<ArmorStand> armorStands = new ArrayList<>();

    protected final String worldName;
    protected final Location position;
    protected final int lineCount;

    public TextDisplay(String worldName, Location position, int lineCount) {
        this.worldName = worldName;
        this.position = position;
        this.lineCount = lineCount;

        GHUtils.getTextDisplayHandler().registerTextDisplay(this);
    }

    public abstract @NotNull List<Component> getLines(Player p);

    public void updateForAll() {
        Bukkit.getOnlinePlayers().forEach(this::update);
    }

    public void update(Player p) {
        GHUtils.getTextDisplayHandler().updateTextDisplay(p, this);
    }
}
