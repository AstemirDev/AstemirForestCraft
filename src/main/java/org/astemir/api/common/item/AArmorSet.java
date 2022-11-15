package org.astemir.api.common.item;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AArmorSet {

    private AItemArmor helmet;
    private AItemArmor chestplate;
    private AItemArmor leggings;
    private AItemArmor boots;
    private IArmorMaterial tier;
    private List<ITextComponent> lore = new ArrayList<>();

    private ItemGroup group = ItemGroup.COMBAT;
    private int priority = 0;

    private int mainModel = -1;
    private int subModel = -1;

    private String mainTexture;
    private String subTexture;



    public AArmorSet(IArmorMaterial tier) {
        this.tier = tier;
    }

    public Item[] register(){
        Item[] items = new Item[4];
        if (helmet != null){
            items[0] = helmet.register();
        }
        if (chestplate != null){
            items[1] = chestplate.register();
        }
        if (leggings != null){
            items[2] = leggings.register();
        }
        if (boots != null){
            items[3] = boots.register();
        }
        return items;
    }

    public int mainModel() {
        return mainModel;
    }

    public AArmorSet mainModel(int mainModel) {
        this.mainModel = mainModel;
        return this;
    }

    public int subModel() {
        return subModel;
    }

    public AArmorSet subModel(int subModel) {
        this.subModel = subModel;
        return this;
    }

    public String mainTexture() {
        return mainTexture;
    }

    public AArmorSet mainTexture(String mainTexture) {
        this.mainTexture = mainTexture;
        return this;
    }

    public String subTexture() {
        return subTexture;
    }

    public AArmorSet subTexture(String subTexture) {
        this.subTexture = subTexture;
        return this;
    }

    public void onTickWhileArmorEquipped(AItem item,ItemStack itemStack, PlayerEntity entity) {}

    public AArmorSet helmet(String name, float damageReduction, float toughness, float knockbackResistance) {
        this.helmet = new AItemArmor(name,tier, EquipmentSlotType.HEAD,damageReduction,toughness,knockbackResistance){
            @Override
            public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
                onTickWhileArmorEquipped(this,itemStack,entity);
            }
        };
        this.helmet.itemGroup(group,priority);
        this.helmet.armorTexture = mainTexture;
        if (mainModel != -1) {
            this.helmet.armorModel = mainModel;
        }
        this.helmet.lore(lore);
        return this;
    }

    public AArmorSet chestplate(String name, float damageReduction, float toughness,float knockbackResistance) {
        this.chestplate = new AItemArmor(name,tier, EquipmentSlotType.CHEST,damageReduction,toughness,knockbackResistance){
            @Override
            public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
                onTickWhileArmorEquipped(this,itemStack,entity);
            }
        };
        this.chestplate.itemGroup(group,priority);
        this.chestplate.armorTexture = mainTexture;
        if (mainModel != -1) {
            this.chestplate.armorModel = mainModel;
        }
        this.chestplate.lore(lore);
        return this;
    }

    public AArmorSet leggings(String name, float damageReduction, float toughness,float knockbackResistance) {
        this.leggings = new AItemArmor(name,tier, EquipmentSlotType.LEGS,damageReduction,toughness,knockbackResistance){
            @Override
            public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
                onTickWhileArmorEquipped(this,itemStack,entity);
            }
        };
        this.leggings.itemGroup(group,priority);
        this.leggings.armorTexture = subTexture;
        if (subModel != -1) {
            this.leggings.armorModel = subModel;
        }
        this.leggings.lore(lore);
        return this;
    }

    public AArmorSet boots(String name, float damageReduction, float toughness,float knockbackResistance) {
        this.boots = new AItemArmor(name,tier, EquipmentSlotType.FEET,damageReduction,toughness,knockbackResistance){
            @Override
            public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
                onTickWhileArmorEquipped(this,itemStack,entity);
            }
        };
        this.boots.itemGroup(group,priority);
        this.boots.armorTexture = mainTexture;
        if (mainModel != -1) {
            this.boots.armorModel = mainModel;
        }
        this.boots.lore(lore);
        return this;
    }


    public AArmorSet helmet(AItemArmor helmet) {
        this.helmet = helmet;
        return this;
    }

    public AArmorSet chestplate(AItemArmor chestplate) {
        this.chestplate = chestplate;
        return this;
    }

    public AArmorSet leggings(AItemArmor leggings) {
        this.leggings = leggings;
        return this;
    }

    public AArmorSet boots(AItemArmor boots) {
        this.boots = boots;
        return this;
    }

    public AArmorSet groupPriority(ItemGroup group,int priority) {
        this.group = group;
        this.priority = priority;
        return this;
    }

    public AArmorSet lore(ITextComponent... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    public AItemArmor getHelmet() {
        return helmet;
    }

    public AItemArmor getChestplate() {
        return chestplate;
    }

    public AItemArmor getLeggings() {
        return leggings;
    }

    public AItemArmor getBoots() {
        return boots;
    }
}
