package org.astemir.forestcraft.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCDataGenerators {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent e){
        DataGenerator dataGenerator = e.getGenerator();
        dataGenerator.addProvider(new FCRecipes(dataGenerator));
    }

}
