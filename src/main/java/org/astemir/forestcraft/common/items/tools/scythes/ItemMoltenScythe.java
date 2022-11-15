package org.astemir.forestcraft.common.items.tools.scythes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.common.items.constructors.FCScytheItem;
import org.astemir.forestcraft.registries.FCSounds;


public class ItemMoltenScythe extends FCScytheItem {


    public ItemMoltenScythe() {
        super("forestcraft:molten_scythe",FCItemTier.MOLTEN, 4, -2.8f,3);
        sound(FCSounds.SCYTHE_WHOOSH);
        getDefaultLore().add(new TranslationTextComponent(FCStrings.MOLTEN).mergeStyle(TextFormatting.RED));
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setFire(5);
        for (int i = 0;i<2;i++) {
            target.world.addParticle(ParticleTypes.LAVA, target.getPosXRandom(0.25f), target.getPosYRandom(), target.getPosZRandom(0.25f), 0.1f, 0.1f, 0.1f);
        }
        return super.onHit(stack, target, attacker);
    }
}
