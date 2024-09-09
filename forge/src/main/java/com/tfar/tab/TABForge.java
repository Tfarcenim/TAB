package com.tfar.tab;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TAB.MOD_ID)
public class TABForge {
    
    public TABForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER,TomlConfig.SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        TAB.init();
        
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
