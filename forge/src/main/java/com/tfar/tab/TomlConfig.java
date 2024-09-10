package com.tfar.tab;

import com.tfar.tab.platform.MLConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class TomlConfig implements MLConfig {

    public static final Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<Server, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }


    public static class Server {
        public static ForgeConfigSpec.BooleanValue enable_belowname;
        public static ForgeConfigSpec.ConfigValue<String> belowname_number;
        public static ForgeConfigSpec.ConfigValue<String> belowname_text;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push(TABProperties.BELOWNAME);
            enable_belowname = builder.define("enable",true);
            belowname_number = builder.define("number","%health%");
            belowname_text = builder.define("text","&cHealth");
            builder.pop();
        }
    }

    @Override
    public boolean belownameEnabled() {
        return Server.enable_belowname.get();
    }

    static void configLoad(ModConfigEvent e) {

    }
}
