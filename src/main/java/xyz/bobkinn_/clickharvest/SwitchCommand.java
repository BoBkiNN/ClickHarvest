package xyz.bobkinn_.clickharvest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SwitchCommand implements CommandExecutor {
    public static List<String> disabled = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (disabled.contains(sender.getName())){
            disabled.remove(sender.getName());
        } else {
            disabled.add(sender.getName());
        }
        return false;
    }

    public static void save(@NotNull File folder){
        if (folder.mkdirs()){
            File save = new File(folder, "save.ser");
            try {
                ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(save.toPath()));
                oos.writeObject(disabled);
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void load(File folder){
        File save = new File(folder, "save.ser");
        if (!save.isFile()){
            disabled = new ArrayList<>();
            return;
        }
        try {
            ObjectInputStream oos = new ObjectInputStream(Files.newInputStream(save.toPath()));
            //noinspection unchecked
            disabled = (List<String>) oos.readObject();
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
