package ru.amidaku.acheat.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import ru.amidaku.acheat.ACheat;
import ru.amidaku.acheat.Effects.EffectManager;


public class Commands implements CommandExecutor {

    private ACheat main;

    public Commands(ACheat main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            final Player player = Bukkit.getPlayer(args[0]);
            if (player == null){
                sender.sendMessage(Color.RED + "Он оффлайн");
                return true;
            }
            if (!(player.hasPermission("acheat.admin"))){
                sender.sendMessage(ChatColor.RED + "Ты хочешь " + ChatColor.GREEN + player.getName() + ChatColor.RED + " вызвать?");
                return true;
            }
            if (player.equals(sender)){
                sender.sendMessage(ChatColor.RED + "Нельзя так с собой");
                return true;
            }
            player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100, 1));
            EffectManager.sphereEffect(player);
            EffectManager.coneEffect(player);
            Bukkit.getScheduler().runTaskLater(main, () -> {
                player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 200);
                player.setHealth(0.0);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 10);
                Bukkit.dispatchCommand(sender, String.format("ipban %s 30d 2.5 —sender=%s", player.getName(), sender.getName()));
                Bukkit.getScheduler().cancelTasks(main);
                }, 100L);
            sender.sendMessage(String.format(ChatColor.GREEN + "Игрок %s наказан", player.getName()));
        }return false;
    }
}


