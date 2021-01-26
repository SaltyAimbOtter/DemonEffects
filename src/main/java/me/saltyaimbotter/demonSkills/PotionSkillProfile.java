package me.saltyaimbotter.demonSkills;

import net.milkbowl.vault.permission.Permission;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Map;

import static me.saltyaimbotter.demonSkills.skills.Skill.POTION_SKILL;

/**
 * The PotionSkillProfile object manages all {@link POTION_SKILL} skills that a player has.
 */
public class PotionSkillProfile {

    private final Player p;
    private final Permission perms;

    /**
     * Used for storing active effects and the schedulerTask ID if that effect has a task.
     * Also stores effects with null schedulerTasks IDs so they can be easily removed all at once.
     */
    private final HashMap<POTION_SKILL, Integer[]> taskList = new HashMap<>();

    /**
     * A new profile gets created when a player joins.
     * Automatically re-applies all {@link POTION_SKILL}s.
     *
     * @param player Player
     */
    public PotionSkillProfile(Player player) {
        this.p = player;
        perms = DemonSkills.getPerms();

        for (POTION_SKILL effect : POTION_SKILL.values()) {
            if (perms.has(player, effect.getPermission())) {
                startEffect(effect);
            }
        }
    }

    /**
     * Reports this object of a changed "demonic." permission.
     * The affected effect will be adjusted based on that change.
     *
     * @param changedPermission that changed.
     */
    public void reportChange(String changedPermission, boolean permissionGranted) {
        if (!changedPermission.contains("demonic.")) {
            return;
        }

        for (POTION_SKILL potionSkill : POTION_SKILL.values()) {
            if (!potionSkill.getPermission().equals(changedPermission)) {
                continue;
            }

            if (permissionGranted) {
                startEffect(potionSkill);
            } else {
                cancelTaskAndEffect(potionSkill);
            }
        }
    }

    /**
     * Starts the specified potionSkill for the current player if the player does not have the "demonic.admin" permission.
     *
     * @param potionSkill The skill to apply.
     */
    public void startEffect(POTION_SKILL potionSkill) {
        if (perms.has(p, "demonic.admin")) {
            return;
        }
        BukkitScheduler scheduler = Bukkit.getScheduler();
        Plugin plugin = DemonSkills.getPlugin();

        Integer[] taskIDs = new Integer[potionSkill.getPotionData().length];

        for (int i = 0; i < potionSkill.getPotionData().length; i++) {
            ImmutablePair<PotionEffectType, Integer> currentEffect = potionSkill.getPotionData()[i];
            PotionEffect effect = new PotionEffect(currentEffect.left, 360, currentEffect.right,true,false);

            taskIDs[i] = scheduler.scheduleSyncRepeatingTask(plugin, () -> p.addPotionEffect(effect), 0, 140);
        }

        taskList.put(potionSkill, taskIDs);
    }

    /**
     * Removes the specified {@link POTION_SKILL} from the player.
     * <br>
     * Cancels all Tasks and removes the PotionEffects from the player.
     * Effects are removed in case the permissions change while the player is offline. They should not be saved in the
     * players data.
     *
     * @param potionSkill the {@link POTION_SKILL} that will be removed.
     */
    public void cancelTaskAndEffect(POTION_SKILL potionSkill) {
        Integer[] taskID = taskList.get(potionSkill);
        for (int i : taskID) {
            Bukkit.getScheduler().cancelTask(i);
        }
        for (ImmutablePair<PotionEffectType, Integer> effectData : potionSkill.getPotionData()) {
            p.removePotionEffect(effectData.left);
        }
    }

    /**
     * Removes all {@link POTION_SKILL} skills from a player.
     */
    public void cancelAllTasksAndEffects() {
        for (Map.Entry<POTION_SKILL, Integer[]> skill : taskList.entrySet()) {
            cancelTaskAndEffect(skill.getKey());
        }
    }
}
