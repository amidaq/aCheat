package ru.amidaku.acheat.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.amidaku.acheat.ACheat;

public class Listeners implements Listener {

    private ACheat main;

    public void onHit(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player && main.isCheat(e.getEntity())){
            e.setCancelled(true);
        }
    }
}
