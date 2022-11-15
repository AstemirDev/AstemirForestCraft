package org.astemir.forestcraft.registries;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.effect.*;

public class FCEffects {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, ForestCraft.MOD_ID);
    public static final RegistryObject<Effect> BROKEN_ARMOR = EFFECTS.register("broken_armor", BrokenArmorEffect::new);
    public static final RegistryObject<Effect> ELECTROCUT = EFFECTS.register("electrocut", ElectrocutEffect::new);
    public static final RegistryObject<Effect> ELECTRO_RESISTANCE = EFFECTS.register("electro_resistance", ElectroResistanceEffect::new);
    public static final RegistryObject<Effect> FEAR = EFFECTS.register("fear", FearEffect::new);
    public static final RegistryObject<Effect> SEA_VISION = EFFECTS.register("sea_vision", SeaVisionEffect::new);
    public static final RegistryObject<Effect> THORNS = EFFECTS.register("thorns",ThornsEffect::new);
    public static final RegistryObject<Effect> SLEEPING = EFFECTS.register("sleeping",SleepingEffect::new);
    public static final RegistryObject<Effect> SNEEZING = EFFECTS.register("sneezing",SneezingEffect::new);



}
