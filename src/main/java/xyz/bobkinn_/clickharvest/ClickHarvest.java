package xyz.bobkinn_.clickharvest;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class ClickHarvest extends JavaPlugin implements Listener {

    public final Map<Material, CropDrops> cropDrops = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = e.getClickedBlock();
        if (block == null) return;
        if (!cropDrops.containsKey(block.getType())) return;
        Player player = e.getPlayer();
        if (player.isSneaking()) return;
        Ageable a = (Ageable) block.getBlockData();
        if (a.getAge() != a.getMaximumAge()) return;
        CropDrops drops = cropDrops.get(block.getType());
        if (drops == null) return;
        a.setAge(0);
        block.setBlockData(a);
        player.swingMainHand();
        for (CropDrop dr : drops.getRandoms()) block.getWorld().dropItemNaturally(block.getLocation(), dr.getRandom());
        player.playSound(player.getLocation(), Sound.ITEM_CROP_PLANT, 1.0f, 1.0f);
        e.setUseItemInHand(Event.Result.DENY);
    }

    @Override
    public void onEnable() {
        cropDrops.put(Material.WHEAT, new CropDrops(new CropDrop(1, 1, Material.WHEAT, 100),
                new CropDrop(1, 3, Material.WHEAT_SEEDS, 100)));
        cropDrops.put(Material.CARROTS, new CropDrops(new CropDrop(1, 4, Material.CARROT, 100)));
        cropDrops.put(Material.BEETROOTS, new CropDrops(new CropDrop(1, 1, Material.BEETROOT, 100),
                new CropDrop(1, 3, Material.BEETROOT_SEEDS, 100)));
        cropDrops.put(Material.POTATOES, new CropDrops(new CropDrop(2, 4, Material.POTATO, 100),
                new CropDrop(1, 1, Material.POISONOUS_POTATO, 2)));
        cropDrops.put(Material.COCOA,new CropDrops( new CropDrop(2, 2, Material.COCOA_BEANS, 100)));
        getServer().getPluginManager().registerEvents(this, this);
    }
}
