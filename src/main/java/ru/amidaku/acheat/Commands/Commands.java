package ru.amidaku.acheat.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import ru.amidaku.acheat.ACheat;

import static org.bukkit.FireworkEffect.Type.STAR;


public class Commands implements CommandExecutor {

    private final ACheat main;

    public Commands(ACheat main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if ((sender instanceof Player)) {
            if (args.length == 1) {
                final Player player = Bukkit.getPlayer(args[0]);
                if (player == null){
                    sender.sendMessage(ChatColor.RED + "Player is offline");
                    return true;
                }
                FireworkEffect effect = FireworkEffect.builder().withColor(Color.RED).withColor(Color.ORANGE).with(STAR).build();
                player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100,1));
                Bukkit.getScheduler().runTaskLater(main, () ->{
                    player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(),400);
                    player.setHealth(0.0);
                },100L);
            }
        } return false;
    }
    
}

