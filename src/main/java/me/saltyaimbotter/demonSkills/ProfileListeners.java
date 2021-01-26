package me.saltyaimbotter.demonSkills;

import me.saltyaimbotter.demonSkills.skills.Skill;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

import static me.saltyaimbotter.demonSkills.DemonSkills.*;
import static org.bukkit.Bukkit.getServer;

public class ProfileListeners implements Listener {

    LuckPerms luckPerms = LuckPermsProvider.get();
    Permission perms = getPerms();

    HashMap<UUID, PotionSkillProfile> playerEffectProfiles;

    public ProfileListeners() {
        playerEffectProfiles = getInstance().getPlayerEffectProfiles();

        EventBus luckPermsEventBus = luckPerms.getEventBus();
        luckPermsEventBus.subscribe(getPlugin(), NodeAddEvent.class, this::onPermissionAddition);
        luckPermsEventBus.subscribe(getPlugin(), NodeRemoveEvent.class, this::onPermissionRemoval);
    }

    //TODO: Remove permission
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        PotionSkillProfile profile = playerEffectProfiles.get(player.getUniqueId());
        profile.cancelAllTasksAndEffects();

        for (Skill.POTION_SKILL potionSkill : Skill.POTION_SKILL.values()) {
            perms.playerRemove(player, potionSkill.getPermission());
        }
        for (Skill.LISTENER_SKILL listenerSkill : Skill.LISTENER_SKILL.values()) {
            perms.playerRemove(player, listenerSkill.getPermission());
        }
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        PotionSkillProfile profile = playerEffectProfiles.get(uuid);

        profile.cancelAllTasksAndEffects();
        playerEffectProfiles.remove(uuid);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        playerEffectProfiles.put(uuid, new PotionSkillProfile(event.getPlayer()));
    }


    public void onPermissionAddition(NodeAddEvent event) {
        if (!event.isUser() || event.getNode().getType() != NodeType.PERMISSION) {
            return;
        }

        User player = (User) event.getTarget();
        PermissionNode permissionNode = (PermissionNode) event.getNode();
        String permission = permissionNode.getPermission();

        getServer().getScheduler().runTask(getPlugin(), () -> {
            PotionSkillProfile profile = playerEffectProfiles.get(player.getUniqueId());
            profile.reportChange(permission, true);
        });
    }

    public void onPermissionRemoval(NodeRemoveEvent event) {
        if (!event.isUser() || event.getNode().getType() != NodeType.PERMISSION) {
            return;
        }

        User player = (User) event.getTarget();
        PermissionNode permissionNode = (PermissionNode) event.getNode();
        String permission = permissionNode.getPermission();

        getServer().getScheduler().runTask(getPlugin(), () -> {
            PotionSkillProfile profile = playerEffectProfiles.get(player.getUniqueId());
            profile.reportChange(permission, false);
        });
    }
}
