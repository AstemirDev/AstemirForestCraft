package org.astemir.api.common.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.common.block.vanilla.ModBlock;
import org.astemir.api.common.item.AItemBlockItem;
import org.astemir.api.common.item.AItemToolType;
import javax.annotation.Nullable;
import java.util.*;

import static net.minecraft.block.Block.getStateId;

public class ABlock {

    public static final VoxelShape DEFAULT = VoxelShapes.fullCube();
    private VoxelShape shape = DEFAULT;
    private Map<Property, Object> states = new HashMap<>();
    private SoundType soundType = SoundType.STONE;
    private AItemToolType requiredTool = null;
    private Material material = Material.AIR;
    private MaterialColor color = MaterialColor.AIR;
    private float slipperiness = 0.6F;
    private float speedFactor = 1.0F;
    private float jumpFactor = 1.0F;
    private float hardness = 0f;
    private float resistance = 0f;
    private int harvestLevel = 0;
    private float blastResistance = 0f;
    private int lightLevel = 0;
    private boolean opaque = false;
    private boolean allowsSpawn = true;
    private boolean suffocates = true;
    private boolean air = false;
    private boolean ticking = false;
    private boolean canCollide = true;
    private String registryName;
    private boolean needToRegisterItem = true;
    private Item blockItem;
    private ItemGroup itemGroup = ItemGroup.BUILDING_BLOCKS;
    private int maxStack = 64;
    private int fuelTicks = -1;


    public ABlock(String registryName) {
        this.registryName = registryName;
    }

