package ru.amidaku.acheat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.amidaku.acheat.Commands.Commands;
import ru.amidaku.acheat.Listeners.Listeners;

import java.awt.*;
import java.util.Objects;

public final class ACheat extends JavaPlugin {


    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "| aCheat has been started");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "| for help vk.com/amidaku");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|");
        Objects.requireNonNull(getCommand("acheat")).setExecutor(new Commands(this));
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }
}
