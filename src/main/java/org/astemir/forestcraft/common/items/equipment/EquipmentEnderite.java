package org.astemir.forestcraft.common.items.equipment;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AWeaponToolSet;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

public class EquipmentEnderite extends AWeaponToolSet {

    public EquipmentEnderite() {
        super(FCItemTier.ENDERITE);
        swordItemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        pickaxeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        axeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        shovelItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SHOVELS);
        hoeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.HOES);
        sword("forestcraft:enderite_sword",4, -2.2f);
        pickaxe("forestcraft:enderite_pickaxe",1, -2.8f);
        axe("forestcraft:enderite_axe",6, -3f);
        shovel("forestcraft:enderite_shovel",1.5f, -3f);
        hoe("forestcraft:enderite_hoe",-4, 0f);
        getSword().fireImmune().rarity(Rarity.RARE);
        getPickaxe().fireImmune().rarity(Rarity.RARE);
        getAxe().fireImmune().rarity(Rarity.RARE);
        getShovel().fireImmune().rarity(Rarity.RARE);
        getHoe().fireImmune().rarity(Rarity.RARE);
    }

    @Override
    public void onRightClick(AItem item, World worldIn, PlayerEntity playerIn, Hand handIn) {
        teleport(playerIn.getHeldItem(handIn).getItem(),playerIn,worldIn);
    }

    private void teleport(Item item, PlayerEntity playerIn, World worldIn) {
        if (!playerIn.getCooldownTracker().hasCooldown(item)) {
            BlockRayTraceResult ray = EntityUtils.rayTrace(worldIn,playerIn, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE);
            if (worldIn.getBlockState(ray.getPos().add(0, 1, 0)).isAir()) {
                playerIn.getCooldownTracker().setCooldown(item, 60);
                for (int i = 0; i < 32; ++i) {
                    worldIn.addParticle(ParticleTypes.PORTAL, ray.getPos().getX() + RandomUtils.randomFloat(-0.5f, 0.5f), ray.getPos().getY() + 0.5f + RandomUtils.randomFloat(0, 1.5f), ray.getPos().getZ() + RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(-0.2f, 0.2f), 0.0D, RandomUtils.randomFloat(-0.2f, 0.2f));
                }
                playerIn.setPosition(ray.getPos().getX(), ray.getPos().getY() + 1f, ray.getPos().getZ());
                for (int i = 0; i < 32; ++i) {
                    worldIn.addParticle(ParticleTypes.PORTAL, ray.getPos().getX() + RandomUtils.randomFloat(-0.5f, 0.5f), ray.getPos().getY() + 0.5f + RandomUtils.randomFloat(0, 1.5f), ray.getPos().getZ() + RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(-0.2f, 0.2f), 0.0D, RandomUtils.randomFloat(-0.2f, 0.2f));
                }
                worldIn.playSound(ray.getPos().getX(), ray.getPos().getY(), ray.getPos().getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1, 1, false);
            }
        }
    }
}
