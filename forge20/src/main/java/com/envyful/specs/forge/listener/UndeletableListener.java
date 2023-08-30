package com.envyful.specs.forge.listener;

import com.envyful.api.forge.concurrency.UtilForgeConcurrency;
import com.envyful.specs.forge.Flags;
import com.pixelmonmod.pixelmon.api.events.PixelmonDeletedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PokemonStorage;
import com.pixelmonmod.pixelmon.api.storage.StoragePosition;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class UndeletableListener {

    @SubscribeEvent
    public void onReleaseAttempt(PixelmonDeletedEvent event) {
        if (!event.pokemon.hasFlag(Flags.CANNOT_RELEASE)) {
            return;
        }

        Pokemon pokemon = event.pokemon;
        StoragePosition position = pokemon.getPosition();
        PokemonStorage storage = pokemon.getStorage();

        UtilForgeConcurrency.runLater(() -> storage.set(position, pokemon), 5);
    }

}
