package org.astemir.forestcraft.common.tileentity;


import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.tileentity.AnimatedTileEntity;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityBeeQueen;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCTileEntities;


public class TileEntityGiantHive extends AnimatedTileEntity {

    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(1.04f).speed(2);

    public static final Animation EXPLODE = new Animation(1,"explode").
            time(0.52f).speed(3);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,EXPLODE){

        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation == EXPLODE){
                if (whoBroken != null) {
                    spawnBeeQueen(whoBroken);
                    world.destroyBlock(getPos(), false);
                    world.playSound(getPos().getX(),getPos().getY(),getPos().getZ(), SoundEvents.ENTITY_WITHER_BREAK_BLOCK, SoundCategory.BLOCKS,1,1,false);
                    if (!world.isRemote){
                        for (int i = 0; i < 50; i++) {
                            ((ServerWorld)world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK,Blocks.BEEHIVE.getDefaultState()), getPos().getX()+RandomUtils.randomFloat(-2f,2f),getPos().getY()+RandomUtils.randomFloat(-1,2f),getPos().getZ()+RandomUtils.randomFloat(-2f,2f), 30, RandomUtils.randomFloat(-1f,1f),  RandomUtils.randomFloat(-1f,1f), RandomUtils.randomFloat(-1f,1f),0.5);
                        }
                    }
                }
            }
        }
    };

    private PlayerEntity whoBroken;

    public TileEntityGiantHive(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TileEntityGiantHive() {
        this(FCTileEntities.GIANT_HIVE);
    }


    public void spawnBeeQueen(PlayerEntity player){
        EntityBeeQueen beeQueen = new EntityBeeQueen(FCEntities.BEEQUEEN,world);
        beeQueen.moveToBlockPosAndAngles(pos, (float) ((Math.PI)-player.rotationYaw),0);
        if (world instanceof ServerWorld) {
            beeQueen.onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(pos), SpawnReason.NATURAL, null, null);
        }
        world.addEntity(beeQueen);
    }

    @Override
    public void tick() {
        factory.tick();
        if (!factory.isPlaying(EXPLODE)) {
            factory.playAnimation(IDLE);
        }
    }

    public void setWhoBroken(PlayerEntity whoBroken) {
        this.whoBroken = whoBroken;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}
