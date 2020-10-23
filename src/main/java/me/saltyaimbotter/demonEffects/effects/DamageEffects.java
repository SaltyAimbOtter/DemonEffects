package me.saltyaimbotter.demonEffects.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPotionEffectEvent;

public class DamageEffects {

    @EventHandler
    public void onPlayerDamage(EntityPotionEffectEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
    }
}
