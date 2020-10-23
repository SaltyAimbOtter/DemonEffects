package me.saltyaimbotter.demonEffects.effects;

import me.saltyaimbotter.demonEffects.DemonEffects;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class SimplePotionEffects {



    public static int applyEffect(Player p, PotionEffectType effectType) {
    return Bukkit.getScheduler().scheduleSyncRepeatingTask(DemonEffects.getPlugin(), () -> {
            Collection<PotionEffect> activeEffects = p.getActivePotionEffects();
            for (PotionEffect activeEffect : activeEffects) {
                if (activeEffect.getType().equals(effectType)) {
                    if (activeEffect.getDuration() > 5) {
                        return;
                    }
                }
            }
            p.addPotionEffect(new PotionEffect(effectType,Integer.MAX_VALUE,0));
        },0,100);
    }
}
