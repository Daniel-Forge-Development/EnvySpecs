package com.envyful.specs.forge;

import com.envyful.specs.forge.spec.AmIBossRequirement;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import net.minecraftforge.fml.common.Mod;

@Mod("envyspecs")
public class EnvySpecsForge {

    public EnvySpecsForge() {
        PokemonSpecificationProxy.register(new AmIBossRequirement());
    }
}
