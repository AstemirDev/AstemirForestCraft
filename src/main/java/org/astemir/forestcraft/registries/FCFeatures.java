package org.astemir.forestcraft.registries;


import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.astemir.forestcraft.common.world.feature.*;

import java.lang.reflect.Field;

public class FCFeatures {

    public static final GiantHiveFeature GIANT_HIVE_FEATURE = (GiantHiveFeature) new GiantHiveFeature().setRegistryName("forestcraft:giant_hive");
    public static final BlueberryBushFeature BLUEBERRY_FEATURE = (BlueberryBushFeature) new BlueberryBushFeature().setRegistryName("forestcraft:blueberry_bush");
    public static final FossilFeature FOSSIL_FEATURE = (FossilFeature) new FossilFeature().setRegistryName("forestcraft:fossils");
    public static final CrystalCaveFeature CRYSTAL_CAVE_FEATURE = (CrystalCaveFeature) new CrystalCaveFeature().setRegistryName("forestcraft:crystal_cave");
    public static final SeaSpongeFeature SEA_SPONGE_FEATURE = (SeaSpongeFeature) new SeaSpongeFeature().setRegistryName("forestcraft:sea_sponge");
    public static final AquamarineOreFeature AQUAMARINE_ORE_FEATURE = (AquamarineOreFeature) new AquamarineOreFeature().setRegistryName("forestcraft:aquamarine_ore");
    public static final BakudanTreasureFeature BAKUDAN_TREASURE_FEATURE = (BakudanTreasureFeature) new BakudanTreasureFeature().setRegistryName("forestcraft:bakudan_treasure");


    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        try {
            for (Field f : FCFeatures.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Feature) {
                    event.getRegistry().register((Feature<?>) obj);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FCConfiguredFeatures.registerConfiguredFeatures();
    }

}