package org.astemir.api.common.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;


import java.util.List;
import java.util.Set;

public enum AItemToolType {

    PICKAXE,AXE,SHOVEL,HOE;

    private static final Set<Material> AXE_MATERIALS = Sets.newHashSet(Material.WOOD, Material.NETHER_WOOD, Material.PLANTS, Material.TALL_PLANTS, Material.BAMBOO, Material.GOURD);


    private static final Set<Block> SHOVEL_BLOCKS = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.SOUL_SOIL);
    private static final Set<Block> AXE_BLOCKS = Sets.newHashSet(Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.CRIMSON_BUTTON, Blocks.WARPED_BUTTON);
    private static final Set<Block> HOE_BLOCKS = ImmutableSet.of(Blocks.NETHER_WART_BLOCK, Blocks.WARPED_WART_BLOCK, Blocks.HAY_BLOCK, Blocks.DRIED_KELP_BLOCK, Blocks.TARGET, Blocks.SHROOMLIGHT, Blocks.SPONGE, Blocks.WET_SPONGE, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES);
    private static final Set<Block> PICKAXE_BLOCKS = ImmutableSet.of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.NETHER_GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD);


    public static ToolType toVanilla(AItemToolType type){
        switch (type){
            case PICKAXE: return ToolType.PICKAXE;
            case AXE: return ToolType.AXE;
            case SHOVEL: return ToolType.SHOVEL;
            case HOE: return ToolType.HOE;
        }
        return null;
    }

    public static Set<Block> getEffectiveOnBlocks(AItemToolType type){
        switch (type){
            case AXE: return AXE_BLOCKS;
            case PICKAXE: return PICKAXE_BLOCKS;
            case SHOVEL: return SHOVEL_BLOCKS;
            case HOE: return HOE_BLOCKS;
        }
        return Sets.newHashSet();
    }

    public static Set<Material> getEffectiveOnMaterials(AItemToolType type){
        if (type == AXE){
            return AXE_MATERIALS;
        }
        return Sets.newHashSet();
    }


    private static float destroySpeed(ItemStack stack, BlockState state, AItemToolType toolType){
        //if (stack.getItem().getToolTypes(stack).stream().anyMatch(e -> state.isToolEffective(e))) return ((IModToolItem)stack.getItem()).getEfficiency();
        return getEffectiveOnBlocks(toolType).contains(state.getBlock()) ? ((IModToolItem)stack.getItem()).getEfficiency() : 1.0F;
    }

    public static float getDestroySpeed(ItemStack stack, BlockState state) {
        Item item = stack.getItem();
        if (item instanceof IModToolItem) {
            List<AItemToolType> toolTypes = ((AItemTool)((IModItem)item).itemConstructor()).getToolTypes();
            Material material = state.getMaterial();
            if (toolTypes.contains(PICKAXE)) {
                float pickaxeSpeed = material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? destroySpeed(stack, state, PICKAXE) : ((IModToolItem) item).getEfficiency();
                if (pickaxeSpeed > 1){
                    return pickaxeSpeed;
                }
            }
            if (toolTypes.contains(AXE)) {
                float axeSpeed = getEffectiveOnMaterials(AXE).contains(material) ? ((IModToolItem) item).getEfficiency() : destroySpeed(stack,state,AXE);
                if (axeSpeed > 1){
                    return axeSpeed;
                }
            }
            if (toolTypes.contains(HOE)) {
                float hoeSpeed = destroySpeed(stack, state, HOE);
                if (hoeSpeed > 1){
                    return hoeSpeed;
                }
            }
            if (toolTypes.contains(SHOVEL)) {
                float shovelSpeed = destroySpeed(stack, state, SHOVEL);
                if (shovelSpeed > 1){
                    return shovelSpeed;
                }
            }
        }
        return 1;
    }
}
