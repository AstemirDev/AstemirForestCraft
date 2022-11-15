package org.astemir.forestcraft.client.container;


import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.astemir.forestcraft.ForestCraft;


public class FCContainers {


    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ForestCraft.MOD_ID);

    public static final RegistryObject<ContainerType<ContainerAlchemicalBag>> ALCHEMICAL_BAG_CONTAINER = CONTAINERS.register("alchemical_bag",()-> IForgeContainerType.create(ContainerAlchemicalBag::createContainerClientSide));



}
