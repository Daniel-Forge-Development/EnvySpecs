package com.envyful.specs.forge;

import com.envyful.specs.forge.listener.UndeletableListener;
import com.envyful.specs.forge.spec.*;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.fml.common.Mod;

@Mod("envyspecs")
public class EnvySpecsForge {

    public EnvySpecsForge() {
        Pixelmon.EVENT_BUS.register(new UndeletableListener());

        PokemonSpecificationProxy.register(new AmIBossRequirement());
        PokemonSpecificationProxy.register(new MaxIVsWithMinPercentRequirement());
        PokemonSpecificationProxy.register(new MinIVsRequirement());
        PokemonSpecificationProxy.register(new NumIVsRequirement());
        PokemonSpecificationProxy.register(new UndeletableRequirement());
    }
}
