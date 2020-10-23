package me.saltyaimbotter.demonEffects;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Listeners implements Listener {

    private final DemonEffects instance;

    public Listeners() {
        instance = DemonEffects.getInstance();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        DemonEffects instance = DemonEffects.getInstance();
        EffectsProfile profile = instance.getProfile(event.getEntity().getUniqueId());
        profile.cancelAllEffects();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        instance.addEffectsProfile(uuid, new EffectsProfile(event.getPlayer()));
    }
}
