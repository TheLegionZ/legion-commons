package br.com.thelegion.legioncommons.nameplate;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleNamePlate implements NamePlate {

    private final Reference<LivingEntity> entityReference;

    private List<EntityArmorStand> stands = new CopyOnWriteArrayList<>();

    public SimpleNamePlate(LivingEntity owner) {
        this.entityReference = new WeakReference<>(owner);
    }

    @Override
    public String getLine(int lineIndex) {
        try {
            return stands.get(lineIndex).getCustomName();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addLine(String line) {
        EntityArmorStand stand = createStand(line);
        stands.add(stand);
        broadcastPacket(new PacketPlayOutSpawnEntityLiving(stand), stand);
    }

    @Override
    public void updateLine(int lineIndex, String newLine) {
        try {
            EntityArmorStand stand = stands.get(lineIndex);
            stand.setCustomName(newLine);
            broadcastPacket(new PacketPlayOutEntityMetadata(1, stand.getDataWatcher(), false), stand);
        } catch (Exception e) {

        }
    }

    @Override
    public LivingEntity getOwner() {
        return entityReference.get();
    }

    @Override
    public void update() {

    }

    public boolean isValid() {
        return getOwner() != null;
    }

    private void broadcastPacket(Packet<?> packet, EntityArmorStand stand) {
        if (!isValid()) {
            return;
        }

        WorldServer handle = ((CraftWorld) getOwner().getWorld()).getHandle();

        for (Object obj : handle.players) {
            if (obj instanceof EntityPlayer) {
                EntityPlayer nmsPlayer = (EntityPlayer) obj;

                double distanceSquared = square(nmsPlayer.locX - stand.locX) + square(nmsPlayer.locZ - stand.locZ);
                if (distanceSquared < 512 && nmsPlayer.playerConnection != null) {
                    nmsPlayer.playerConnection.sendPacket(packet);
                }
            }
        }
    }

    private double square(double num) {
        return num * num;
    }

    private EntityArmorStand createStand(String line) {
        WorldServer handle = ((CraftWorld) getOwner().getWorld()).getHandle();

        EntityArmorStand stand = new EntityArmorStand(handle);
        stand.setInvisible(true);
        stand.setSmall(true);
        stand.setGravity(false);
        stand.n(true);
        stand.setCustomName(line);
        stand.setCustomNameVisible(true);

        return stand;
    }
}
