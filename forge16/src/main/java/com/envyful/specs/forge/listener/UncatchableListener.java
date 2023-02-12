package com.envyful.specs.forge.listener;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class UncatchableListener {

    @SubscribeEvent
    public void onReleaseAttempt(CaptureEvent.StartCapture event) {
        if (!event.getPokemon().isUncatachable()) {
            return;
        }

        event.setCatchRate(0);
        event.setCanceled(true);
    }

}
