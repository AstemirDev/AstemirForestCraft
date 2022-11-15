package org.astemir.forestcraft.client.managers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.astemir.forestcraft.client.render.renderer.entity.animals.*;
import org.astemir.forestcraft.client.render.renderer.entity.monsters.*;
import org.astemir.forestcraft.client.render.renderer.entity.monsters.bosses.*;
import org.astemir.forestcraft.client.render.renderer.entity.projectiles.*;
import org.astemir.forestcraft.registries.FCEntities;

public class FCEntityRenderManager {

    public static void register(){
        registerEntityRenderers();
        registerProjectileRenderers();
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerEntityRenderers(){
        EntityRendererManager renderManager = Minecraft.getInstance().getRenderManager();
        registerRenderer(FCEntities.DEER, new RendererDeer(renderManager));
        registerRenderer(FCEntities.BEEQUEEN, new RendererBeeQueen(renderManager));
        registerRenderer(FCEntities.INSANE_DOG, new RendererInsaneDog(renderManager));
        registerRenderer(FCEntities.ALPHA_INSANE_DOG, new RendererAlphaInsaneDog(renderManager));
        registerRenderer(FCEntities.GEM_GOLEM, new RendererGemGolem(renderManager));
        registerRenderer(FCEntities.THUNDER_SCREAMER,new RendererThunderScreamer(renderManager));
        registerRenderer(FCEntities.TERROR_BRINGER,new RendererTerrorBringer(renderManager));
        registerRenderer(FCEntities.INFECTED_ZOMBIE,new RendererInfectedZombie(renderManager));
        registerRenderer(FCEntities.NIGHT_WATCHER,new RendererNightWatcher(renderManager));
        registerRenderer(FCEntities.CICADA,new RendererCicada(renderManager));
        registerRenderer(FCEntities.EATER_OF_THE_DEPTHS,new RendererEaterOfTheDepths(renderManager));
        registerRenderer(FCEntities.FIRE_FISHING_BOBBER,new RendererFireFishingBobber(renderManager));
        registerRenderer(FCEntities.KROCK,new RendererKrock(renderManager));
        registerRenderer(FCEntities.BAKUDAN,new RendererBakudan(renderManager));
        registerRenderer(FCEntities.BAKUDAN_BALL,new RendererBakudanBall(renderManager));
        registerRenderer(FCEntities.KILLER_BEE, new RendererKillerBee(renderManager));
        registerRenderer(FCEntities.PROJECTILE_BEE, new RendererKillerBee(renderManager));
        registerRenderer(FCEntities.IGUANA_KING, new RendererIguanaKing(renderManager));
        registerRenderer(FCEntities.SOUL_BLAZE, new RendererSoulBlaze(renderManager));
        registerRenderer(FCEntities.AIR_SUCKER, new RendererAirSucker(renderManager));
        registerRenderer(FCEntities.CROCUS_FLOWER, new RendererCrocusFlower(renderManager));
        registerRenderer(FCEntities.DANDELIONEER, new RendererDandelioneer(renderManager));
        registerRenderer(FCEntities.CROCUS_PETAL_PROJECTILE,new RendererCrocusPetal(renderManager));
        registerRenderer(FCEntities.PETRIFIED_ZOMBIE,new RendererPetrifiedZombie(renderManager));
        registerRenderer(FCEntities.ELECTRITE_ARROW,new RendererElectriteArrow(renderManager));
        registerRenderer(FCEntities.WOOD_ABOMINATION,new RendererWoodAbomination(renderManager));
        registerRenderer(FCEntities.ARCHAIC_SENTINEL,new RendererArchaicSentinel(renderManager));
        registerRenderer(FCEntities.KELPY,new RendererKelpy(renderManager));
        registerRenderer(FCEntities.NIGHT_BAT,new RendererNightBat(renderManager));
        registerRenderer(FCEntities.NETHER_BAT,new RendererNetherBat(renderManager));
        registerRenderer(FCEntities.COSMIC_FIEND,new RendererCosmicFiend(renderManager));
        registerRenderer(FCEntities.NETHER_SCOURGE,new RendererNetherScourge(renderManager));
        registerRenderer(FCEntities.COSMIC_FIRE,new RendererCosmicFire(renderManager));
        registerRenderer(FCEntities.BUBBLE_PROJECTILE,new RendererWaterBubble(renderManager));
        registerRenderer(FCEntities.DESECRATED_SOUL,new RendererDesecratedSoul(renderManager));
        registerRenderer(FCEntities.IGUANA,new RendererIguana(renderManager));
        registerRenderer(FCEntities.JUNGLE_WASP,new RendererJungleWasp(renderManager));
        registerRenderer(FCEntities.CLOUD_RAY,new RendererCloudRay(renderManager));
        registerRenderer(FCEntities.MINI_TORNADO_PROJECTILE,new RendererMiniTornado(renderManager));
        registerRenderer(FCEntities.DAYBREAK_PROJECTILE,new RendererAncientRune(renderManager));
        registerRenderer(FCEntities.CHANGELING,new RendererChangeling(renderManager));
        registerRenderer(FCEntities.WORM,new RendererWorm(renderManager));
        registerRenderer(FCEntities.SEA_DEVIL,new RendererSeaDevil(renderManager));
        registerRenderer(FCEntities.WATER_BUG,new RendererWaterBug(renderManager));
        registerRenderer(FCEntities.AKNAYAH,new RendererAknayah(renderManager));

    }

    @OnlyIn(Dist.CLIENT)
    public static void registerProjectileRenderers(){
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.DIRT_PROJECTILE,(a)->new SpriteRenderer(a, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.SKY_FRAGMENT_PROJECTILE,(a)->new SpriteRenderer(a, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.SKY_BULLET_PROJECTILE,(a)->new SpriteRenderer(a, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.MOLTEN_BULLET_PROJECTILE,(a)->new SpriteRenderer(a, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.DEMON_BULLET_PROJECTILE,(a)->new SpriteRenderer(a, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.BLACKHOLE_BULLET_PROJECTILE,(a)->new SpriteRenderer(a, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.NOTE_PROJECTILE,(a)->new SpriteRenderer(a,itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.FIRE_LIQUID_PROJECTILE,(a)->new SpriteRenderer(a, itemRenderer));
    }

    private static <T extends Entity> void registerRenderer(EntityType<T> entityClass, EntityRenderer<? super T> entityRendererIn){
        Minecraft.getInstance().getRenderManager().register(entityClass,entityRendererIn);
    }
}
