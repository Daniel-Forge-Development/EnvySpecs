package com.envyful.specs.forge.spec;

import com.google.common.collect.Sets;
import com.pixelmonmod.api.pokemon.requirement.AbstractIntegerPokemonRequirement;
import com.pixelmonmod.api.requirement.Requirement;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;

import java.util.Set;

public class LevelLessThanEqualToRequirement extends AbstractIntegerPokemonRequirement {

    private static final Set<String> KEYS = Sets.newHashSet(
            "lvllteq", "lvllessthanequalto"
    );

    public LevelLessThanEqualToRequirement() {
        super(KEYS, 10);
    }

    public LevelLessThanEqualToRequirement(int value) {
        super(KEYS, 10, value);
    }

    @Override
    public Requirement<Pokemon, PixelmonEntity, Integer> createInstance(Integer integer) {
        return new LevelLessThanEqualToRequirement(integer);
    }

    @Override
    public boolean isDataMatch(Pokemon pokemon) {
        return pokemon.getPokemonLevel() <= this.value;
    }

    @Override
    public void applyData(Pokemon pokemon) {

    }
}
