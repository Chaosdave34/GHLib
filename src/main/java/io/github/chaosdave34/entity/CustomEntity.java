package io.github.chaosdave34.entity;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import lombok.Getter;
import io.github.chaosdave34.GHLib;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

@Getter
public abstract class CustomEntity implements Listener {
    protected String id;

    public CustomEntity(String id) {
        this.id = id;
        GHLib.getEntityHandler().registerCustomEntity(this);
    }

    public abstract void spawn(Player p, Location location);

    public abstract void onAddToWorld(EntityAddToWorldEvent e);

    protected boolean checkCustomEntity(Entity entity) {
        PersistentDataContainer container = entity.getPersistentDataContainer();

        NamespacedKey customEntityKey = new NamespacedKey(GHLib.PLUGIN, "custom_entity");
        if (container.has(customEntityKey)) {
            String id = container.get(customEntityKey, PersistentDataType.STRING);
            return this.id.equals(id);
        }

        return false;
    }
}
