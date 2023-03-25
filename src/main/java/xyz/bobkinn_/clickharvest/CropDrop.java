package xyz.bobkinn_.clickharvest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CropDrop {
    private final int min;
    private final int max;
    private final Material material;
    private final int chance;

    public CropDrop(int min, int max, Material material, int dropChance){
        this.min = min;
        this.max = max;
        this.material = material;
        this.chance = dropChance;
    }

    public int getMax() {
        return max;
    }

    public int getChance() {
        return chance;
    }

    public int getMin() {
        return min;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getRandom(){
        int n = new Random().nextInt(getMin(), getMax()+1);
        return new ItemStack(getMaterial(), n);
    }
}
