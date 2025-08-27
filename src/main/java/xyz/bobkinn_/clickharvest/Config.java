package xyz.bobkinn_.clickharvest;

import lombok.Value;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

@Value
public class Config {
    WorldGuard worldguard;
    Worlds worlds;

    public static Config deserialize(ConfigurationSection section) {
        if (section == null) {
            return new Config(
                    new WorldGuard(false, "interact"),
                    new Worlds(Worlds.Mode.BLACKLIST, new ArrayList<>())
            );
        }
        WorldGuard wg = WorldGuard.deserialize(section.getConfigurationSection("worldguard"));
        Worlds worlds = Worlds.deserialize(section.getConfigurationSection("worlds"));
        return new Config(wg, worlds);
    }

    @Value
    public static class WorldGuard {
        boolean enabled;
        String flag;

        public static WorldGuard deserialize(ConfigurationSection section) {
            if (section == null) {
                return new WorldGuard(false, "interact");
            }
            boolean enable = section.getBoolean("enabled", false);
            String flag = section.getString("flag", "interact");
            return new WorldGuard(enable, flag);
        }
    }

    @Value
    public static class Worlds {
        Mode mode;
        List<String> list;

        public static Worlds deserialize(ConfigurationSection section) {
            if (section == null) {
                return new Worlds(Mode.BLACKLIST, new ArrayList<>());
            }
            Mode mode;
            try {
                mode = Mode.valueOf(section.getString("mode", "blacklist").toUpperCase());
            } catch (IllegalArgumentException e) {
                mode = Mode.BLACKLIST; // fallback
            }
            List<String> list = section.getStringList("list");
            return new Worlds(mode, list);
        }

        public enum Mode {
            BLACKLIST,
            WHITELIST
        }
    }
}
