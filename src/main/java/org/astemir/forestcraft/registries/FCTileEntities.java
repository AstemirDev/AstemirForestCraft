package org.astemir.forestcraft.registries;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.tileentity.*;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = ForestCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCTileEntities {


    public static final TileEntityType<TileEntityGiantHive> GIANT_HIVE = createTileEntity(TileEntityType.Builder.create(TileEntityGiantHive::new, FCBlocks.GIANT_HIVE).build(null),"giant_hive");
    public static final TileEntityType<TileEntityIguanaKingEgg> IGUANA_KING_EGG = createTileEntity(TileEntityType.Builder.create(TileEntityIguanaKingEgg::new, FCBlocks.IGUANA_KING_EGG).build(null),"iguana_king_egg");
    public static final TileEntityType<TileEntityAncientMonument> ANCIENT_MONUMENT = createTileEntity(TileEntityType.Builder.create(TileEntityAncientMonument::new, FCBlocks.ANCIENT_MONUMENT).build(null),"ancient_monument");
    public static final TileEntityType<TileEntityAncientChest> ANCIENT_CHEST = createTileEntity(TileEntityType.Builder.create(TileEntityAncientChest::new,FCBlocks.ANCIENT_CHEST).build(null),"ancient_chest");
    public static final TileEntityType<TileEntityCosmicBeacon> COSMIC_BEACON = createTileEntity(TileEntityType.Builder.create(TileEntityCosmicBeacon::new,FCNewBlocks.COSMIC_BEACON).build(null),"cosmic_beacon");


    public static TileEntityType createTileEntity(TileEntityType type, String name){
        type.setRegistryName(ForestCraft.MOD_ID,name);
        return type;
    }


    @SubscribeEvent
    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        try {
            for (Field f : FCTileEntities.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof TileEntityType) {
                    event.getRegistry().register((TileEntityType) obj);
                } else if (obj instanceof TileEntityType[]) {
                    for (TileEntityType te : (TileEntityType[]) obj) {
                        event.getRegistry().register(te);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
