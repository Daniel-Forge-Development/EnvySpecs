package com.envyful.specs.forge;

import com.envyful.api.command.CommandFactory;
import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.specs.forge.command.DebugSpecCommand;
import com.envyful.specs.forge.spec.type.BossSpec;
import com.envyful.specs.forge.spec.type.GenerationSpec;
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

    private ForgeCommandFactory commandFactory = new ForgeCommandFactory();

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
        PokemonSpec.extraSpecTypes.add(new BossSpec("b", true));
        PokemonSpec.extraSpecTypes.add(new GenerationSpec("gen", 1));

        this.commandFactory.registerCommand(event.getServer(), new DebugSpecCommand());
    }

    public static EnvySpecsForge getInstance() {
        return instance;
    }
}
