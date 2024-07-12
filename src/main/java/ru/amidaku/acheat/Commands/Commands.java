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
            final Player cheater = Bukkit.getPlayer(args[0]);
            if (cheater == null){
                sender.sendMessage(Color.RED + "Он оффлайн");
                return true;
            }
            if ((cheater.hasPermission("acheat.admin"))){
                sender.sendMessage(ChatColor.RED + "Ты хочешь " + ChatColor.GREEN + cheater.getName() + ChatColor.RED + " вызвать?");
                return true;
            }
            if (cheater.equals(sender)){
                sender.sendMessage(ChatColor.RED + "Нельзя так с собой");
                return true;
            }
            cheater.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100, 1));
            sphereEffect(cheater);
            Bukkit.getScheduler().runTaskLater(main, () -> {
                cheater.getWorld().spawnParticle(Particle.FLAME, cheater.getLocation(), 400);
                cheater.setHealth(0.0);
                cheater.getWorld().playSound(cheater.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 10);
                Bukkit.dispatchCommand(sender, String.format("ipban %s 30d 2.5 -s", cheater.getName()));
                sender.sendMessage(String.format(ChatColor.GREEN + "Игрок %s наказан", cheater.getName()));
                Bukkit.getScheduler().cancelTasks(main);
                }, 100L);

        }return false;
    }
    public static void sphereEffect(final Player player){
        final Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(245, 76, 0), 0.5F);
        Bukkit.getScheduler().runTaskTimerAsynchronously(main, () -> {
            for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
                double radius = Math.sin(i) * 3;
                double y = Math.cos(i) * 3 + 1;
                for (double a = 0; a < Math.PI * 2; a += Math.PI / 10) {
                    double x = Math.cos(a) * radius;
                    double z = Math.sin(a) * radius;
                    Location loc = player.getLocation();
                    loc.add(x, y, z);
                    loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0.0, 0.0, 0.0, dust);
                    loc.subtract(x, y, z);
                }
            }
        }, 0, 1);
    }
}




