package ru.amidaku.acheat;

import org.bukkit.plugin.java.JavaPlugin;
import ru.amidaku.acheat.Commands.Commands;

public final class ACheat extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("acheat").setExecutor(new Commands(this));
        getServer().getConsoleSender().sendMessage("amidaku start that");
    }
    @Override
    public void onDisable() {
    }
}
