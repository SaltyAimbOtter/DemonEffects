package me.saltyaimbotter.demonEffects;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;

import static me.saltyaimbotter.demonEffects.effects.Effects.EFFECT;
import static me.saltyaimbotter.demonEffects.effects.Effects.EFFECT.REGEN;
import static me.saltyaimbotter.demonEffects.effects.Effects.EFFECT.VISION;
import static me.saltyaimbotter.demonEffects.effects.Effects.applyEffects;

public class EffectsProfile {

    Player p;
    HashMap<EFFECT, Integer> taskList = new HashMap<>();

    public EffectsProfile(Player p) {
        this.p = p;

        Permission perms = DemonEffects.getPerms();
        if (perms.has(p, "demonic.regen")) {
            startEffect(REGEN);
        }
        if (perms.has(p, "demonic.vision")) {
            startEffect(VISION);
        }
    }



    public void startEffect(EFFECT effect) {
        int taskId = applyEffects(p, effect);
        if (taskId < 1) {
            Bukkit.getLogger().log(Level.SEVERE, "Scheduling effect tasks failed. This should never happen! Maybe your server is overloaded?");
            return;
        }
        taskList.put(effect, taskId);
    }

    public void cancelAllEffects() {
        for (Integer integer : taskList.values()) {
            Bukkit.getScheduler().cancelTask(integer);
        }
        taskList.clear();
    }
}
