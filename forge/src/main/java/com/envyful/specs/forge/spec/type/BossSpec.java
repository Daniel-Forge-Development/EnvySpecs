package com.envyful.specs.forge.spec.type;

import com.envyful.specs.forge.spec.AbstractSpecFlag;
import com.envyful.specs.forge.spec.data.Spec;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

@Spec(
        value = { "boss", "b" },
        target = Boolean.class
)
public class BossSpec extends AbstractSpecFlag<Boolean> {

    public BossSpec(String key, Boolean value) {
        super(key, value);
    }

    @Override
    public SpecValue<?> parse(@Nullable String s) {
        if (s == null) {
            return new BossSpec("b", true);
        }

        if (s.contains(":")) {
            String[] args = s.split(":");
            return new BossSpec(args[0], Boolean.parseBoolean(args[1]));
        }

        if (s.startsWith("!")) {
            return new BossSpec(s.replace("!", ""), false);
        }

        return new BossSpec(s, true);
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
        return entityPixelmon.getBossMode() != null && this.value;
    }

    @Override
    public boolean matches(Pokemon pokemon) {
        return false;
    }

    @Override
    public SpecValue<Boolean> clone() {
        return new BossSpec(this.key, this.value);
    }
}
