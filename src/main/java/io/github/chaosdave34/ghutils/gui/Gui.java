package io.github.chaosdave34.ghutils.gui;

import io.github.chaosdave34.ghutils.GHUtils;
import io.github.chaosdave34.ghutils.enchantment.CustomEnchantment;
import lombok.Getter;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class Gui {
    protected final int rows;
    protected final Component title;
    protected final boolean locked;
    protected final Map<Integer, ItemStack> content = new HashMap<>();

    protected final Map<Integer, Method> inventoryClickHandlers = new HashMap<>();

    private final Enchantment emptyEnchantment = new CustomEnchantment("empty", 1, "EMPTY", EnchantmentCategory.BREAKABLE, EquipmentSlot.values()).build();

    public Gui(int rows, Component title, boolean locked) {
        this.rows = rows;
        this.title = title;
        this.locked = locked;

        Class<?> clazz = this.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(InventoryClickHandler.class)) {
                inventoryClickHandlers.put(method.getAnnotation(InventoryClickHandler.class).slot(), method);
            }
        }
    }

    protected ItemStack createItemStack(Material material, String name, boolean hideAttributes, boolean glint) {
        return createItemStack(material, Component.text(name).decoration(TextDecoration.ITALIC, false), hideAttributes, glint);
    }

    protected ItemStack createItemStack(Material material, Component name, boolean hideAttributes, boolean glint) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(name);
        if (hideAttributes) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if (glint)
            itemMeta.addEnchant(emptyEnchantment, 1, true); // Hacky way to add enchantment glint

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    protected abstract @NonNull Inventory build(Player p, Inventory inventory);

    public void show(Player p) {
        Inventory inv = Bukkit.createInventory(null, rows * 9, title);
        p.openInventory(build(p, inv));
        GHUtils.getGuiHandler().getOpenGuis().put(p.getUniqueId(), this);
    }

    public void onInventoryClose(InventoryCloseEvent e) {
    }

    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;

        if (locked) e.setCancelled(true);

        if (inventoryClickHandlers.containsKey(e.getRawSlot())) {
            try {
                inventoryClickHandlers.get(e.getRawSlot()).invoke(this, e);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                GHUtils.PLUGIN.getLogger().warning("Error while executing button handler method. " + ex.getMessage());
            }
        }
    }

    protected void fillEmpty(Inventory inventory, Material material) {
        ItemStack[] content =  inventory.getContents();
        for (int slot = 0; slot < content.length; slot++){
            if (content[slot] == null)
                content[slot] = new ItemStack(material);
        }
        inventory.setContents(content);
    }
}
