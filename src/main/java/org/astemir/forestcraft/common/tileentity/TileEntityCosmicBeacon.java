package org.astemir.forestcraft.common.tileentity;


import net.minecraft.entity.SpawnReason;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.common.tileentity.AnimatedTileEntity;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityCosmicFiend;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.forestcraft.registries.FCTileEntities;

public class TileEntityCosmicBeacon extends AnimatedTileEntity {


    public static final Animation BASE_ROTATION = new Animation(0,"base_rotation").
            loop().
            time(2.08f).speed(2);

    public static final Animation OPEN = new Animation(1,"open").
            time(2.6f).speed(2);

    public static final Animation LOOP = new Animation(3,"loop").
            loop().
            time(0.52f).conflict(1);



    private int ticksAfterOpen = 0;

    private AnimationFactory factory = new AnimationFactory(this,BASE_ROTATION,OPEN,LOOP){
        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation == OPEN){
                if (!world.isRemote) {
                    ((ServerWorld)world).getServer().getPlayerList().sendPacketToAllPlayers(new SPlaySoundEffectPacket(FCSounds.COSMIC_BEACON_ACTIVATE.get(), SoundCategory.BLOCKS, pos.getX(),pos.getY(),pos.getZ(),50, 1f));
                }
                factory.playAnimation(LOOP);
            }
        }
    };

    public TileEntityCosmicBeacon() {
        super(FCTileEntities.COSMIC_BEACON);
    }

    @Override
    public void tick() {
        factory.tick();
        factory.playAnimation(BASE_ROTATION);
        if (factory.isPlaying(LOOP)){
            ticksAfterOpen++;
        }
        if (ticksAfterOpen == 100) {
            if (!world.isRemote) {
                ((ServerWorld)world).getServer().getPlayerList().sendPacketToAllPlayers(new SPlaySoundEffectPacket(FCSounds.COSMOS_ECHO.get(), SoundCategory.BLOCKS, pos.getX(),pos.getY()+20,pos.getZ(),50, 1f));
            }
        }else
        if (ticksAfterOpen == 200){
            spawnCosmicFiend();
            world.destroyBlock(getPos(),false,null);
        }
    }


    public void spawnCosmicFiend(){
        EntityCosmicFiend cosmicFiend = new EntityCosmicFiend(FCEntities.COSMIC_FIEND,world);
        cosmicFiend.moveToBlockPosAndAngles(pos.add(0,50,0),0,0);
        if (world instanceof ServerWorld) {
            cosmicFiend.onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(pos), SpawnReason.NATURAL, null, null);
        }
        world.addEntity(cosmicFiend);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 256.0D;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}