    public ActionResultType onRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return ActionResultType.PASS;
    }

    public void onTick(BlockState state, ServerWorld worldIn, BlockPos posIn, Random randomIn) {
    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
    }

    public void onPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
    }

    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state;
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state;
    }

    public void onHarvest(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        worldIn.playEvent(player, 2001, pos, getStateId(state));
        if (state.getBlock().isIn(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinTasks.func_234478_a_(player, false);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void onClientTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    }

    public void onRainTick(World worldIn, BlockPos pos) {
    }

    public boolean dropFromExplosion(Explosion explosionIn) {
        return true;
    }

    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return null;
    }

    public boolean hasTileEntity(BlockState state) {
        return false;
    }

    public void onEntityFall(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, 1.0F);
    }

    public void onEntityLand(IBlockReader worldIn, Entity entityIn) {
        entityIn.setMotion(entityIn.getMotion().mul(1.0D, 0.0D, 1.0D));
    }

    public VoxelShape shape() {
        return shape;
    }

    public ABlock shape(VoxelShape shape) {
        this.shape = shape;
        return this;
    }

    public boolean propagatesSkylightDown() {
        return false;
    }


    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.hasOpaqueCollisionShape(worldIn, pos) ? 0.2F : 1.0F;
    }

    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return getCollisionShape(state, reader, pos, context);
    }

    public void blockItemLink(Block block) {
        Item item = blockItem(block);
        if (blockItem == null) {
            if (item == null) {
                AItemBlockItem itemConstructor = (AItemBlockItem) new AItemBlockItem(registryName, block) {
                    @Override
                    public int getFuelTicks(ItemStack itemStack) {
                        if (fuelTicks() != -1) {
                            return fuelTicks();
                        }
                        return super.getFuelTicks(itemStack);
                    }
                }.itemGroup(itemGroup).maxStack(maxStack);
                blockItem = itemConstructor.register();
            }else{
                blockItem = item;
            }
        }
    }

    public Item blockItem(Block block) {
        return null;
    }


    public boolean isNeedToRegisterItem() {
        return needToRegisterItem;
    }

    public ItemGroup itemGroup() {
        return itemGroup;
    }

    public ABlock itemGroup(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
        return this;
    }

    public ABlock maxStack(int maxStack) {
        this.maxStack = maxStack;
        return this;
    }

    public int maxStack() {
        return maxStack;
    }

    public BlockRenderType renderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public ABlock requiredTool(AItemToolType type) {
        this.requiredTool = type;
        return this;
    }

    public ABlock soundType(SoundType soundType) {
        this.soundType = soundType;
        return this;
    }

    public ABlock states(Map<Property, Object> states) {
        this.states = states;
        return this;
    }

    public ABlock material(Material material) {
        this.material = material;
        return this;
    }

    public ABlock materialColor(MaterialColor color) {
        this.color = color;
        return this;
    }

    public ABlock slipperiness(float slipperiness) {
        this.slipperiness = slipperiness;
        return this;
    }

    public ABlock speedFactor(float speedFactor) {
        this.speedFactor = speedFactor;
        return this;
    }

    public ABlock jumpFactor(float jumpFactor) {
        this.jumpFactor = jumpFactor;
        return this;
    }

    public ABlock hardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public ABlock resistance(float resistance) {
        this.resistance = resistance;
        this.blastResistance(resistance);
        return this;
    }

    public ABlock blastResistance(float blastResistance) {
        this.blastResistance = blastResistance;
        return this;
    }


    public ABlock harvestLevel(int harvestLevel) {
        this.harvestLevel = harvestLevel;
        return this;
    }


    public ABlock lightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
        return this;
    }

    public ABlock opaque() {
        this.opaque = true;
        return this;
    }

    public ABlock allowsSpawn(boolean allowsSpawn) {
        this.allowsSpawn = allowsSpawn;
        return this;
    }

    public ABlock suffocates(boolean suffocates) {
        this.suffocates = suffocates;
        return this;
    }

    public ABlock fuel(int ticks) {
        this.fuelTicks = ticks;
        return this;
    }

    public ABlock air() {
        this.air = true;
        return this;
    }

    public ABlock ticking() {
        this.ticking = true;
        return this;
    }

    public ABlock canCollide(boolean canCollide) {
        this.canCollide = canCollide;
        return this;
    }

    public Map<Property, Object> states() {
        return states;
    }

    public SoundType soundType() {
        return soundType;
    }

    public int fuelTicks() {
        return fuelTicks;
    }

    public AItemToolType requiredTool() {
        return requiredTool;
    }

    public Material material() {
        return material;
    }

    public MaterialColor materialColor() {
        return color;
    }


    public float slipperiness() {
        return slipperiness;
    }

    public float speedFactor() {
        return speedFactor;
    }

    public float jumpFactor() {
        return jumpFactor;
    }

    public float hardness() {
        return hardness;
    }

    public float resistance() {
        return resistance;
    }

    public float blastResistance() {
        return blastResistance;
    }

    public int lightLevel() {
        return lightLevel;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public boolean allowsSpawn() {
        return allowsSpawn;
    }

    public boolean suffocates() {
        return suffocates;
    }

    public boolean isAir() {
        return air;
    }

    public boolean isTicking() {
        return ticking;
    }

    public boolean canCollide() {
        return canCollide;
    }

    public Block.Properties buildProperties() {
        Block.Properties properties = AbstractBlock.Properties.create(material(), materialColor());
        properties.jumpFactor(jumpFactor());
        properties.speedFactor(speedFactor());
        properties.slipperiness(slipperiness());
        properties.hardnessAndResistance(hardness(), resistance());
        if (requiredTool() != null) {
            properties.harvestTool(AItemToolType.toVanilla(requiredTool()));
            properties.setRequiresTool();
        }
        properties.setLightLevel((state) -> lightLevel());
        properties.setSuffocates((a, b, c) -> suffocates());
        properties.setOpaque((a, b, c) -> isOpaque());
        if (isAir()) {
            properties.setAir();
        }
        if (isTicking()) {
            properties.tickRandomly();
        }
        if (soundType != null) {
            properties.sound(soundType);
        }
        if (!canCollide) {
            properties.doesNotBlockMovement();
        }
        properties.harvestLevel(harvestLevel);
        properties.setAllowsSpawn((a, b, c, d) -> allowsSpawn);
        return properties;
    }


    public Block build(Block.Properties properties) {
        ModBlock resultItem = (ModBlock) new ModBlock(properties).blockConstructor(this);
        return resultItem;
    }

    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder){
    }

    public Map<Property,Object> getPlacementState(BlockItemUseContext context){
        return new HashMap<>();
    }

    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 0;
    }

    @Deprecated
    public <T extends Comparable<T>, V extends T> BlockState placement(Block block,BlockItemUseContext context){
        BlockState state = block.getDefaultState();
        for (Map.Entry<Property, Object> entry : getPlacementState(context).entrySet()) {
            state = state.with(entry.getKey(),(V)entry.getValue());
        }
        return state;
    }

    public float getBlockHardness(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos){
        float f = state.getBlockHardness(worldIn, pos);
        if (f == -1.0F) {
            return 0.0F;
        } else {
            int i = net.minecraftforge.common.ForgeHooks.canHarvestBlock(state, player, worldIn, pos) ? 30 : 100;
            return player.getDigSpeed(state, pos) / f / (float)i;
        }
    }


    public <T extends Comparable<T>, V extends T> void blockDefaultStates(Block block){
        StateContainer.Builder<Block, BlockState> builder = new StateContainer.Builder(block);
        for (Map.Entry<Property, Object> entry : states().entrySet()) {
            builder.add(entry.getKey());
        }
        fillStateContainer(builder);
        block.stateContainer = builder.func_235882_a_(Block::getDefaultState, BlockState::new);
        BlockState state = block.stateContainer.getBaseState();
        for (Map.Entry<Property, Object> entry : states().entrySet()) {
            state = state.with(entry.getKey(),(V)entry.getValue());
        }
        ((IModBlock)block).defaultState(state);
    }

    public Block register() {
        Block block = build(buildProperties());
        blockDefaultStates(block);
        block.setRegistryName(registryName);
        return block;
    }


    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.canCollide ? state.getShape(worldIn, pos) : VoxelShapes.empty();
    }

    public String getRegistryName() {
        return registryName;
    }

    public Item getBlockItem() {
        return blockItem;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape();
    }

}