package top.s0uths1de.betterexperience.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModRegister {
    public static KeyBinding switchKey;
    public static KeyBinding inspectKey;
    public static KeyBinding markKey;

    public static void registerKeyBindings() {
        switchKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + BetterExperienceClient.MODID + ".g",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category." + BetterExperienceClient.MODID
        ));

        markKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + BetterExperienceClient.MODID + ".mouse_middle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
                "mark." + BetterExperienceClient.MODID
        ));
    }
}
