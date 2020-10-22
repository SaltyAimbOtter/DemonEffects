package me.saltyaimbotter.vampiresim.effects;

import me.saltyaimbotter.vampiresim.VampireSim;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SimplePotionEffects {



    public static void applyEffect(Player p, PotionEffectType effectType) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(VampireSim.getPlugin(), new Runnable() {
            @Override
            public void run() {
                p.addPotionEffect(new PotionEffect(effectType,20,1));
            }
        },0,100);
    }
}
