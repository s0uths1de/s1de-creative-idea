package top.s0uths1de.betterexperience.client.key;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.s0uths1de.betterexperience.client.BetterExperienceClient;

import static top.s0uths1de.betterexperience.client.ModRegister.switchKey;

public class GKeyHandle {
    public static int previousSelectedSlot = 0;
    public static int curr;
    public static int switchCount = 0;

    public static final Logger LOGGER = LoggerFactory.getLogger(BetterExperienceClient.MODID);

    public static void handleSlotSwitch(MinecraftClient client) {

        ClientPlayerEntity player = client.player;
        if (player != null) {
            int currentSelectedSlot = player.getInventory().getSelectedSlot();
            if (currentSelectedSlot != previousSelectedSlot) {
                LOGGER.info("Player switched slot from {} to {}", previousSelectedSlot, currentSelectedSlot);
                curr = previousSelectedSlot;
                previousSelectedSlot = currentSelectedSlot;
                switchCount = 0;
            }
        }
    }

    public static void handleHotkeyPress(MinecraftClient client) {
        ClientPlayerEntity player = client.player;
        if (switchKey != null && switchKey.wasPressed()) {
            if (player != null) {
                LOGGER.info("Hotkey pressed, switching back to slot {}", curr);
                player.getInventory().setSelectedSlot(curr);
            }
        }
    }
}
