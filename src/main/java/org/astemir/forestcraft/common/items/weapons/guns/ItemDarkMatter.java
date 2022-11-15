package org.astemir.forestcraft.common.items.weapons.guns;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;
import org.astemir.api.common.item.IAnimatedItem;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.FCItemModels;
import org.astemir.forestcraft.client.ScreenShaker;
import org.astemir.forestcraft.common.entities.projectiles.bullets.FCBulletEntity;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.items.constructors.FCMiniGun;
import org.astemir.forestcraft.registries.properties.FCGuns;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.forestcraft.network.MinigunSoundMessage;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntityBlackholeBullet;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCItems;
import java.util.Arrays;
import java.util.List;

public class ItemDarkMatter extends FCMiniGun implements IAnimatedItem {

    public ItemDarkMatter() {
        super("forestcraft:dark_matter", FCGuns.DARK_MATTER);
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.GUNS);
        maxDamage(20000);
        rarity(FCRarity.LEGENDARY);
        setupISTER(ForestCraft.PROXY::setupISTER);
    }

    @Override
    public FCBulletEntity createProjectile(LivingEntity entity, World world) {
        FCBulletEntity bullet = new EntityBlackholeBullet(entity,world).initialize(entity, FCItems.BLACKHOLE_BULLET);
        return bullet;
    }


    @Override
    public void startUsing(World worldIn, PlayerEntity playerIn) {
        SoundUtils.playSound(worldIn, FCSounds.MINIGUN_START.get(), SoundCategory.PLAYERS,playerIn.getPosition(),1,1);
    }


    @Override
    public void afterShot(World worldIn, LivingEntity livingEntityIn, ItemStack stack) {
        super.afterShot(worldIn,livingEntityIn,stack);
        ScreenShaker.shakeScreen((PlayerEntity) livingEntityIn,10,4);
        for (PlayerEntity playerEntity : worldIn.getEntitiesWithinAABB(PlayerEntity.class, livingEntityIn.getBoundingBox().grow(3))) {
            if (playerEntity instanceof ServerPlayerEntity) {
                ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerEntity), new MinigunSoundMessage(livingEntityIn.getEntityId()));
            }
        }
    }

    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        SoundUtils.playSound(worldIn, FCSounds.MINIGUN_STOP.get(), SoundCategory.PLAYERS,entityLiving.getPosition(),1,1);
    }

    @Override
    public List<Enchantment> getAppliableEnchantments() {
        return Arrays.asList(Enchantments.MENDING,Enchantments.UNBREAKING);
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }


    @Override
    public int getModelIndex() {
        return FCItemModels.DARK_MATTER;
    }
}
