package me.saltyaimbotter.demonEffects;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static me.saltyaimbotter.demonEffects.effects.Effects.EFFECT.*;

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
    public void onLogout(PlayerQuitEvent event) {
        DemonEffects instance = DemonEffects.getInstance();
        EffectsProfile profile = instance.getProfile(event.getPlayer().getUniqueId());
        profile.cancelAllEffects();
        instance.removeEffectsProfile(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        instance.addEffectsProfile(uuid, new EffectsProfile(event.getPlayer()));

        //Sense Skill
        List<Entity> nearbyPlayers = event.getPlayer().getNearbyEntities(50, 50, 50)
                .parallelStream().filter(e -> e instanceof Player).collect(Collectors.toList());
        if (!nearbyPlayers.isEmpty()) {
            String perm = SENSE.getPermission();
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (perms.has(p, perm)) {
                    if (p == event.getPlayer()) return;
                    p.sendMessage("§5You sense a presence nearby.");
                }
            }
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Player p = event.getEntity().getKiller();

        if (p != null) {
            if (perms.has(p, BANQUET.getPermission())) {
                p.setFoodLevel(p.getFoodLevel() + 1);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player) event.getEntity();
        if (perms.has(p, SUPREME.getPermission())) {
            event.setDamage(event.getDamage() * 0.8);
        }

        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if (perms.has(damager, INDOMITABLE.getPermission())) {
                event.setDamage(event.getDamage() * 1.2);
            }
        }

        if (perms.has(p, PAIN.getPermission())) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2));
        }
    }

    @EventHandler
    public void onVelocityChange(PlayerVelocityEvent event) {
        Player p = event.getPlayer();
        if (perms.has(p, UNRELENTING.getPermission())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }


        Player p = (Player) event.getEntity();
        if (perms.has(p, UNBREAKABLE.getPermission())) {
            event.setDamage(event.getDamage() - 1);
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (perms.has(p, FLAWLESS.getPermission())) {
                event.setCancelled(true);
            }
        }
    }
}
