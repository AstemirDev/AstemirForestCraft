package org.astemir.forestcraft.common.tileentity;


import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.network.AncientMonumentMessage;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.registries.FCLootTables;
import org.astemir.forestcraft.registries.FCTileEntities;


public class TileEntityAncientMonument extends TileEntity implements ITickableTileEntity {

    private long ticks = 0;
    private int hoverStart = 0;
    public ItemStack displayItem = ItemStack.EMPTY;


    public TileEntityAncientMonument() {
        super(FCTileEntities.ANCIENT_MONUMENT);
    }


    public void setItem(ItemStack itemStackIn) {
        displayItem = itemStackIn;
        updateBlock();
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        read(this.getBlockState(), packet.getNbtCompound());
        updateBlock();
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        if (nbt.contains("DisplayItem",10)) {
            this.setItem(ItemStack.read(nbt.getCompound("DisplayItem")));
        }
        if (nbt.contains("RandomLoot")){
            if (nbt.getBoolean("RandomLoot")){
                setItem(FCLootTables.ANCIENT_MONUMENT.drop());
            }
        }
    }




    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!getDisplayItem().isEmpty()){
            compound.put("DisplayItem",this.getDisplayItem().write(new CompoundNBT()));
        }
        compound.putBoolean("RandomLoot",false);
        return compound;
    }


    public void dropItem() {
        WorldUtils.dropItem(world,getPos(),displayItem);
        setItem(ItemStack.EMPTY);
    }

    public void updateBlock(){
        this.markDirty();
        sendMSGToAll(new AncientMonumentMessage(getPos().toLong(),displayItem));
    }

    public static <MSG> void sendMSGToAll(MSG message) {
        if (message == null){
            return;
        }
        if (ServerLifecycleHooks.getCurrentServer() != null) {
            if (ServerLifecycleHooks.getCurrentServer().getPlayerList() != null) {
                if (ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers() != null) {
                    for (ServerPlayerEntity player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                        ForestCraft.PACKET_HANDLER.getNetwork().sendTo(message, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                    }
                }
            }
        }
    }


    public int getHoverStart() {
        return hoverStart;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    public ItemStack getDisplayItem() {
        return this.displayItem;
    }

    @Override
    public void tick() {
        ticks++;
        if (ticks % 20 == 0){
            if (world.getBlockState(getPos().up()).isSolid()){
                dropItem();
            }
        }
    }


    public long getTicks() {
        return ticks;
    }
}
