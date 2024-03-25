package io.github.chaosdave34.ghlib.enchantment;

import lombok.Getter;
import io.github.chaosdave34.ghlib.ReflectionUtils;
import io.github.chaosdave34.ghlib.Utils;
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
