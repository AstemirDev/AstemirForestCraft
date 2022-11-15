package org.astemir.forestcraft.common.event;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.forestcraft.configuration.ConfigUtils;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCPotions;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCItems;

import java.util.List;

public class VillagerEvents {

    @SubscribeEvent
    public static void addWanderingVillagerTrades(WandererTradesEvent e){
        if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.VILLAGER_NEW_TRADES)) {
            List<VillagerTrades.ITrade> tierCommon = e.getGenericTrades();
            List<VillagerTrades.ITrade> tierRare = e.getRareTrades();
            tierCommon.add(trade(12, FCItems.TRAVELLER,1,8));
            tierCommon.add(trade(18, FCItems.RAIN_FLUTE,1,8));
            tierRare.add(trade(64, FCItems.PRISMATIC_DIAMOND,1,16));
        }
    }

    @SubscribeEvent
    public static void addVillagerTrades(VillagerTradesEvent e){
        if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.VILLAGER_NEW_TRADES)) {
            List<VillagerTrades.ITrade> tierOne = e.getTrades().get(1);
            List<VillagerTrades.ITrade> tierTwo = e.getTrades().get(2);
            List<VillagerTrades.ITrade> tierThree = e.getTrades().get(3);
            List<VillagerTrades.ITrade> tierFour = e.getTrades().get(4);
            List<VillagerTrades.ITrade> tierFive = e.getTrades().get(5);
            if (e.getType().equals(VillagerProfession.FARMER)) {
                tierOne.add(trade(4, FCItems.WEIRD_CUCUMBER,1,8,2,2));
                tierOne.add(trade(4, FCItems.POMIDOR,1,8,2,2));
                tierOne.add(trade(FCItems.POMIDOR,8,1,8,2,2));
                tierOne.add(trade(FCItems.WEIRD_CUCUMBER,8,1,8,2,2));
            }
            if (e.getType().equals(VillagerProfession.FISHERMAN)) {
                tierOne.add(trade(1, FCItems.FISHING_HOOK,1,8,2));
                tierOne.add(trade(2, FCItems.WORM,4,10,2));
                tierTwo.add(trade(FCItems.GOLDENFISH,5,5,4,2));
                tierTwo.add(trade(FCItems.FISHROOM,2,5,4,2));
                tierTwo.add(trade(FCItems.ICICLE,2,5,4,2));
                tierTwo.add(trade(FCItems.CRIMSON_FISH,3,5,6,2));
                tierTwo.add(trade(FCItems.WARPED_FISH,3,5,6,2));
                tierTwo.add(trade(FCItems.CACTUSH,2,5,4,2));
                tierTwo.add(trade(FCItems.FLESHER,2,5,4,2));
                tierTwo.add(trade(FCItems.FRESHWATER_PHANTOM,3,5,6,2));
                tierTwo.add(trade(FCItems.SQUID,2,5,4,2));
                tierTwo.add(trade(FCItems.BOOM_FISH,2,5,5,2));
                tierThree.add(trade(5, FCItems.GEMINNOW,5,5,2));
                tierFour.add(tradePotion(8, FCPotions.SEA_VISION.get(),1,8));
                tierFive.add(tradePotion(12, FCPotions.SEA_VISION_LONG.get(),1,12));
            }
        }
    }

    private static BasicTrade trade(Item item,int count,int emeralds,int max,int xp,float multiplier){
        return new BasicTrade(ItemUtils.count(item,count),ItemUtils.count(Items.EMERALD,emeralds),max,xp,multiplier);
    }

    private static BasicTrade trade(Item item,int emeralds,int max,int xp,float multiplier){
        return new BasicTrade(item.getDefaultInstance(),ItemUtils.count(Items.EMERALD,emeralds),max,xp,multiplier);
    }

    private static BasicTrade tradePotion(int emeralds, Potion potion, int max, int xp){
        return new BasicTrade(emeralds, ItemUtils.getPotionItemStack(potion),max,xp);
    }

    private static BasicTrade trade(int emeralds, Item item,int max,int xp){
        return new BasicTrade(emeralds,item.getDefaultInstance(),max,xp);
    }

    private static BasicTrade trade(int emeralds, Item item,int count,int max,int xp){
        return new BasicTrade(emeralds,ItemUtils.count(item,count),max,xp);
    }

    private static BasicTrade trade(int emeralds, Item item,int count,int max,int xp,float multiplier){
        return new BasicTrade(emeralds,ItemUtils.count(item,count),max,xp,multiplier);
    }

    private static BasicTrade trade(int emeralds, Item item,int max,int xp,float multiplier){
        return new BasicTrade(emeralds,item.getDefaultInstance(),max,xp,multiplier);
    }
}
