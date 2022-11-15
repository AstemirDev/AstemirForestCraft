package org.astemir.api.common.bossbar;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.network.PacketDistributor;
import org.astemir.api.network.BossbarSyncMessage;
import org.astemir.api.client.AColor;
import org.astemir.api.math.MathUtils;
import org.astemir.forestcraft.ForestCraft;

import java.util.concurrent.CopyOnWriteArrayList;

public class ABossbar {


    private LivingEntity boss;
    private AColor color = AColor.GREEN;
    private String bossName = "";
    private Style style = Style.DEFAULT;
    private float value = 0;
    private float previousValue = 0;
    private int bossId;

    private CopyOnWriteArrayList<ServerPlayerEntity> trackingPlayers = new CopyOnWriteArrayList<>();

    public ABossbar(String bossName) {
        this.bossName = bossName;
    }


    public ABossbar() {
    }


    public String bossName() {
        return bossName;
    }

    public ABossbar bossName(String bossName) {
        this.bossName = bossName;
        return this;
    }

    public LivingEntity boss() {
        return boss;
    }

    public ABossbar boss(LivingEntity boss) {
        this.boss = boss;
        return this;
    }

    public AColor color() {
        return color;
    }

    public ABossbar color(AColor color) {
        this.color = color;
        return this;
    }


    public boolean isSameBossbar(ABossbar bossbar){
        if (getBossId() == bossbar.getBossId()){
            return true;
        }
        return false;
    }

    public ABossbar value(float value) {
        this.value = value;
        return this;
    }

    public ABossbar style(Style style) {
        this.style = style;
        return this;
    }

    public ABossbar previousValue(float previousValue) {
        this.previousValue = previousValue;
        return this;
    }

    public float value() {
        return value;
    }

    public Style style() {
        return style;
    }

    public float previousValue() {
        return previousValue;
    }

    public void show(ServerPlayerEntity target) {
        ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() ->  target),new BossbarSyncMessage(target.getEntityId(),BossbarSyncMessage.ADD,this));
        trackingPlayers.add(target);
        update();
    }

    public void hide(ServerPlayerEntity target){
        ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() ->  target),new BossbarSyncMessage(target.getEntityId(),BossbarSyncMessage.REMOVE,this));
        trackingPlayers.remove(target);
    }

    public void hideAll(){
        for (ServerPlayerEntity trackingPlayer : trackingPlayers) {
            hide(trackingPlayer);
        }
    }

    public CompoundNBT encode(){
        CompoundNBT bossbarNbt = new CompoundNBT();
        bossbarNbt.putInt("EntityId",boss.getEntityId());
        bossbarNbt.putFloat("Value",value());
        bossbarNbt.putFloat("PreviousValue",previousValue());
        bossbarNbt.putString("DisplayName",bossName());
        bossbarNbt.putIntArray("Color",color.toArray());
        bossbarNbt.putInt("Style",style.id);
        return bossbarNbt;
    }

    public ABossbar decode(CompoundNBT nbt){
        bossId(nbt.getInt("EntityId"));
        value(nbt.getFloat("Value"));
        previousValue(nbt.getFloat("PreviousValue"));
        bossName(nbt.getString("DisplayName"));
        color(AColor.fromArray(nbt.getIntArray("Color")));
        style(Style.fromId(nbt.getInt("Style")));
        return this;
    }

    public ABossbar update() {
        if (boss == null){
            hideAll();
        }
        this.previousValue = value;
        this.value = MathUtils.interpolate(value,boss.getHealth() / boss.getMaxHealth(),0.1f);
        for (ServerPlayerEntity trackingPlayer : trackingPlayers) {
            if (trackingPlayer.getShouldBeDead()){
                hide(trackingPlayer);
            }
            ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() -> trackingPlayer),new BossbarSyncMessage(trackingPlayer.getEntityId(),BossbarSyncMessage.UPDATE,this));
        }
        return this;
    }

    public int getBossId() {
        return bossId;
    }

    public ABossbar bossId(int id) {
        this.bossId = id;
        return this;
    }

    public enum Style{

        DEFAULT(0),SEGMENTED(1),FLAT(2);

        private int id;

        Style(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Style fromId(int id){
            switch (id){
                case 0: return DEFAULT;
                case 1: return SEGMENTED;
                case 2: return FLAT;
            }
            return DEFAULT;
        }
    }
}
