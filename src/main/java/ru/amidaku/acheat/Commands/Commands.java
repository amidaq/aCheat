package ru.amidaku.acheat.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import ru.amidaku.acheat.ACheat;



public class Commands implements CommandExecutor {

    private static ACheat main;

    public Commands(ACheat main) {
        Commands.main = main;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            final Player player = Bukkit.getPlayer(args[0]);
            if (player == null){
                sender.sendMessage(Color.RED + "Он оффлайн");
                return true;
            }
            if ((player.hasPermission("acheat.admin"))){
                sender.sendMessage(ChatColor.RED + "Ты хочешь " + ChatColor.GREEN + player.getName() + ChatColor.RED + " вызвать?");
                return true;
            }
            if (player.equals(sender)){
                sender.sendMessage(ChatColor.RED + "Нельзя так с собой");
                return true;
            }
            player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100, 1));
            sphereEffect(player);
            Bukkit.getScheduler().runTaskLater(main, () -> {
                player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 400);
                player.setHealth(0.0);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 10);
                Bukkit.dispatchCommand(sender, String.format("ipban %s 30d %s", player.getName(),main.getConfig().getString("reason")));
                sender.sendMessage(String.format(ChatColor.GREEN + "Игрок %s наказан", player.getName()));
                Bukkit.getScheduler().cancelTasks(main);
                }, 100L);
        }return false;
    }
    public static void sphereEffect(final Player player){
        Bukkit.getScheduler().runTaskLaterAsynchronously(main, () -> {
            for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
                double radius = Math.sin(i) * 3;
                double y = Math.cos(i) * 3 + 1;
                for (double a = 0; a < Math.PI * 2; a += Math.PI / 10) {
                    double x = Math.cos(a) * radius;
                    double z = Math.sin(a) * radius;
                    Location loc = player.getLocation();
                    loc.add(x, y, z);
                    loc.getWorld().spawnParticle(Particle.DOLPHIN, loc, 20);
                    loc.subtract(x, y, z);
                }
            }
        }, 90L);
    }
}




