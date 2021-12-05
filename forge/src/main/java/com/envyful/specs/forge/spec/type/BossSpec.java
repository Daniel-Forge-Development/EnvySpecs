package com.envyful.specs.forge.spec.type;

import com.envyful.specs.forge.spec.AbstractSpecFlag;
import com.envyful.specs.forge.spec.data.Spec;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumBossMode;
import net.minecraft.nbt.NBTTagCompound;

@Spec(
        value = { "isboss" },
        target = Boolean.class
)
public class BossSpec extends AbstractSpecFlag<Boolean> {

    public BossSpec(String key, boolean value) {
        super(key, value, BossSpec::new);
    }

    @Override
    protected Boolean parse(String key, String value, boolean negate) {
        if (value == null) {
            return !negate;
        }

        boolean bool = Boolean.parseBoolean(value);

        if (!bool) {
            if (value.equalsIgnoreCase("1")) {
                bool = true;
            }
        }

        if (negate) {
            bool = !bool;
        }

        return bool;
    }

    @Override
    public SpecValue<?> readFromNBT(NBTTagCompound nbtTagCompound) {
        for (String s : this.getKeys()) {
            if (nbtTagCompound.hasKey(s)) {
                return new BossSpec(s, nbtTagCompound.getBoolean(s));
            }
        }

        return null;
    }

    @Override
    public boolean matches(EntityPixelmon entityPixelmon) {
        return entityPixelmon.getBossMode() != null && entityPixelmon.getBossMode() != EnumBossMode.NotBoss && this.value;
    }

    @Override
    public boolean matches(Pokemon pokemon) {
        return false;
    }
}
