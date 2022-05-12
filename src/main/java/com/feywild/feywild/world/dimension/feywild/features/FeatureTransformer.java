package com.feywild.feywild.world.dimension.feywild.features;

import io.github.noeppi_noeppi.libx.mod.registration.Registerable;
import io.github.noeppi_noeppi.libx.mod.registration.RegistryTransformer;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FeatureTransformer implements RegistryTransformer {

    public static final FeatureTransformer INSTANCE = new FeatureTransformer();

    private FeatureTransformer() {

    }

    @Nullable
    @Override
    public Object getAdditional(ResourceLocation id, Object object) {
        Object actual;
        Consumer<Registry<?>> bindHolder;
        if (object instanceof NoneFeatureHolder<?> ref) {
            actual = ref.value();
            bindHolder = registry -> {
                //noinspection unchecked
                ((NoneFeatureHolder<Object>) ref).register(((Registry<Object>) registry), id);
            };
        } else {
            actual = object;
            bindHolder = registry -> {
            };
        }

        if (actual instanceof ConfiguredFeature<?, ?> feature) {
            return new Registerable() {

                @Override
                public void registerCommon(ResourceLocation id, Consumer<Runnable> defer) {
                    Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, feature);
                    bindHolder.accept(BuiltinRegistries.CONFIGURED_FEATURE);
                }
            };
        } else if (actual instanceof PlacedFeature placement) {
            return new Registerable() {

                @Override
                public void registerCommon(ResourceLocation id, Consumer<Runnable> defer) {
                    Registry.register(BuiltinRegistries.PLACED_FEATURE, id, placement);
                    bindHolder.accept(BuiltinRegistries.PLACED_FEATURE);
                }
            };
        } else if (actual instanceof ConfiguredStructureFeature<?, ?> structure) {
            return new Registerable() {

                @Override
                public void registerCommon(ResourceLocation id, Consumer<Runnable> defer) {
                    Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, id, structure);
                    bindHolder.accept(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE);
                }
            };
        } else if (actual instanceof ConfiguredWorldCarver<?> carver) {
            return new Registerable() {

                @Override
                public void registerCommon(ResourceLocation id, Consumer<Runnable> defer) {
                    Registry.register(BuiltinRegistries.CONFIGURED_CARVER, id, carver);
                    bindHolder.accept(BuiltinRegistries.CONFIGURED_CARVER);
                }
            };
        } else if (actual instanceof StructureSet set) {
            return new Registerable() {

                @Override
                public void registerCommon(ResourceLocation id, Consumer<Runnable> defer) {
                    Registry.register(BuiltinRegistries.STRUCTURE_SETS, id, set);
                    bindHolder.accept(BuiltinRegistries.STRUCTURE_SETS);
                }
            };
        } else {
            return null;
        }
    }
}
