package me.saltyaimbotter.demonSkills.skills;

import me.saltyaimbotter.demonSkills.DemonSkills;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.event.Listener;

public class SkillListeners implements Listener {

    private final DemonSkills instance;
    private final Permission perms;

    public SkillListeners() {
        instance = DemonSkills.getInstance();
        perms = DemonSkills.getPerms();
    }

//    @EventHandler
//    public void onJoin(PlayerJoinEvent event) {
//        UUID uuid = event.getPlayer().getUniqueId();
//        instance.addEffectsProfile(uuid, new EffectsProfile(event.getPlayer()));
//
//        //Sense Skill
//        List<Entity> nearbyPlayers = event.getPlayer().getNearbyEntities(50, 50, 50)
//                .parallelStream().filter(e -> e instanceof Player).collect(Collectors.toList());
//        if (!nearbyPlayers.isEmpty()) {
//            String perm = SENSE.getPermission();
//            for (Player p : Bukkit.getOnlinePlayers()) {
//                if (perms.has(p, perm)) {
//                    if (p == event.getPlayer()) return;
//                    p.sendMessage("§5You sense a presence nearby.");
//                }
//            }
//        }
//    }
//
//    @EventHandler
//    public void onKill(EntityDeathEvent event) {
//        Player p = event.getEntity().getKiller();
//
//        if (p != null) {
//            if (perms.has(p, BANQUET.getPermission())) {
//                p.setFoodLevel(p.getFoodLevel() + 1);
//            }
//        }
//    }
//
//    @EventHandler
//    public void onEntityDamage(EntityDamageByEntityEvent event) {
//        if (!(event.getEntity() instanceof Player)) {
//            return;
//        }
//
//        Player p = (Player) event.getEntity();
//        if (perms.has(p, SUPREME.getPermission())) {
//            event.setDamage(event.getDamage() * 0.8);
//        }
//
//        if (event.getDamager() instanceof Player) {
//            Player damager = (Player) event.getDamager();
//            if (perms.has(damager, INDOMITABLE.getPermission())) {
//                event.setDamage(event.getDamage() * 1.2);
//            }
//        }
//
//        if (perms.has(p, PAIN.getPermission())) {
//            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 0));
//            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2));
//        }
//    }
//
//    @EventHandler
//    public void onVelocityChange(PlayerVelocityEvent event) {
//        Player p = event.getPlayer();
//        if (perms.has(p, UNRELENTING.getPermission())) {
//            event.setCancelled(true);
//        }
//    }
//    @EventHandler
//    public void onDamage(EntityDamageEvent event) {
//        if (!(event.getEntity() instanceof Player)) {
//            return;
//        }
//
//
//        Player p = (Player) event.getEntity();
//        if (perms.has(p, UNBREAKABLE.getPermission())) {
//            event.setDamage(event.getDamage() - 1);
//        }
//        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
//            if (perms.has(p, FLAWLESS.getPermission())) {
//                event.setCancelled(true);
//            }
//        }
//    }
}
