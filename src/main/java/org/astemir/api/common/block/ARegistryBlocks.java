package org.astemir.api.common.block;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.astemir.api.common.block.vanilla.ModBlock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ARegistryBlocks {

    public static Class BLOCKS_CONTAINER;

    public static List<Block> BLOCKS = new ArrayList<>();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Field f : BLOCKS_CONTAINER.getDeclaredFields()) {
            Object obj = null;
            try {
                obj = f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (obj instanceof Block) {
                if (((Block) obj).getRegistryName() != null) {
                    event.getRegistry().register((Block) obj);
                    if (obj instanceof IModBlock){
                        ((IModBlock)obj).blockConstructor().blockItemLink((Block)obj);
                    }
                    BLOCKS.add((Block)obj);
                }
            }else
            if (obj instanceof Block[]){
                for (Block block : (Block[]) obj){
                    if (block != null) {
                        if (block.getRegistryName() != null) {
                            event.getRegistry().register(block);
                            if (block instanceof IModBlock){
                                ((IModBlock)block).blockConstructor().blockItemLink(block);
                            }
                            BLOCKS.add(block);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Field f : BLOCKS_CONTAINER.getDeclaredFields()) {
            Object obj = null;
            try {
                obj = f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (obj instanceof Block) {
                if (((Block) obj).getRegistryName() != null) {
                    ABlock aBlock = (((IModBlock)obj)).blockConstructor();
                    if (aBlock.isNeedToRegisterItem()) {
                        if (aBlock.getBlockItem() != null) {
                            event.getRegistry().register(aBlock.getBlockItem());
                        }
                    }
                    BLOCKS.add((Block)obj);
                }
            }else
            if (obj instanceof Block[]){
                for (Block block : (Block[]) obj){
                    if (block != null) {
                        if (block.getRegistryName() != null) {
                            ABlock aBlock = (((IModBlock)obj)).blockConstructor();
                            if (aBlock.isNeedToRegisterItem()) {
                                if (aBlock.getBlockItem() != null) {
                                    event.getRegistry().register(aBlock.getBlockItem());
                                }
                            }
                            BLOCKS.add((Block)obj);
                        }
                    }
                }
            }
        }
    }


}
