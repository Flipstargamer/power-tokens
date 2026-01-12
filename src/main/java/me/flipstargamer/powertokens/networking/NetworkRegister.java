package me.flipstargamer.powertokens.networking;

import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.networking.power_selector.PowerPickerClosePayload;
import me.flipstargamer.powertokens.networking.power_selector.PowerPickerSelectPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = PowerTokens.MOD_ID)
public class NetworkRegister {
    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1")
                .executesOn(HandlerThread.NETWORK);

        registrar.playToServer(
                PowerPickerClosePayload.TYPE,
                PowerPickerClosePayload.STREAM_CODEC,
                PowerPickerClosePayload::handleServer
        );

        registrar.playToServer(
                PowerPickerSelectPayload.TYPE,
                PowerPickerSelectPayload.STREAM_CODEC,
                PowerPickerSelectPayload::handleServer
        );
    }

    @SubscribeEvent
    public static void registerClient(RegisterClientPayloadHandlersEvent event) {
    }
}
