package xyz.bobkinn_.clickharvest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CropDrops {
    private final List<CropDrop> drops;
    public CropDrops(CropDrop... drops){
        this.drops = Arrays.stream(drops).collect(Collectors.toList());
    }

    public List<CropDrop> getRandoms(){
        return drops.stream().filter((cropDrop -> new Random().nextInt(0, 101) <= cropDrop.getChance()))
                .collect(Collectors.toList());
    }
}
