package ru.amidaku.acheat.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.amidaku.acheat.ACheat;

public class Listeners implements Listener {

    private ACheat main;

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player && main.isCheat((Player) e.getEntity())){
            e.setCancelled(true);
        }
    }
}
