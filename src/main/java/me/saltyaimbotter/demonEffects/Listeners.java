package me.saltyaimbotter.demonEffects;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static me.saltyaimbotter.demonEffects.effects.Effects.EFFECT.BANQUIET;

public class Listeners implements Listener {

    private final DemonEffects instance;
    private final Permission perms;

    public Listeners() {
        instance = DemonEffects.getInstance();
        perms = DemonEffects.getPerms();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        DemonEffects instance = DemonEffects.getInstance();
        EffectsProfile profile = instance.getProfile(event.getEntity().getUniqueId());
        profile.removePerms();
        profile.cancelAllEffects();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        instance.addEffectsProfile(uuid, new EffectsProfile(event.getPlayer()));
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
         Player p = event.getEntity().getKiller();
         if (perms.has(p, BANQUIET.getPermission())) {

         }



    }
}
