package org.astemir.forestcraft.registries;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.astemir.api.common.item.ITSRRendered;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.blocks.IFuel;
import org.astemir.forestcraft.common.blocks.ISeedable;
import org.astemir.forestcraft.common.blocks.building.*;
import org.astemir.forestcraft.common.blocks.decorative.*;
import org.astemir.forestcraft.common.blocks.doors.BlockArchaicDoor;
import org.astemir.forestcraft.common.blocks.mechanism.BlockBlockExcavator;
import org.astemir.forestcraft.common.blocks.mechanism.BlockElectrode;
import org.astemir.forestcraft.common.blocks.decorative.BlockBakudanTreasure;
import org.astemir.forestcraft.common.blocks.other.BlockSoulFire;
import org.astemir.forestcraft.common.blocks.plants.*;
import org.astemir.forestcraft.common.blocks.tileentities.*;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;

@Mod.EventBusSubscriber(modid = ForestCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCBlocks {



    public static final Block SEA_SPONGE  = createBlock(new BlockSeaSponge(),"sea_sponge");
    public static final Block LIGHT_BULB  = createBlock(new BlockLightBulb(),"light_bulb");
    public static final Block FOSSIL_BLOCK  = createBlock(Material.SAND,1f,1f,-1, SoundType.SAND,true, ToolType.SHOVEL,"fossil_block");
    public static final Block DEEP_ROCK  = createBlock(Material.ROCK,3f,3f,1,SoundType.STONE,true,ToolType.PICKAXE,"deep_rock");
    public static final Block PEARLSTONE  = createBlock(Material.ROCK,1f,1f,-1,SoundType.BASALT,true,ToolType.PICKAXE,"pearlstone");
    public static final Block MOSSY_PEARLSTONE  = createBlock(Material.ROCK,1f,1f,-1,SoundType.BASALT,true,ToolType.PICKAXE,"mossy_pearlstone");
    public static final Block SHINY_PEARLSTONE  = createBlock(Material.ROCK,1f,1f,-1,15,SoundType.BASALT,true,ToolType.PICKAXE,"shiny_pearlstone");
    public static final Block POLISHED_PEARLSTONE  = createBlock(Material.ROCK,1f,1f,-1,SoundType.BASALT,true,ToolType.PICKAXE,"polished_pearlstone");
    public static final Block GEM_ORE  = createBlock(Material.ROCK,1f,1f,-1,SoundType.BASALT,true,ToolType.PICKAXE,"gem_ore");
    public static final Block GEM_BLOCK  = createBlock(Material.IRON,1f,1f,-1,SoundType.BASALT,true,ToolType.PICKAXE,"gem_block");
    public static final Block LEATHER_BLOCK  = createBlock(Material.ORGANIC,1f,1f,-1,SoundType.NETHER_WART,false,ToolType.HOE,"leather_block");
    public static final Block ROTTEN_FLESH_BLOCK  = createBlock(Material.ORGANIC,1f,1f,-1,SoundType.NETHER_WART,false,ToolType.HOE,"rotten_flesh_block");
    public static final Block JEWEL_WART_BLOCK  = createBlock(Material.IRON,2f,2f,-1,SoundType.METAL,true,ToolType.PICKAXE,"jewel_wart_block");
    public static final Block BLUE_JEWEL_MASONRY  = createBlock(Material.ROCK,2f,2f,-1,SoundType.METAL,true,ToolType.PICKAXE,"blue_jewel_masonry");
    public static final Block GREEN_JEWEL_MASONRY  = createBlock(Material.ROCK,2f,2f,-1,SoundType.METAL,true,ToolType.PICKAXE,"green_jewel_masonry");
    public static final Block YELLOW_JEWEL_MASONRY  = createBlock(Material.ROCK,2f,2f,-1,SoundType.METAL,true,ToolType.PICKAXE,"yellow_jewel_masonry");
    public static final Block SULFUR_ORE  = createBlock(Material.ROCK,3f,3f,1,SoundType.STONE,true,ToolType.PICKAXE,"sulfur_ore");
    public static final Block NITER_ORE  = createBlock(Material.ROCK,3f,3f,1,SoundType.STONE,true,ToolType.PICKAXE,"niter_ore");
    public static final Block AQUAMARINE_ORE  = createBlock(Material.ROCK,1.5f,1.5f,2,SoundType.STONE,true,ToolType.PICKAXE,"aquamarine_ore");
    public static final Block PEARLSTONE_WALL  = createBlock(new WallBlock(AbstractBlock.Properties.from(PEARLSTONE)),"pearlstone_wall");
    public static final Block PEARLSTONE_STAIRS  = createBlock(new StairsBlock(PEARLSTONE.getDefaultState(),AbstractBlock.Properties.from(PEARLSTONE)),"pearlstone_stairs");
    public static final Block PEARLSTONE_SLAB  = createBlock(new SlabBlock(AbstractBlock.Properties.from(PEARLSTONE)),"pearlstone_slab");
    public static final Block NITER_BLOCK  = createBlock(Material.ROCK,3f,3f,1,SoundType.STONE,true,ToolType.PICKAXE,"niter_block");
    public static final Block SULFUR_BLOCK  = createBlock(Material.ROCK,3f,3f,1,SoundType.STONE,true,ToolType.PICKAXE,"sulfur_block");
    public static final Block POOL_TILE_BLOCK  = createBlock(Material.ROCK,2f,3f,1,SoundType.BONE,true,ToolType.PICKAXE,"pool_tile_block");
    public static final Block POOL_TILE_SLAB  = createBlock(new SlabBlock(AbstractBlock.Properties.from(POOL_TILE_BLOCK)),"pool_tile_slab");
    public static final Block POOL_TILE_STAIRS  = createBlock(new StairsBlock(POOL_TILE_BLOCK.getDefaultState(),AbstractBlock.Properties.from(POOL_TILE_BLOCK)),"pool_tile_stairs");
    public static final Block DEEP_ROCK_WALL  = createBlock(new WallBlock(AbstractBlock.Properties.from(DEEP_ROCK)),"deep_rock_wall");
    public static final Block DEEP_ROCK_STAIRS  = createBlock(new StairsBlock(DEEP_ROCK.getDefaultState(),AbstractBlock.Properties.from(DEEP_ROCK)),"deep_rock_stairs");
    public static final Block DEEP_ROCK_SLAB  = createBlock(new SlabBlock(AbstractBlock.Properties.from(DEEP_ROCK)),"deep_rock_slab");
    public static final Block POLISHED_DEEP_ROCK  = createBlock(Material.ROCK,3f,3f,1,SoundType.STONE,true,ToolType.PICKAXE,"polished_deep_rock");
    public static final Block ELECTRITE_BLOCK  = createBlock(Material.IRON,50.0F, 1200.0F,3,SoundType.METAL,true,ToolType.PICKAXE,"electrite_block");
    public static final Block ENDERITE_BLOCK  = createBlock(Material.IRON,50.0F, 1200.0F,4,SoundType.METAL,true,ToolType.PICKAXE,"enderite_block");
    public static final Block AQUAMARINE_BLOCK  = createBlock(Material.IRON,20.0F, 600.0F,4,SoundType.METAL,true,ToolType.PICKAXE,"aquamarine_block");
    public static final Block BLUEBERRY_BUSH_EMPTY  = createBlock(new BlockBlueberryBushEmpty(),"blueberry_bush_empty");
    public static final Block POMIDOR_SAPLING  = createBlock(new BlockPomidorSapling(),"pomidor_sapling");
    public static final Block CUCUMBER_SAPLING  = createBlock(new BlockWeirdCucumberSapling(),"weird_cucumber_sapling");
    public static final Block SAND_WITH_CROSS  = createBlock(new SandBlock(11098145, AbstractBlock.Properties.create(Material.SAND, MaterialColor.ADOBE).hardnessAndResistance(0.5F).sound(SoundType.SAND).harvestTool(ToolType.SHOVEL)),"sand_with_cross");
    public static final Block RED_SAND_WITH_CROSS  = createBlock(new SandBlock(11098145, AbstractBlock.Properties.create(Material.SAND, MaterialColor.ADOBE).hardnessAndResistance(0.5F).sound(SoundType.SAND).harvestTool(ToolType.SHOVEL)),"red_sand_with_cross");
    public static final Block DANDELION_FLUFF  = createBlock(new BlockDandelionFluff(),"dandelion_fluff");
    public static final Block BAKUDAN_TREASURE  = createBlock(new BlockBakudanTreasure(),"bakudan_treasure");
    public static final Block GIANT_HIVE  = createBlock(new BlockGiantHive(),"giant_hive");
    public static final Block SHARPED_LEAVES  = createBlock(new BlockSharpedLeaves(),"sharped_leaves");
    public static final Block BLUEBERRY_BUSH  = createBlock(new BlockBlueberryBush(),"blueberry_bush");
    public static final Block INSANE_WOOL  = createBlock(new BlockInsaneWool(),"insane_wool");
    public static final Block GEM_CRYSTAL  = createBlock(new BlockGemCrystal(),"gem_crystal_0");
    public static final Block SPORUM  = createBlock(new BlockSporum(),"sporum");
    public static final Block CROCUS_FLOWER  = createBlock(new BlockCrocusFlower(),"crocus_flower");
    public static final Block SNOWBERRY_BUSH  = createBlock(new BlockSnowberryBush(),"snowberry_bush");
    public static final Block SHEAF_OF_GRASS  = createBlock(new BlockSheafOfGrass(),"sheaf_of_grass");
    public static final Block GRASS_CARPET  = createBlock(new CarpetBlock(DyeColor.GREEN, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.GREEN).hardnessAndResistance(0.1F).sound(SoundType.PLANT)),"grass_carpet");
    public static final Block GRASS_HAY  = createBlock(new BlockGrassHay(),"grass_hay_block");
    public static final Block MYSTERY_PILE_MESA  = createBlock(new BlockMysteryPileMesa(),"mystery_pile_mesa");
    public static final Block MYSTERY_PILE_GRASS  = createBlock(new BlockMysteryPileGrass(),"mystery_pile_grass");
    public static final Block BLOCK_EXCAVATOR  = createBlock(new BlockBlockExcavator(),"block_excavator");
    public static final Block BONE_STRUCTURE_0  = createBlock(new BlockBoneStructure0(),"bone_structure_0");
    public static final Block BONE_STRUCTURE_1  = createBlock(new BlockBoneStructure1(),"bone_structure_1");
    public static final Block BONE_STRUCTURE_2  = createBlock(new BlockBoneStructure2(),"bone_structure_2");
    public static final Block IGUANA_KING_EGG  = createBlock(new BlockIguanaKingEgg(),"iguana_king_egg");
    public static final Block GROWN_DANDELION  = createBlock(new BlockGrownDandelion(),"dandelion_grown");
    public static final Block ELECTRODE  = createBlock(new BlockElectrode(),"electrode");
    public static final Block POTTED_DANDELION_GROWN  = createBlock(new FlowerPotBlock(()-> (FlowerPotBlock) Blocks.FLOWER_POT,()->LIGHT_BULB,AbstractBlock.Properties.from(Blocks.FLOWER_POT)),"potted_dandelion_grown");
    public static final Block POTTED_LIGHT_BULB  = createBlock(new FlowerPotBlock(()-> (FlowerPotBlock) Blocks.FLOWER_POT,()->LIGHT_BULB,AbstractBlock.Properties.from(Blocks.FLOWER_POT)),"potted_light_bulb");
    public static final Block ARCHAIC_STONE = createBlock(Material.ROCK,50.0F, 1200.0F,4,SoundType.STONE,true,ToolType.PICKAXE,"archaic_stone");
    public static final Block ARCHAIC_SYMBOLS = createBlock(Material.ROCK,50.0F, 1200.0F,4,SoundType.STONE,true,ToolType.PICKAXE,"archaic_symbols");
    public static final Block ARCHAIC_PATTERN_1 = createBlock(Material.ROCK,50.0F, 1200.0F,4,SoundType.STONE,true,ToolType.PICKAXE,"archaic_pattern_1");
    public static final Block POLISHED_ARCHAIC_STONE = createBlock(Material.ROCK,50.0F, 1200.0F,4,SoundType.STONE,true,ToolType.PICKAXE,"polished_archaic_stone");
    public static final Block MOSSY_ARCHAIC_STONE = createBlock(Material.ROCK,50.0F, 1200.0F,4,SoundType.STONE,true,ToolType.PICKAXE,"mossy_archaic_stone");
    public static final Block SLIGHT_MOSSY_ARCHAIC_STONE = createBlock(Material.ROCK,50.0F, 1200.0F,4,SoundType.STONE,true,ToolType.PICKAXE,"slight_mossy_archaic_stone");
    public static final Block ANCIENT_MONUMENT  = createBlock(new BlockAncientMonument(),"ancient_monument");
    public static final Block ARCHAIC_DOOR  = createBlock(new BlockArchaicDoor(),"archaic_door");
    public static final Block ANCIENT_CHEST  = createBlock(new BlockAncientChest(),"ancient_chest");
    public static final Block MOLTEN_BLOCK  = createBlock(new BlockMoltenBlock(),"molten_block");
    public static final Block MYSTERY_LEAVES  = createBlock(createLeavesBlock(),"mystery_leaves");
    public static final Block MYSTERY_GRASS  = createBlock(new TallGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS)),"mystery_grass");
    public static final Block MYSTERY_WOOD_SAPLING  = createBlock(new TallGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS)),"mystery_wood_sapling");
    public static final Block MYSTERY_WOOD  = createBlock(new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)),"mystery_wood");
    public static final Block MYSTERY_GRASS_BLOCK  = createBlock(new GrassBlock(AbstractBlock.Properties.from(Blocks.DIRT)),"mystery_grass_block");
    public static final Block MYSTERY_PLANKS  = createBlock(Material.WOOD,3f,3f,1,SoundType.WOOD,false,ToolType.AXE,"mystery_planks");
    public static final Block MYSTERY_WOOD_FENCE  = createBlock(new FenceBlock(AbstractBlock.Properties.from(MYSTERY_PLANKS)),"mystery_wood_fence");
    public static final Block MYSTERY_WOOD_GATE  = createBlock(new FenceGateBlock(AbstractBlock.Properties.from(MYSTERY_PLANKS)),"mystery_wood_fence_gate");
    public static final Block MYSTERY_WOOD_STAIRS  = createBlock(new StairsBlock(DEEP_ROCK.getDefaultState(),AbstractBlock.Properties.from(MYSTERY_PLANKS)),"mystery_wood_stairs");
    public static final Block MYSTERY_WOOD_SLAB  = createBlock(new SlabBlock(AbstractBlock.Properties.from(MYSTERY_PLANKS)),"mystery_wood_slab");
    public static final Block MYSTERY_WOOD_DOOR  = createBlock(new DoorBlock(AbstractBlock.Properties.from(MYSTERY_PLANKS)),"mystery_wood_door");
    public static final Block MYSTERY_WOOD_TRAPDOOR  = createBlock(new TrapDoorBlock(AbstractBlock.Properties.from(MYSTERY_PLANKS)),"mystery_wood_trapdoor");
    public static final Block MYSTERY_WOOD_PRESSURE_PLATE  = createBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,AbstractBlock.Properties.from(MYSTERY_PLANKS)),"mystery_wood_pressure_plate");
    public static final Block MYSTERY_WOOD_BUTTON  = createBlock(new WoodButtonBlock(AbstractBlock.Properties.from(MYSTERY_PLANKS)),"mystery_wood_button");


    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }


    private static LeavesBlock createLeavesBlock() {
        return new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setOpaque(FCBlocks::isntSolid).setBlocksVision(FCBlocks::isntSolid).setSuffocates(FCBlocks::isntSolid));
    }

    @SubscribeEvent
    public static void createBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockSoulFire().setRegistryName(new ResourceLocation("minecraft","soul_fire")));
        try {
            for (Field f : FCBlocks.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block) {
                    event.getRegistry().register((Block) obj);
                } else if (obj instanceof Block[]) {
                    for (Block block : (Block[]) obj) {
                        event.getRegistry().register(block);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SubscribeEvent
    public static void createBlockItems(RegistryEvent.Register<Item> event) {
        try {
            for (Field f : FCBlocks.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block && !(obj instanceof WallTorchBlock) && (!(obj instanceof FlowerPotBlock))) {
                    Item.Properties props = new Item.Properties();
                    props.group(FCItemGroups.BLOCKS);
                    if (obj instanceof ITSRRendered) {
                        props = ForestCraft.PROXY.setupISTER(props);
                    }
                    BlockItem itemBlock = new BlockItem((Block) obj, props){
                        @Override
                        public int getBurnTime(ItemStack itemStack) {
                            if (obj instanceof IFuel) {
                                return ((IFuel)obj).getBurnTime();
                            }
                            return -1;
                        }
                    };
                    if (!(obj instanceof ISeedable)) {
                        itemBlock.setRegistryName(((Block) obj).getRegistryName());
                        event.getRegistry().register(itemBlock);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Block createBlock(Material material,float hardness,float resistance, int harvestLevel, SoundType sound, boolean needTool, ToolType tool,String name){
        return createBlock(material,hardness,resistance,harvestLevel,-1,sound,needTool,tool,name);
    }

    public static Block createBlock(Material material,float hardness,float resistance,int harvestLevel, int lightLevel,SoundType sound, boolean needTool, ToolType tool,String name){
        AbstractBlock.Properties properties = AbstractBlock.Properties.create(material);
        properties.harvestLevel(harvestLevel).harvestTool(tool);
        properties.hardnessAndResistance(hardness,resistance);
        properties.sound(sound);
        if (lightLevel != -1){
            properties.setLightLevel((a)->lightLevel);
        }
        if (needTool) {
            properties.setRequiresTool();
        }
        return createBlock(new Block(properties),name);
    }
    
    public static Block createBlock(Block block, String name){
        block.setRegistryName(ForestCraft.MOD_ID,name);
        return block;
    }
}
