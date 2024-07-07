package ru.amidaku.acheat.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import ru.amidaku.acheat.ACheat;


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
            if (player == null){
                sender.sendMessage(Color.RED + "Он оффлайн");
                return true;
            }
            if (!(player.equals(sender))) {
                if (!(player.hasPermission("*"))) {
                    player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100, 1));
                    Bukkit.getScheduler().runTaskLaterAsynchronously(main, () -> {
                        for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
                            double radius = Math.sin(i) * 3;
                            double y = Math.cos(i) * 3 + 1;
                            for (double a = 0; a < Math.PI * 2; a += Math.PI / 10) {
                                double x = Math.cos(a) * radius;
                                double z = Math.sin(a) * radius;
                                Location loc = player.getLocation();
                                loc.add(x, y, z);
                                loc.getWorld().spawnParticle(Particle.DOLPHIN, loc, 5);
                                loc.subtract(x, y, z);
                            }
                        }
                    }, 90L);
                    Bukkit.getScheduler().runTaskLaterAsynchronously(main, () -> {
                        player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 200);
                        player.setHealth(0.0);
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 10);
                        Bukkit.dispatchCommand(sender, commande + player.getName() + send + sender.getName() + time + reason);
                        Bukkit.getScheduler().cancelTasks(main);
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

