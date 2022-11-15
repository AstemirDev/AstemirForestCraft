package org.astemir.forestcraft.common.items.equipment;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AWeaponToolSet;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;


public class EquipmentMolten extends AWeaponToolSet {

    public EquipmentMolten() {
        super(FCItemTier.MOLTEN);
        lore(new TranslationTextComponent(FCStrings.MOLTEN).mergeStyle(TextFormatting.RED));
        swordItemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        pickaxeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        axeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        shovelItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SHOVELS);
        hoeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.HOES);
        sword("forestcraft:molten_sword",3, -2.4f).
        pickaxe("forestcraft:molten_pickaxe",1, -2.8f).
        axe("forestcraft:molten_axe",5, -3f).
        shovel("forestcraft:molten_shovel",1.5f, -3f).
        hoe("forestcraft:molten_hoe",-3, -1f);
    }


    @Override
    public void onHit(AItem item, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setFire(200);
        if (!target.world.isRemote) {
            for (int i = 0;i<2;i++) {
                ((ServerWorld)target.world).spawnParticle(ParticleTypes.LAVA, target.getPosXRandom(0.25f), target.getPosYRandom(), target.getPosZRandom(0.25f), 0, 0, 0,0,0.1);
            }
        }
    }
}
