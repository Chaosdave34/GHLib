package io.github.chaosdave34.ghutils.entity;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import io.github.chaosdave34.ghutils.GHUtils;
import io.github.chaosdave34.ghutils.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class CustomEntityHandler implements Listener {
    private final Map<String, CustomEntity> customEntities = new HashMap<>();


    public void registerCustomEntity(CustomEntity customEntity) {
        Utils.registerEvents(customEntity);
        customEntities.put(customEntity.id, customEntity);
    }

    @EventHandler
    public void onEntityAddToWorld(EntityAddToWorldEvent e) {
        Entity entity = e.getEntity();
        PersistentDataContainer container = entity.getPersistentDataContainer();

        NamespacedKey customEntityKey = new NamespacedKey(GHUtils.PLUGIN, "custom_entity");
        if (container.has(customEntityKey)) {
            String id = container.get(customEntityKey, PersistentDataType.STRING);

            if (customEntities.containsKey(id)) {
                customEntities.get(id).onAddToWorld(e);
            }

        }
    }

}
