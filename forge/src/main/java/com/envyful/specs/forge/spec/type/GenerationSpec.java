package com.envyful.specs.forge.spec.type;

import com.envyful.specs.forge.spec.AbstractSpecFlag;
import com.envyful.specs.forge.spec.data.Spec;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumBossMode;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

@Spec(
        value = { "gen" },
        target = Integer.class
)
public class GenerationSpec extends AbstractSpecFlag<Integer> {

    public GenerationSpec(String key, int value) {
        super(key, value, GenerationSpec::new);
    }

    @Override
    protected Integer parse(String key, String value, boolean negate) {
        return -1;
    }

    @Override
    public SpecValue<?> readFromNBT(NBTTagCompound nbtTagCompound) {
        for (String s : this.getKeys()) {
            if (nbtTagCompound.hasKey(s)) {
                return new GenerationSpec(s, nbtTagCompound.getInteger(s));
            }
        }

        return null;
    }

    @Override
    public boolean matches(EntityPixelmon entityPixelmon) {
        return entityPixelmon.getSpecies().getGeneration() == this.value;
    }

    @Override
    public boolean matches(Pokemon pokemon) {
        return pokemon.getSpecies().getGeneration() == this.value;
    }

    @Override
    public void apply(EntityPixelmon entityPixelmon) {
        entityPixelmon.getPokemonData().setSpecies(this.getRandomFromGeneration(), true);
    }

    @Override
    public void apply(Pokemon pokemon) {
        pokemon.setSpecies(this.getRandomFromGeneration(), true);
    }

    @Override
    public void apply(NBTTagCompound nbt) {
        nbt.setInteger("ndex", this.getRandomFromGeneration().getNationalPokedexInteger());
    }

    private EnumSpecies getRandomFromGeneration() {
        EnumSpecies species = EnumSpecies.randomPoke();

        while (species.getGeneration() != this.value) {
            species = EnumSpecies.randomPoke();
        }

        return species;
    }
}
