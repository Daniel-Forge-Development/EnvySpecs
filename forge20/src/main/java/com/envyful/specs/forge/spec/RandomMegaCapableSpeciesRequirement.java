package com.envyful.specs.forge.spec;

import com.google.common.collect.Sets;
import com.pixelmonmod.api.pokemon.requirement.AbstractPokemonRequirement;
import com.pixelmonmod.api.registry.RegistryValue;
import com.pixelmonmod.api.requirement.Requirement;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RandomMegaCapableSpeciesRequirement extends AbstractPokemonRequirement<RegistryValue<Species>> {

    private static final Set<String> KEYS = Sets.newHashSet("randommega");
    private RegistryValue<Species> species;

    public RandomMegaCapableSpeciesRequirement() {
        super(KEYS);
    }

    public RandomMegaCapableSpeciesRequirement(RegistryValue<Species> species) {
        this();

        this.species = species;
    }

    @Override
    public List<Requirement<Pokemon, PixelmonEntity, ?>> createSimple(String key, String spec) {
        if (!spec.equalsIgnoreCase(key)) {
            return Collections.emptyList();
        }

        Species randomSpecies = PixelmonSpecies.getRandomSpecies();

        while (randomSpecies.getDefaultForm().getMegaForms().isEmpty()) {
            randomSpecies = PixelmonSpecies.getRandomSpecies();
        }

        return Collections.singletonList(createInstance(randomSpecies.getRegistryValue()));
    }

    @Override
    public Requirement<Pokemon, PixelmonEntity, RegistryValue<Species>> createInstance(RegistryValue<Species> species) {
        return new RandomMegaCapableSpeciesRequirement(species);
    }

    @Override
    public boolean isDataMatch(Pokemon pokemon) {
        return pokemon.getSpecies().is(this.species);
    }

    @Override
    public void applyData(Pokemon pokemon) {
        pokemon.setSpecies(this.species, true);
        pokemon.setForm(this.species.getValueUnsafe().getDefaultForm().getRandomMegaForm());
    }

    @Override
    public RegistryValue<Species> getValue() {
        return this.species;
    }
}
