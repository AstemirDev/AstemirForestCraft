package org.astemir.forestcraft.common.items.tools.scythes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.common.items.constructors.FCScytheItem;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

public class ItemEnderiteScythe extends FCScytheItem {


    public ItemEnderiteScythe() {
        super("forestcraft:enderite_scythe",FCItemTier.ENDERITE, 5, -2.7f, 5);
        rarity(Rarity.RARE);
        sound(FCSounds.SCYTHE_WHOOSH);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!playerIn.getCooldownTracker().hasCooldown(playerIn.getHeldItem(handIn).getItem())) {
            BlockRayTraceResult ray = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
            if (worldIn.getBlockState(ray.getPos().add(0, 1, 0)).isAir()) {
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(),60);
                for (int i = 0; i < 32; ++i) {
                    worldIn.addParticle(ParticleTypes.PORTAL, ray.getPos().getX()+RandomUtils.randomFloat(-0.5f,0.5f), ray.getPos().getY() + 0.5f + RandomUtils.randomFloat(0, 1.5f), ray.getPos().getZ()+RandomUtils.randomFloat(-0.5f,0.5f), RandomUtils.randomFloat(-0.2f, 0.2f), 0.0D, RandomUtils.randomFloat(-0.2f, 0.2f));
                }
                playerIn.setPosition(ray.getPos().getX(), ray.getPos().getY() + 1f, ray.getPos().getZ());
                for (int i = 0; i < 32; ++i) {
                    worldIn.addParticle(ParticleTypes.PORTAL, ray.getPos().getX()+RandomUtils.randomFloat(-0.5f,0.5f), ray.getPos().getY() + 0.5f + RandomUtils.randomFloat(0, 1.5f), ray.getPos().getZ()+RandomUtils.randomFloat(-0.5f,0.5f), RandomUtils.randomFloat(-0.2f, 0.2f), 0.0D, RandomUtils.randomFloat(-0.2f, 0.2f));
                }
                worldIn.playSound(ray.getPos().getX(), ray.getPos().getY(), ray.getPos().getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1, 1, false);
            }
        }
        return super.onRightClick(worldIn, playerIn, handIn);
    }
}
