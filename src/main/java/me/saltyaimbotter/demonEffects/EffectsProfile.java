package me.saltyaimbotter.demonEffects;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;

import static me.saltyaimbotter.demonEffects.DemonEffects.getPerms;
import static me.saltyaimbotter.demonEffects.effects.Effects.EFFECT;
import static me.saltyaimbotter.demonEffects.effects.Effects.EFFECT.*;
import static me.saltyaimbotter.demonEffects.effects.Effects.applyEffects;

public class EffectsProfile {

    private final Player p;
    /**
     * Used for storing active effects and the schedulerTask ID if that effect has a task.
     * Also stores effects with null schedulerTasks IDs so they can be easily removed all at once.
     */
    private final HashMap<EFFECT, Integer> taskList = new HashMap<>();

    /**
     * A new profile gets created when a player joins.
     * Checking for permissions here to reapply all effects.
     * Only needed for effects with a task though.
     *
     * @param p Player
     */
    public EffectsProfile(Player p) {
        this.p = p;

        Permission perms = DemonEffects.getPerms();
        if (perms.has(p, REGEN.getPermission())) {
            startEffect(REGEN);
        }
        if (perms.has(p, VIOLENCE.getPermission())) {
            startEffect(VIOLENCE);
        }
        if (perms.has(p, VISION.getPermission())) {
            startEffect(VISION);
        }
        if (perms.has(p, WRAITH.getPermission())) {
            startEffect(WRAITH);
        }
        if (perms.has(p, IMMORTAL.getPermission())) {
            startEffect(IMMORTAL);
        }
    }


    public void startEffect(EFFECT effect) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p.getUniqueId());
        getPerms().playerAdd(null, offlinePlayer, effect.getPermission());

        int taskId = applyEffects(p, effect);
        if (taskId < 1 && taskId != -100) {
            Bukkit.getLogger().log(Level.SEVERE, "Scheduling effect tasks failed. This should never happen! Maybe your server is overloaded?");
            return;
        }
        taskList.put(effect, taskId);
    }

    public void cancelAllEffects() {
        for (Integer integer : taskList.values()) {
            if (integer != null) {
                Bukkit.getScheduler().cancelTask(integer);
            }
        }
        taskList.clear();
    }

    public void removePerms() {
        Permission perms = DemonEffects.getPerms();
        OfflinePlayer offp = Bukkit.getOfflinePlayer(p.getUniqueId());
        for (EFFECT effect : taskList.keySet()) {
            perms.playerRemove(null, offp, effect.getPermission());
        }
    }
}
