package top.s0uths1de.betterexperience.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Timer;
import java.util.TimerTask;

public class MarkBeaconPacket {

    public static final String ID = "top.s0uths1de.betterexperience:mark_beacon";
    private final BlockPos pos;

    public MarkBeaconPacket(BlockPos pos) {
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }

    public PacketByteBuf write() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(pos);
        return buf;
    }

    public static MarkBeaconPacket read(PacketByteBuf buf) {
        return new MarkBeaconPacket(buf.readBlockPos());
    }

    private static final int BEACON_DISAPPEAR_TIME = 10000;

    public static void register(MinecraftServer server, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        MarkBeaconPacket packet = MarkBeaconPacket.read(buf);
        ServerPlayerEntity player = server.getPlayerManager().getPlayer(handler.getPlayer().getUuid());
        if (player != null) {
            World world = player.getWorld();
            if (world instanceof ServerWorld serverWorld) {
                // 生成信标
                ArmorStandEntity beaconMarker = new ArmorStandEntity(EntityType.ARMOR_STAND, serverWorld);
                beaconMarker.setPos(packet.getPos().getX() + 0.5, packet.getPos().getY() + 1, packet.getPos().getZ() + 0.5);
                beaconMarker.setInvisible(true);
                beaconMarker.setInvulnerable(true);
                beaconMarker.setSilent(true);
                beaconMarker.setShowArms(false);
                beaconMarker.setCustomNameVisible(false);
                beaconMarker.equipStack(net.minecraft.entity.EquipmentSlot.HEAD, new ItemStack(Items.BEACON));
                serverWorld.spawnEntity(beaconMarker);
                serverWorld.playSound(null, packet.getPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                BlockPos pos = packet.getPos();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (!beaconMarker.isRemoved()) {
                            beaconMarker.remove(net.minecraft.entity.Entity.RemovalReason.DISCARDED);
                            serverWorld.playSound(null, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        }
                    }
                }, BEACON_DISAPPEAR_TIME);

//                server.getWorld(player.getWorld().getRegistryKey()).ifPresent(serverWorld1 -> {
//                    server.getScheduler().schedule(() -> {
//                        if (!beaconMarker.isRemoved()) {
//                            beaconMarker.remove(net.minecraft.entity.Entity.RemovalReason.DISCARDED);
//                            serverWorld1.playSound(null, packet.getPos(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                        }
//                    }, 100, java.util.concurrent.TimeUnit.MILLISECONDS);
//                });
            }
        }
    }
}
