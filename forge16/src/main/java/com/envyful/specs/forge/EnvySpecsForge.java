package com.envyful.specs.forge;

import com.envyful.specs.forge.spec.AmIBossRequirement;
import com.envyful.specs.forge.spec.MaxIVsWithMinPercentRequirement;
import com.envyful.specs.forge.spec.MinIVsRequirement;
import com.envyful.specs.forge.spec.NumIVsRequirement;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import net.minecraftforge.fml.common.Mod;

@Mod("envyspecs")
public class EnvySpecsForge {

    public EnvySpecsForge() {
        PokemonSpecificationProxy.register(new AmIBossRequirement());
        PokemonSpecificationProxy.register(new MaxIVsWithMinPercentRequirement());
        PokemonSpecificationProxy.register(new MinIVsRequirement());
        PokemonSpecificationProxy.register(new NumIVsRequirement());
    }
}
