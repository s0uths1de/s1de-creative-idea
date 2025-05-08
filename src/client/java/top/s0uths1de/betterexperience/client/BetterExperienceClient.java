package top.s0uths1de.betterexperience.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.s0uths1de.betterexperience.client.key.GKeyHandle;


public class BetterExperienceClient implements ClientModInitializer {

    private static KeyBinding markKey;

    public static boolean isInspecting = false;
    public static int inspectionTick = 0;

    public static final String MODID = "better_experience";

    private static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {

        ModConfig.load();

        ModRegister.registerKeyBindings();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            GKeyHandle.handleSlotSwitch(client);
            GKeyHandle.handleHotkeyPress(client);
        });
    }

//    private static final int BEACON_DISAPPEAR_TIME = 10000; // 信标消失时间，单位：毫秒
//
//    // 当用户点击中键，在玩家当前指针指向最远处，到达方块生成一个没有底座的信标，并在一定时间后消失。
//    private void handleMark(MinecraftClient client) {
//        if (markKey != null && markKey.wasPressed()) {
//            ClientPlayerEntity player = client.player;
//            if (player != null) {
//                LOGGER.info("Marking position");
//                HitResult hitResult = client.crosshairTarget;
//                if (hitResult.getType() == HitResult.Type.BLOCK) {
//                    LOGGER.info("Hit block at {}", hitResult.getPos());
//                    BlockHitResult blockHitResult = (BlockHitResult) hitResult;
//                    BlockPos pos = blockHitResult.getBlockPos();
//                    World world = player.getWorld();
//                    System.out.println(world.isClient);
//                    if (world.isClient) {
//                        ServerWorld serverWorld = (ServerWorld) world;
//                        // 生成信标
//                        ArmorStandEntity beaconMarker = new ArmorStandEntity(EntityType.ARMOR_STAND, world);
//                        beaconMarker.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
//                        beaconMarker.setInvisible(true);
//                        beaconMarker.setInvulnerable(true);
//                        beaconMarker.setSilent(true);
//                        beaconMarker.setShowArms(false);
//                        beaconMarker.setCustomNameVisible(false);
//                        beaconMarker.equipStack(net.minecraft.entity.EquipmentSlot.HEAD, new ItemStack(Items.BEACON));
//                        serverWorld.spawnEntity(beaconMarker);
//                        serverWorld.playSound(null, pos, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
//
//                        // 定时移除信标
//                        new Timer().schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                if (!beaconMarker.isRemoved()) {
//                                    beaconMarker.remove(net.minecraft.entity.Entity.RemovalReason.DISCARDED);
//                                    serverWorld.playSound(null, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                                }
//                            }
//                        }, BEACON_DISAPPEAR_TIME);
//                    }
//                }
//            }
//        }
//    }
}
