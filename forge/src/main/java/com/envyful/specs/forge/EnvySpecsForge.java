package com.envyful.specs.forge;

import com.envyful.specs.forge.spec.type.BossSpec;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = EnvySpecsForge.MOD_ID,
        name = "EnvySpecs Forge",
        version = EnvySpecsForge.VERSION,
        acceptableRemoteVersions = "*"
)
public class EnvySpecsForge {

    protected static final String MOD_ID = "envyspecs";
    protected static final String VERSION = "0.2.0";

    @Mod.Instance(MOD_ID)
    private static EnvySpecsForge instance;

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
        PokemonSpec.extraSpecTypes.add(new BossSpec("b", true));
    }

    public static EnvySpecsForge getInstance() {
        return instance;
    }
}
