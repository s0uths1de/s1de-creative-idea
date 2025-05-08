package top.s0uths1de.betterexperience;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import top.s0uths1de.betterexperience.packet.MarkBeaconPacket;

public class BetterExperience implements ModInitializer {

    private static final String MOD_ID = "betterexperience";
    private static final Identifier MARK_BEACON_PACKET_ID = Identifier.of(MOD_ID, "mark_beacon");
    @Override
    public void onInitialize() {
//        ServerPlayNetworking.registerGlobalReceiver(MARK_BEACON_PACKET_ID, MarkBeaconPacket::register);
    }
}
