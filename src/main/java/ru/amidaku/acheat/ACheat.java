package ru.amidaku.acheat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ru.amidaku.acheat.Commands.Commands;

import java.util.Objects;

public final class ACheat extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "aCheat succsesful start");
        Objects.requireNonNull(getCommand("acheat")).setExecutor(new Commands(this));
    }
}
