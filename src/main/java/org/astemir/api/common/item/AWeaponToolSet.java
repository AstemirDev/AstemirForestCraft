package org.astemir.api.common.item;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.astemir.api.math.MapBuilder;
import org.astemir.api.math.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AWeaponToolSet {

    private AItemSword sword;
    private AItemPickaxe pickaxe;
    private AItemAxe axe;
    private AItemShovel shovel;
    private AItemHoe hoe;
    private IItemTier tier;
    private List<ITextComponent> lore = new ArrayList<>();

    private Pair<ItemGroup,Integer> swordGroup = new Pair(ItemGroup.COMBAT,1);
    private Pair<ItemGroup,Integer> pickaxeGroup = new Pair(ItemGroup.TOOLS,1);
    private Pair<ItemGroup,Integer> axeGroup = new Pair(ItemGroup.TOOLS,1);
    private Pair<ItemGroup,Integer> shovelGroup = new Pair(ItemGroup.TOOLS,1);
    private Pair<ItemGroup,Integer> hoeGroup = new Pair(ItemGroup.TOOLS,1);


    public AWeaponToolSet(IItemTier tier) {
        this.tier = tier;
    }

    public Item[] register(){
        Item[] items = new Item[5];
        if (sword != null){
            items[0] = sword.register();
        }
        if (pickaxe != null){
            items[1] = pickaxe.register();
        }
        if (axe != null){
            items[2] = axe.register();
        }
        if (shovel != null){
            items[3] = shovel.register();
        }
        if (hoe != null){
            items[4] = hoe.register();
        }
        return items;
    }

    public void onHit(AItem item,ItemStack stack, LivingEntity target, LivingEntity attacker) { }

    public void onRightClick(AItem item,World worldIn, PlayerEntity playerIn, Hand handIn) { }

    public void onLeftClick(AItem item,ItemStack stack, PlayerEntity player) { }

    public void onSwing(AItem item,ItemStack stack, LivingEntity entity) {}

    public AWeaponToolSet sword(String name,float damage,float speed) {
        this.sword = new AItemSword(name,tier,damage,speed){
            @Override
            public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
                AWeaponToolSet.this.onRightClick(this,worldIn,playerIn,handIn);
                return super.onRightClick(worldIn, playerIn, handIn);
            }

            @Override
            public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
                AWeaponToolSet.this.onLeftClick(this,stack,player);
                return super.onLeftClick(stack, player);
            }

            @Override
            public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                AWeaponToolSet.this.onHit(this,stack,target,attacker);
                return super.onHit(stack,target,attacker);
            }

            @Override
            public boolean onSwing(ItemStack stack, LivingEntity entity) {
                AWeaponToolSet.this.onSwing(this,stack,entity);
                return super.onSwing(stack, entity);
            }
        };
        this.sword.itemGroup(swordGroup.getKey(),swordGroup.getValue());
        this.sword.lore(lore);
        return this;
    }

    public AWeaponToolSet pickaxe(String name,float damage,float speed) {
        this.pickaxe = new AItemPickaxe(name,tier,damage,speed){
            @Override
            public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                AWeaponToolSet.this.onHit(this,stack,target,attacker);
                return super.onHit(stack, target, attacker);
            }
            @Override
            public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
                AWeaponToolSet.this.onRightClick(this,worldIn,playerIn,handIn);
                return super.onRightClick(worldIn, playerIn, handIn);
            }

            @Override
            public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
                AWeaponToolSet.this.onLeftClick(this,stack,player);
                return super.onLeftClick(stack, player);
            }

            @Override
            public boolean onSwing(ItemStack stack, LivingEntity entity) {
                AWeaponToolSet.this.onSwing(this,stack,entity);
                return super.onSwing(stack, entity);
            }
        };
        this.pickaxe.itemGroup(pickaxeGroup.getKey(),pickaxeGroup.getValue());
        this.pickaxe.lore(lore);
        return this;
    }

    public AWeaponToolSet axe(String name,float damage,float speed) {
        this.axe = new AItemAxe(name,tier,damage,speed){
            @Override
            public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                AWeaponToolSet.this.onHit(this,stack,target,attacker);
                return super.onHit(stack, target, attacker);
            }
            @Override
            public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
                AWeaponToolSet.this.onRightClick(this,worldIn,playerIn,handIn);
                return super.onRightClick(worldIn, playerIn, handIn);
            }

            @Override
            public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
                AWeaponToolSet.this.onLeftClick(this,stack,player);
                return super.onLeftClick(stack, player);
            }

            @Override
            public boolean onSwing(ItemStack stack, LivingEntity entity) {
                AWeaponToolSet.this.onSwing(this,stack,entity);
                return super.onSwing(stack, entity);
            }
        };
        this.axe.itemGroup(axeGroup.getKey(),axeGroup.getValue());
        this.axe.lore(lore);
        return this;
    }

    public AWeaponToolSet shovel(String name,float damage,float speed) {
        this.shovel = new AItemShovel(name,tier,damage,speed){
            @Override
            public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                AWeaponToolSet.this.onHit(this,stack,target,attacker);
                return super.onHit(stack, target, attacker);
            }
            @Override
            public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
                AWeaponToolSet.this.onRightClick(this,worldIn,playerIn,handIn);
                return super.onRightClick(worldIn, playerIn, handIn);
            }

            @Override
            public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
                AWeaponToolSet.this.onLeftClick(this,stack,player);
                return super.onLeftClick(stack, player);
            }

            @Override
            public boolean onSwing(ItemStack stack, LivingEntity entity) {
                AWeaponToolSet.this.onSwing(this,stack,entity);
                return super.onSwing(stack, entity);
            }
        };
        this.shovel.itemGroup(shovelGroup.getKey(),shovelGroup.getValue());
        this.shovel.lore(lore);
        return this;
    }

    public AWeaponToolSet hoe(String name,float damage,float speed) {
        this.hoe = new AItemHoe(name,tier,damage,speed){
            @Override
            public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                AWeaponToolSet.this.onHit(this,stack,target,attacker);
                return super.onHit(stack, target, attacker);
            }
            @Override
            public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
                AWeaponToolSet.this.onRightClick(this,worldIn,playerIn,handIn);
                return super.onRightClick(worldIn, playerIn, handIn);
            }

            @Override
            public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
                AWeaponToolSet.this.onLeftClick(this,stack,player);
                return super.onLeftClick(stack, player);
            }

            @Override
            public boolean onSwing(ItemStack stack, LivingEntity entity) {
                AWeaponToolSet.this.onSwing(this,stack,entity);
                return super.onSwing(stack, entity);
            }
        };
        this.hoe.itemGroup(hoeGroup.getKey(),hoeGroup.getValue());
        this.hoe.lore(lore);
        return this;
    }

    public AWeaponToolSet sword(AItemSword sword) {
        this.sword = sword;
        return this;
    }

    public AWeaponToolSet pickaxe(AItemPickaxe pickaxe) {
        this.pickaxe = pickaxe;
        return this;
    }

    public AWeaponToolSet axe(AItemAxe axe) {
        this.axe = axe;
        return this;
    }

    public AWeaponToolSet shovel(AItemShovel shovel) {
        this.shovel = shovel;
        return this;
    }

    public AWeaponToolSet hoe(AItemHoe hoe) {
        this.hoe = hoe;
        return this;
    }

    public AWeaponToolSet pickaxeItemGroup(ItemGroup group,int priority) {
        this.pickaxeGroup = new Pair<>(group,priority);
        return this;
    }

    public AWeaponToolSet axeItemGroup(ItemGroup group,int priority) {
        this.axeGroup = new Pair<>(group,priority);
        return this;
    }

    public AWeaponToolSet shovelItemGroup(ItemGroup group,int priority) {
        this.shovelGroup = new Pair<>(group,priority);
        return this;
    }

    public AWeaponToolSet hoeItemGroup(ItemGroup group,int priority) {
        this.hoeGroup = new Pair<>(group,priority);
        return this;
    }

    public AWeaponToolSet swordItemGroup(ItemGroup group,int priority) {
        this.swordGroup = new Pair<>(group,priority);
        return this;
    }

    public AWeaponToolSet lore(ITextComponent... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    public AItemSword getSword() {
        return sword;
    }

    public AItemPickaxe getPickaxe() {
        return pickaxe;
    }

    public AItemAxe getAxe() {
        return axe;
    }

    public AItemShovel getShovel() {
        return shovel;
    }

    public AItemHoe getHoe() {
        return hoe;
    }
}
