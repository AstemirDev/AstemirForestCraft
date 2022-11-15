package org.astemir.forestcraft.client.particle;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DandelionSeedParticle extends SpriteTexturedParticle {


    private DandelionSeedParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.motionX *= (double) 0.8F;
        this.motionY *= (double) 0.8F;
        this.motionZ *= (double) 0.8F;
        this.motionY = (double) (this.rand.nextFloat() * 0.4F + 0.05F);
        this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
        this.maxAge = (int) (32.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    @Override
    public float getScale(float scaleFactor) {
        float f = ((float) this.age + scaleFactor) / (float) this.maxAge;
        return this.particleScale * (1.0F - f * f);
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.motionY -= 0.005D;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double) 0.999F;
            this.motionY *= (double) 0.999F;
            this.motionZ *= (double) 0.999F;
            if (this.onGround) {
                this.motionX *= (double) 0.7F;
                this.motionZ *= (double) 0.7F;
            }

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
            DandelionSeedParticle lavaparticle = new DandelionSeedParticle(worldIn, x, y, z);
            lavaparticle.selectSpriteRandomly(this.spriteSet);
            return lavaparticle;
        }
    }
}
