//package top.s0uths1de.betterexperience.client.mixin.client;
//
//import net.fabricmc.fabric.mixin.client.gametest.lifecycle.GameOptionsAccessor;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.option.GameOptions;
//import net.minecraft.client.option.KeyBinding;
//import org.lwjgl.glfw.GLFW;
//
//public class ModifyInventoryKeyToO {
//
//    public static void modifyInventoryKey() {
//        MinecraftClient client = MinecraftClient.getInstance();
//        GameOptions gameOptions = client.options;
//
//        // 创建一个新的 KeyBinding，将按键设置为 O 键
//        KeyBinding newInventoryKey = new KeyBinding("key.inventory", GLFW.GLFW_KEY_O, "key.categories.inventory");
//
//        // 使用访问器方法设置新的 inventoryKey
//        GameOptionsAccessor accessor = (GameOptionsAccessor) gameOptions;
//
//        System.out.println("Inventory key has been set to O.");
//    }
//}