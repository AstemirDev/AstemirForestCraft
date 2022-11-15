package org.astemir.api.common.particle;


import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.client.AColor;
import org.astemir.api.math.RandomUtils;

public class Particle3D {

    private IParticleData particle;
    private double speedX = 0;
    private double speedY = 0;
    private double speedZ = 0;
    private double sizeX = 0;
    private double sizeY = 0;
    private double sizeZ = 0;
    private int count = 0;
    private double speed = 0;
    private int renderTimes = 1;
    private boolean showFromDistance = false;
    private boolean randomSpeed = false;

    public Particle3D(BasicParticleType type) {
        this.particle = type;
    }

    public Particle3D(ParticleType type, BlockState state) {
        this.particle = new BlockParticleData(type,state);
    }

    public Particle3D(ParticleType type,ItemStack stack) {
        this.particle = new ItemParticleData(type,stack);
    }

    public Particle3D(AColor color) {
        this.particle = new RedstoneParticleData(color.r,color.g,color.b,color.a);
    }


    public Particle3D speed(double x, double y, double z) {
        this.speedX = x;
        this.speedY = y;
        this.speedZ = z;
        return this;
    }

    public Particle3D size(double x, double y, double z) {
        this.sizeX = x;
        this.sizeY = y;
        this.sizeZ = z;
        return this;
    }

    public Particle3D deltaSpeed(double speed) {
        this.speed = speed;
        return this;
    }


    public Particle3D count(int count) {
        this.count = count;
        return this;
    }


    public Particle3D renderTimes(int count) {
        this.renderTimes = count;
        return this;
    }


    public Particle3D distant() {
        this.showFromDistance = true;
        return this;
    }

    public Particle3D randomSpeed() {
        this.randomSpeed = true;
        return this;
    }

    public void play(World world,double x,double y,double z) {
        for (int i = 0; i < renderTimes; i++) {
            double sX = this.speedX;
            double sY = this.speedY;
            double sZ = this.speedZ;
            if (randomSpeed){
                sX*= RandomUtils.randomFloat(-1,1);
                sY*=RandomUtils.randomFloat(-1,1);
                sZ*=RandomUtils.randomFloat(-1,1);
            }
            if (world instanceof ServerWorld){
                ((ServerWorld)world).spawnParticle(particle,x+RandomUtils.randomFloat(-(float) sizeX, (float) sizeX),y+RandomUtils.randomFloat(-(float) sizeY, (float) sizeY),z+RandomUtils.randomFloat(-(float) sizeZ, (float) sizeZ),count,sX,sY,sZ,speed);
            }else {
                world.addOptionalParticle(particle, showFromDistance, x + RandomUtils.randomFloat(-(float) sizeX, (float) sizeX), y + RandomUtils.randomFloat(-(float) sizeY, (float) sizeY), z + RandomUtils.randomFloat(-(float) sizeZ, (float) sizeZ), sX, sY, sZ);
            }
        }
    }

}