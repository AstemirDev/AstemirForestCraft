package org.astemir.forestcraft.client.particle;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.client.FCParticleRenderType;

@OnlyIn(Dist.CLIENT)
public class StardustParticle extends SpriteTexturedParticle {

    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;
    private final IAnimatedSprite spriteSetWithAge;

    protected StardustParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ,IAnimatedSprite spriteSetWithAge) {
        super(world, x, y, z);
        this.spriteSetWithAge = spriteSetWithAge;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.portalPosX = this.posX;
        this.portalPosY = this.posY;
        this.portalPosZ = this.posZ;
        this.particleScale = (float)((double)this.particleScale * 1.5D);
        this.maxAge = 4;
        this.selectSpriteWithAge(spriteSetWithAge);
    }

    public IParticleRenderType getRenderType() {
        return FCParticleRenderType.COSMIC;
    }

    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }


    public float getScale(float scaleFactor) {
        float f = 1.0F - ((float)this.age%4 + scaleFactor) / ((float)this.maxAge * 1F);
        return this.particleScale * f;
    }


    @Override
    public int getBrightnessForRender(float partialTick) {
        return 15728880;
    }


    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= 20) {
            this.setExpired();
        } else {
            float f = (float)this.age / (float)this.maxAge;
            this.posX += this.motionX * (double)f;
            this.posY += this.motionY * (double)f;
            this.posZ += this.motionZ * (double)f;
            this.setSprite(spriteSetWithAge.get(age%4,4));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            StardustParticle portalparticle = new StardustParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed,spriteSet);
            portalparticle.selectSpriteWithAge(this.spriteSet);
            return portalparticle;
        }
    }
}
