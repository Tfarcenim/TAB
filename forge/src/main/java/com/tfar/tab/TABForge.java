package com.tfar.tab;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TAB.MOD_ID)
public class TABForge {

    public static final String OBJECTIVE_NAME = "TAB-BelowName";


    static ServerScoreboard scoreboard;
    
    public TABForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER,TomlConfig.SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        MinecraftForge.EVENT_BUS.addListener(this::displayName);
        MinecraftForge.EVENT_BUS.addListener(this::alwaysShowName);
        MinecraftForge.EVENT_BUS.addListener(this::serverStart);
        MinecraftForge.EVENT_BUS.addListener(this::playerJoin);
        // Use Forge to bootstrap the Common mod.
        TAB.init();
        
    }

    void serverStart(ServerStartedEvent event) {
        scoreboard = new ServerScoreboard(event.getServer());
        Objective objective = scoreboard.addObjective(OBJECTIVE_NAME, ObjectiveCriteria.HEALTH,Component.literal("health"), ObjectiveCriteria.RenderType.INTEGER);
        scoreboard.startTrackingObjective(objective);
    }

    void alwaysShowName(RenderNameTagEvent event) {
        event.setResult(Event.Result.ALLOW);
    }


    void displayName(PlayerEvent.NameFormat event) {
        Component username = event.getUsername();
    }

    void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        Objective objective = scoreboard.getObjective("test");
        player.connection.send(new ClientboundSetObjectivePacket(objective,ClientboundSetObjectivePacket.METHOD_ADD));
       player.connection.send(new ClientboundSetDisplayObjectivePacket(Scoreboard.DISPLAY_SLOT_BELOW_NAME,objective));
    }

}
//Below are core features included in TAB
//
//+ Belowname
//+ Header/Footer
//+ Nametags
//+ Playerlist Objective
//+ Scoreboard
//+ Sorting in tablist
//+ Tablist name formatting
