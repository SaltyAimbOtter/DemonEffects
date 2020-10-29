package me.saltyaimbotter.demonEffects.effects;

import me.saltyaimbotter.demonEffects.DemonEffects;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class SimplePotionEffects {

    public static int applyPotionEffect(Player p, PotionEffectType effectType, int amplifier) {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(DemonEffects.getPlugin(), () -> {
            Collection<PotionEffect> activeEffects = p.getActivePotionEffects();
            for (PotionEffect activeEffect : activeEffects) {
                if (activeEffect.getType().equals(effectType)) {
                    if (activeEffect.getDuration() > 5) {
                        return;
                    }
                }
            }
            p.addPotionEffect(new PotionEffect(effectType, Integer.MAX_VALUE, amplifier));
        }, 0, 100);
    }
}
