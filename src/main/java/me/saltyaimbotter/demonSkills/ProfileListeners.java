package me.saltyaimbotter.demonSkills;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class ProfileListeners implements Listener {

    private final DemonSkills instance;
    HashMap<UUID, SkillProfile> playerEffectProfiles;

    public ProfileListeners() {
        instance = DemonSkills.getInstance();
        playerEffectProfiles = instance.getPlayerEffectProfiles();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        UUID uuid = event.getEntity().getUniqueId();

        SkillProfile profile = playerEffectProfiles.get(uuid);
        profile.cancelAllTasksAndEffects();
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        SkillProfile profile = playerEffectProfiles.get(uuid);

        profile.cancelAllTasksAndEffects();
        playerEffectProfiles.remove(uuid);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        playerEffectProfiles.put(uuid, new SkillProfile(event.getPlayer()));
    }

    //TODO: LuckPerms integration
    @EventHandler
    public void onPermissionsChange() {
    }
}
