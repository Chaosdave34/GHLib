package io.github.chaosdave34.ghlib.gui;

import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class GuiHandler implements Listener {
    private final Map<UUID, Gui> openGuis = new HashMap<>();

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (openGuis.containsKey(e.getPlayer().getUniqueId())) {
            openGuis.get(e.getPlayer().getUniqueId()).onInventoryClose(e);
        }
        openGuis.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (openGuis.containsKey(e.getWhoClicked().getUniqueId())) {
            openGuis.get(e.getWhoClicked().getUniqueId()).onInventoryClick(e);
        }
    }
}
