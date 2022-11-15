package org.astemir.forestcraft.registries;

import net.minecraft.advancements.CriteriaTriggers;
import org.astemir.forestcraft.common.triggers.ArmorEquipTrigger;

public class FCTriggers {

    public static final ArmorEquipTrigger ARMOR_EQUIPPED = CriteriaTriggers.register(new ArmorEquipTrigger());

}
