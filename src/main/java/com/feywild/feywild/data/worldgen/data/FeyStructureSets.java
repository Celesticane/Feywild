package com.feywild.feywild.data.worldgen.data;

import io.github.noeppi_noeppi.mods.sandbox.datagen.ext.StructureSetData;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class FeyStructureSets extends StructureSetData {

    private final FeyStructures structures = this.resolve(FeyStructures.class);

    public final Holder<StructureSet> overworldHouses = this.structureSet()
            .entry(this.structures.library)
            .entry(this.structures.blacksmith)
            .placeRandom(50, 20)
            .frequency(0.9f)
            .build();

    /*
    public final Holder<StructureSet> worldTrees = this.structureSet()
            .entry(this.structures.springWorldTree)
            .entry(this.structures.summerWorldTree)
            .entry(this.structures.autumnWorldTree)
            .entry(this.structures.winterWorldTree)
            .placeRandom(50, 20)
            .spreadType(RandomSpreadType.TRIANGULAR)
            .frequency(0.6f)
            .build();
    */
    public final Holder<StructureSet> beekeep = this.simple(this.structures.beekeep, 15, 8, 0.9f).build();
    public final Holder<StructureSet> feyGeode = this.simple(this.structures.feyGeode, 8, 4, 0.9f).build();
    public final Holder<StructureSet> dwarvenForge = this.simple(this.structures.dwarvenForge, 15, 6, 0.9f).build();

    public FeyStructureSets(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("SameParameterValue")
    private RandomPlacementBuilder simple(Holder<Structure> structure, int spacing, int separation, float frequency) {
        return this.structureSet()
                .entry(structure)
                .placeRandom(spacing, separation)
                .frequency(frequency);
    }
}
