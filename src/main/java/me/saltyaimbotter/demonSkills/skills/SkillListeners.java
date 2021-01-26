package me.saltyaimbotter.demonSkills.skills;

import me.saltyaimbotter.demonSkills.DemonSkills;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static me.saltyaimbotter.demonSkills.skills.Skill.LISTENER_SKILL;
import static me.saltyaimbotter.demonSkills.skills.Skill.LISTENER_SKILL.*;
import static org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.*;

public class SkillListeners implements Listener {

    private final Permission perms;

    private final HashMap<UUID, Long> senseCooldown = new HashMap<>();
    private final HashMap<UUID, Long> desiresCooldown = new HashMap<>();

    public SkillListeners() {
        perms = DemonSkills.getPerms();
    }

    /**
     * Handles the SENSE {@link LISTENER_SKILL}.
     *
     * @param event passed by Bukkit.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        //Sense Skill
        List<Entity> nearbyPlayers = event.getPlayer().getNearbyEntities(50, 50, 50)
                .parallelStream().filter(e -> e instanceof Player).collect(Collectors.toList());

        String perm = SENSE.getPermission();
        for (Entity player : nearbyPlayers) {
            if (!perms.has(player, perm) || perms.has(player, "demonic.admin")) {
                continue;
            }
            long epoch = Instant.now().getEpochSecond();
            UUID uuid = player.getUniqueId();

            if (senseCooldown.getOrDefault(uuid, 0L) >= epoch - 30) {
                continue;
            }
            player.sendMessage("§5You sense a presence nearby.");
            senseCooldown.put(uuid, epoch);
        }

    }

    /**
     * Handles the BANQUET {@link LISTENER_SKILL}.
     *
     * @param event passed by Bukkit.
     */
    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Player p = event.getEntity().getKiller();

        if (p == null) {
            return;
        }
        if (!perms.has(p, BANQUET.getPermission()) || perms.has(p, "demonic.admin")) {
            return;
        }
        p.setFoodLevel(p.getFoodLevel() + 1);

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player p = (Player) event.getDamager();

        if (perms.has(p, UNDENIABLE.getPermission()) && !perms.has(p, "demonic.admin")) {
            event.setDamage(event.getDamage() + 3);
        }

        if (perms.has(p, DESIRES.getPermission()) && !perms.has(p, "demonic.admin")) {
            long epoch = Instant.now().getEpochSecond();
            UUID uuid = p.getUniqueId();
            if (desiresCooldown.getOrDefault(uuid, 0L) >= epoch - 2) {
                return;
            }

            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 1, true, false));
            if (p.getHealth() >= 20) {
                return;
            }
            p.setHealth(p.getHealth() + 1);
            desiresCooldown.put(uuid, epoch);
        }

    }

    @EventHandler
    public void onVelocityChange(PlayerVelocityEvent event) {
        Player p = event.getPlayer();
        if (perms.has(p, UNSTOPPABLE.getPermission()) && !perms.has(p, "demonic.admin")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) event.getEntity();

        //Uncontrollable skill
        DamageCause cause = event.getCause();
        if (cause == FIRE || cause == FIRE_TICK || cause == ENTITY_EXPLOSION || cause == BLOCK_EXPLOSION || cause == HOT_FLOOR) {
            if (perms.has(p, UNCONTROLLABLE.getPermission()) && !perms.has(p, "demonic.admin")) {
                event.setCancelled(true);
            }
        }
        if (cause == LAVA) {
            if (perms.has(p, UNCONTROLLABLE.getPermission()) && !perms.has(p, "demonic.admin")) {
                event.setDamage(event.getDamage() * 0.25);
            }
        }
    }
}

