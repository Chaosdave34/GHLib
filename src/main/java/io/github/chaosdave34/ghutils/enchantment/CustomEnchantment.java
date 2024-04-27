package io.github.chaosdave34.ghutils.enchantment;

import io.github.chaosdave34.ghutils.GHUtils;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.bukkit.craftbukkit.enchantments.CraftEnchantment;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

@Getter
public class CustomEnchantment extends Enchantment implements Listener {
    protected String id;

    public CustomEnchantment(String id, int maxLevel, String displayName, TagKey<Item> supportedItems, EquipmentSlot... equipmentSlots) {
        super(Enchantment.definition(supportedItems, 0, maxLevel, new Cost(0, 0), new Cost(0, 0), 0, equipmentSlots));
        this.id = id;
        this.descriptionId = displayName;
    }

    public org.bukkit.enchantments.Enchantment build() {
        GHUtils.getEnchantmentHandler().registerEnchantment(this);

        return CraftEnchantment.minecraftToBukkit(this);
    }

    @Override
    public @NotNull Component getFullname(int level) {
        return Component.translatable(this.getDescriptionId())
                .withStyle(ChatFormatting.BLUE)
                .append(CommonComponents.SPACE)
                .append(Component.translatable("enchantment.level." + level));
    }

}
