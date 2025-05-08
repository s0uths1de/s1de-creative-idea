//package top.s0uths1de.betterexperience.client.mixin.client;//package top.s0uths1de.betterexperience.client.mixin.client;
//
//
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.option.GameOptions;
//import net.minecraft.client.option.KeyBinding;
//import net.minecraft.client.util.InputUtil;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.gen.Accessor;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.io.File;
//
//
//@Mixin(GameOptions.class)
//public class GameOptionsMixin {
//
////    @Mutable
////    @Final
////    @Shadow
////    public KeyBinding inventoryKey;
////
////    @Inject(method = "<init>", at = @At("TAIL"))
////    private void modifyInventoryKey(CallbackInfo ci) {
////        this.inventoryKey = new KeyBinding("key.inventory", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_O, "key.categories.inventory");
////        KeyBinding.updateKeysByCode();
////    }
//
////    @Accessor("inventoryKey")
////    static void setInventoryKey(KeyBinding keyBinding) {
////        throw new UnsupportedOperationException();
////    }
//
//
////    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 4))
////    private int injected(int value) {
////        return ++value;
////    }
//}
//
