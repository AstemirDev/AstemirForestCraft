package org.astemir.forestcraft.common.items.misc;


import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import org.astemir.api.common.item.AMobBucketItem;
import org.astemir.forestcraft.registries.FCItemGroups;


public class ItemSquidBucket extends AMobBucketItem {


    public ItemSquidBucket() {
        super("forestcraft:squid_bucket",()->EntityType.SQUID, ()->Fluids.WATER);
        maxStack(1);
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
    }

}

