package ru.amidaku.acheat.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import ru.amidaku.acheat.ACheat;
import ru.amidaku.acheat.Listeners.EffectListener;


public class Commands implements CommandExecutor {

    private final ACheat main;

    private String commande = "ipban ";
    private final String send = " --sender=";

    private final String reason = " 2.5";

    private final String time = " 30d";



    public Commands(ACheat main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            final Player player = Bukkit.getPlayer(args[0]);
            main.cheat = true;
            if (player == null){
                sender.sendMessage(Color.RED + "Он оффлайн");
                return true;
            }
            if (!(player.equals(sender))) {
                if (!(player.hasPermission("*"))) {
                    player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100, 1));
                    Bukkit.getScheduler().runTaskTimer(main, () -> {
                        new EffectListener();
                    },0,20L);
                    Bukkit.getScheduler().runTaskLater(main, () -> {
                        player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 250);
                        player.setHealth(0.0);
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 10);
                        Bukkit.dispatchCommand(sender, commande + player.getName() + send + sender.getName() + time + reason);
                        Bukkit.getScheduler().cancelTasks(main);
                        main.cheat = false;
                    }, 100L);

                }
                if (player.hasPermission("*")) {
                    sender.sendMessage(ChatColor.RED + "Ты хочешь " + ChatColor.GREEN + player.getName() + ChatColor.RED + " вызвать?");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Нельзя так с собой");
            }

        } return false;
    }
}

