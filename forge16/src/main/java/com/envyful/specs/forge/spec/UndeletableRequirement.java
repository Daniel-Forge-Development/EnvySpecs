package com.envyful.specs.forge.spec;

import com.envyful.specs.forge.Flags;
import com.google.common.collect.Sets;
import com.pixelmonmod.api.pokemon.requirement.AbstractBooleanPokemonRequirement;
import com.pixelmonmod.api.requirement.Requirement;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;

import java.util.Set;

public class UndeletableRequirement extends AbstractBooleanPokemonRequirement {

    private static final Set<String> KEYS = Sets.newHashSet(
            "undeleteable", "untrashable", "cannotrelease"
    );

    public UndeletableRequirement() {
        super(KEYS);
    }

    public UndeletableRequirement(boolean value) {
        super(KEYS, value);
    }

    @Override
    public Requirement<Pokemon, PixelmonEntity, Boolean> createInstance(Boolean aBoolean) {
        return new UndeletableRequirement(aBoolean);
    }

    @Override
    public boolean isDataMatch(Pokemon pokemon) {
        return this.value && pokemon.hasFlag(Flags.CANNOT_RELEASE);
    }

    @Override
    public void applyData(Pokemon pokemon) {
        if (this.value) {
            pokemon.addFlag(Flags.CANNOT_RELEASE);
        }
    }
}
