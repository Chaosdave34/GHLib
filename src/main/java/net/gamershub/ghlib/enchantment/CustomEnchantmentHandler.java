package net.gamershub.ghlib.enchantment;

import lombok.Getter;
import net.gamershub.ghlib.ReflectionUtils;
import net.gamershub.ghlib.Utils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

@Getter
public class CustomEnchantmentHandler {

    public CustomEnchantmentHandler() {
        ReflectionUtils.unfreezeRegistry(BuiltInRegistries.ENCHANTMENT);
    }

    public void registerEnchantment(CustomEnchantment customEnchantment) {
        Registry.register(BuiltInRegistries.ENCHANTMENT, customEnchantment.id, customEnchantment);
        Utils.registerEvents(customEnchantment);
    }
}
