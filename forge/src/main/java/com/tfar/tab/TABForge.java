package com.tfar.tab;

import net.minecraftforge.fml.common.Mod;

@Mod(TAB.MOD_ID)
public class TABForge {
    
    public TABForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        TAB.init();
        
    }
}