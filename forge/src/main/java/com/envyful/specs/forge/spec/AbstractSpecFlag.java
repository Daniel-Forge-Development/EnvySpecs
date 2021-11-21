package com.envyful.specs.forge.spec;

import com.envyful.specs.forge.spec.data.Spec;
import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.pokemon.ISpecType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class AbstractSpecFlag<T> extends SpecValue<T> implements ISpecType {

    private final List<String> keys;
    private final Class<? extends SpecValue<T>> clazz;
    private final Class<T> valueClass;

    @SuppressWarnings("unchecked")
    public AbstractSpecFlag(String key, T value) {
        super(key, value);

        Spec spec = this.getClass().getAnnotation(Spec.class);

        if (spec == null) {
            this.keys = null;
            this.clazz = null;
            this.valueClass = null;
            return;
        }

        this.keys = Lists.newArrayList(spec.value());
        this.clazz = (Class<? extends SpecValue<T>>) this.getClass();
        this.valueClass = (Class<T>) spec.target();
    }

    @Override
    public Class<T> getValueClass() {
        return this.valueClass;
    }

    @Override
    public List<String> getKeys() {
        return Collections.unmodifiableList(this.keys);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound, SpecValue specValue) {
        if (specValue == null || specValue.value == null) {
            nbtTagCompound.setString(this.key, "");
        } else {
            if (specValue.value instanceof String) {
                nbtTagCompound.setString(this.key, (String) specValue.value);
            } else if (specValue.value instanceof Integer) {
                nbtTagCompound.setInteger(this.key, (int) specValue.value);
            } else if (specValue.value instanceof Double) {
                nbtTagCompound.setDouble(this.key, (double) specValue.value);
            } else if (specValue.value instanceof Long) {
                nbtTagCompound.setLong(this.key, (long) specValue.value);
            } else if (specValue.value instanceof Short) {
                nbtTagCompound.setShort(this.key, (short) specValue.value);
            } else if (specValue.value instanceof Byte) {
                nbtTagCompound.setByte(this.key, (byte) specValue.value);
            } else if (specValue.value instanceof UUID) {
                nbtTagCompound.setUniqueId(this.key, (UUID) specValue.value);
            }
        }
    }

    @Override
    public Class<? extends SpecValue<?>> getSpecClass() {
        return this.clazz;
    }

    @Override
    public String toParameterForm(SpecValue<?> specValue) {
        if (specValue == null || specValue.value == null) {
            return this.key;
        }

        return this.key + ":" + specValue.value;
    }

    @Override
    public void apply(EntityPixelmon entityPixelmon) {}

    @Override
    public void apply(Pokemon pokemon) {}
}
