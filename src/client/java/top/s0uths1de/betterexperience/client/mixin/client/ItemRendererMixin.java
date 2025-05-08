package top.s0uths1de.betterexperience.client.mixin.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.s0uths1de.betterexperience.client.BetterExperienceClient;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V", at = @At("HEAD"))
    private void renderItem(LivingEntity entity, ItemStack stack, ItemDisplayContext displayContext, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci) {
        if (BetterExperienceClient.isInspecting) {
            float progress = (float) BetterExperienceClient.inspectionTick / 120.0F;
            progress = (progress % 0.5F) * 2;

            float x = 0, y = 0, z = 0;

            if (progress <= 0.25F) {
                // 移入阶段
                x = progress * 4;
            } else if (progress <= 0.41666666F) {
                // 停顿阶段
                x = 1;
            } else {
                // 移回阶段
                x = (1 - (progress - 0.41666666F) * 4);
            }

            // 保存MatrixStack的状态
            matrices.push();

            // 应用位置变换
            matrices.translate(x, y, z);
        }
    }

    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V", at = @At("RETURN"))
    private void postRenderItem(LivingEntity entity, ItemStack stack, ItemDisplayContext displayContext, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci) {
        if (BetterExperienceClient.isInspecting) {
            // 恢复MatrixStack的状态
            matrices.pop();
        }
    }
}