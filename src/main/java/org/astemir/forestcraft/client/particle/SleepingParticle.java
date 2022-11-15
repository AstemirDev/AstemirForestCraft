package org.astemir.forestcraft.client.particle;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SleepingParticle extends SpriteTexturedParticle {


    private SleepingParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.motionX = 0.2F;
        this.motionY = 0.1F;
        this.motionZ = 0;
        this.maxAge = 16;
        this.particleScale = 1f;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    @Override
    public float getScale(float scaleFactor) {
        float f = ((float)this.age + scaleFactor) / (float)this.maxAge;
        return Math.max(0,this.particleScale * (0.5F - f/4));
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double) 0.9F;
            this.motionY *= (double) 0.9F;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SleepingParticle lavaparticle = new SleepingParticle(worldIn, x, y, z);
            lavaparticle.selectSpriteRandomly(this.spriteSet);
            return lavaparticle;
        }
    }
}
