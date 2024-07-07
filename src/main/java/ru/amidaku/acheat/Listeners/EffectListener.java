package ru.amidaku.acheat.Listeners;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ru.amidaku.acheat.ACheat;
public class EffectListener implements Listener {

    private ACheat main;

    public void giveEffect(PlayerMoveEvent e) {
        if (main.isCheat(e.getPlayer())) {
            Location loc = e.getPlayer().getLocation();
            new BukkitRunnable() {
                double pri = 0;

                @Override
                public void run() {
                    pri += Math.PI / 10;

                    for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 10) {
                        double r = 1.5;
                        double x = Math.sin(theta) * r * Math.cos(pri);
                        double y = Math.cos(pri) + r;
                        double z = Math.sin(theta) * r * Math.sin(pri);
                        loc.add(x, y, z);
                        loc.getWorld().spawnParticle(Particle.DRIPPING_HONEY, loc, 100);
                        loc.subtract(x, y, z);
                    }
                    if (pri > 2 * Math.PI) {
                        this.cancel();
                    }
                }
            }.runTaskTimer(main, 0, 1);
        }
    }
}