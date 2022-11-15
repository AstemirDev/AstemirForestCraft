package org.astemir.forestcraft.client.particle;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SporeParticle extends RisingParticle {

    private final IAnimatedSprite spriteSetWithAge;


    public SporeParticle(ClientWorld world, double x, double y, double z, float defaultMotionMultX, float defaultMotionMultY, float defaultMotionMultZ, double motionX, double motionY, double motionZ, float scale, IAnimatedSprite spriteWithAge, float colorMult, int maxAge, double yAccel, boolean canCollide, IAnimatedSprite spriteSetWithAge) {
        super(world, x, y, z, defaultMotionMultX, defaultMotionMultY, defaultMotionMultZ, motionX, motionY, motionZ, scale, spriteWithAge, colorMult, maxAge, yAccel, canCollide);
        this.spriteSetWithAge = spriteWithAge;
        this.motionX *= defaultMotionMultX;
        this.motionY *= defaultMotionMultY;
        this.motionZ *= defaultMotionMultZ;
        this.motionX += motionX;
        this.motionY += motionY;
        this.motionZ += motionZ;
        this.particleScale *= 0.75F * scale;
        this.maxAge = (int)((double)maxAge / ((double)world.rand.nextFloat() * 0.8D + 0.2D));
        this.maxAge = (int)((float)this.maxAge * scale);
        this.maxAge = Math.max(this.maxAge, 1);
        this.particleAlpha = 1;
        this.particleBlue = 1;
        this.particleGreen = 1;
        this.particleRed = 1;
        this.selectSpriteWithAge(spriteWithAge);
        this.canCollide = canCollide;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.motionY -= this.particleGravity;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.selectSpriteRandomly(this.spriteSetWithAge);
        }
    }


    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SporeParticle(worldIn,x,y,z,0.2f,0.1f,0.1f,xSpeed,ySpeed,zSpeed,2,spriteSet,10f,5,0.1f,false,spriteSet);
        }
    }
}
