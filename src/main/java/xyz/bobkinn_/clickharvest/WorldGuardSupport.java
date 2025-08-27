package xyz.bobkinn_.clickharvest;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldGuardSupport {

    public static boolean canInteract(Player player, Block block, String flag) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        WorldGuard wg = WorldGuard.getInstance();
        boolean canBypass = wg.getPlatform().getSessionManager().hasBypass(localPlayer, localPlayer.getWorld());
        if (canBypass) return true;

        Flag<?> f = Flags.fuzzyMatchFlag(wg.getFlagRegistry(), flag);
        if (!(f instanceof StateFlag)) {
            throw new IllegalArgumentException("Unknown flag "+flag);
        }

        Location loc = BukkitAdapter.adapt(block.getLocation());
        RegionContainer container = wg.getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        return query.testState(loc, localPlayer, (StateFlag) f);
    }
}
