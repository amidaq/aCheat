package ru.amidaku.acheat;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.amidaku.acheat.Commands.Commands;
import ru.amidaku.acheat.Listeners.EffectListener;
import ru.amidaku.acheat.Listeners.Listeners;

import java.awt.*;
import java.util.Objects;

public final class ACheat extends JavaPlugin {


    public boolean isCheat(Player player) {
        return player.hasPermission("acheat.cheater");
    }

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(Color.GREEN + "|");
        getServer().getConsoleSender().sendMessage(Color.GREEN + "| aCheat has been started");
        getServer().getConsoleSender().sendMessage(Color.GREEN + "| for help vk.com/amidaku");
        getServer().getConsoleSender().sendMessage(Color.GREEN + "|");
        Objects.requireNonNull(getCommand("acheat")).setExecutor(new Commands(this));
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginManager().registerEvents(new EffectListener(), this);
    }
}
