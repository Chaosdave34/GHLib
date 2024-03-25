package io.github.chaosdave34.enchantment;

import lombok.Getter;
import io.github.chaosdave34.ReflectionUtils;
import io.github.chaosdave34.Utils;
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
