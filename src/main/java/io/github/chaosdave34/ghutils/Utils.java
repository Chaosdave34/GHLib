package io.github.chaosdave34.ghutils;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public class Utils {
    public static void sendPacketToOnlinePlayers(Packet<?> packet) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer cp = (CraftPlayer) player;
            ServerPlayer sp = cp.getHandle();
            ServerGamePacketListenerImpl connection = sp.connection;

            connection.send(packet);
        }
    }

    public static void registerEvents(@NotNull Listener listener) {
        GHUtils.PLUGIN.getServer().getPluginManager().registerEvents(listener, GHUtils.PLUGIN);
    }

    public static void spawnNmsEntity(@NotNull Entity entity) {
        Bukkit.getOnlinePlayers().forEach(player -> spawnNmsEntity(player, entity));
    }

    public static void spawnNmsEntity(@NotNull Player p, @NotNull Entity entity) {
        CraftPlayer cp = (CraftPlayer) p;
        ServerPlayer sp = cp.getHandle();
        ServerGamePacketListenerImpl connection = sp.connection;

        ClientboundAddEntityPacket addEntityPacket = new ClientboundAddEntityPacket(entity);
        connection.send(addEntityPacket);

        List<SynchedEntityData.DataValue<?>> nonDefaultValues = entity.getEntityData().getNonDefaultValues();
        if (nonDefaultValues != null) {
            ClientboundSetEntityDataPacket setEntityDataPacket = new ClientboundSetEntityDataPacket(entity.getId(), entity.getEntityData().getNonDefaultValues());
            connection.send(setEntityDataPacket);
        }
    }
}
