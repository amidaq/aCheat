package ru.amidaku.acheat.Effects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.amidaku.acheat.ACheat;

public class EffectManager {
    private static ACheat main;

    public static void coneEffect(final Player player){
        new BukkitRunnable(){
        double phi = 0;

            @Override
            public void run() {
                phi += Math.PI / 8;
                double x;
                double y;
                double z;
                Location loc = player.getLocation();
                for (double t = 0; t <= 2*Math.PI; t+= Math.PI/16){
                    for (double i = 0; i <= 1; i+= 0.1){
                        x = 0.3 * (2*Math.PI-t)*0.5*Math.cos(t+phi+i*Math.PI);
                        y = t;
                        z = 0.3 * (2*Math.PI-t)*0.5*Math.sin(t+phi+i*Math.PI);
                        loc.add(x,y,z);
                        loc.getWorld().spawnParticle(Particle.DOLPHIN, loc, 20);
                        loc.subtract(x,y,z);
                    }
                }
            }
        }.runTaskTimerAsynchronously(main, 0, 1);
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