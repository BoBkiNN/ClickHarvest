package xyz.bobkinn_.clickharvest;

import lombok.Data;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

@Data
public class CropDrop {
    private final int min;
    private final int max;
    private final Material material;
    private final int chance;

    public ItemStack getRandom(){
        int n = new Random().nextInt(getMax()+1) + getMin();
        return new ItemStack(getMaterial(), n);
    }
}
