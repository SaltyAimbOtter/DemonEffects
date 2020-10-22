package me.saltyaimbotter.vampiresim.effects;

import me.saltyaimbotter.vampiresim.VampireSim;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Effects {

    public void applyEffects(Player p, EFFECT effect) {
        switch (effect) {
            case REGEN:
                SimplePotionEffects.applyEffect(p, PotionEffectType.REGENERATION);
                VampireSim.getPerms().playerAdd(p, "demonic.regen");
                break;
            case VIOLENCE:
                SimplePotionEffects.applyEffect(p, PotionEffectType.SPEED);
                VampireSim.getPerms().playerAdd(p, "demonic.speed");
                break;
            case VISION:
                SimplePotionEffects.applyEffect(p, PotionEffectType.NIGHT_VISION);
                VampireSim.getPerms().playerAdd(p, "demonic.regen");
                break;
            case WRAITH:
                SimplePotionEffects.applyEffect(p, PotionEffectType.INVISIBILITY);
                break;
            case IMMORTAL:
                SimplePotionEffects.applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
                break;

        }

    }

    public enum EFFECT {
        REGEN,
        VIOLENCE,
        VISION,
        WRAITH,
        IMMORTAL,
        BANQUIET,
        PAIN,
        INDOMITABLE,
        UNRELENTING,
        FLAWLESS,
        SUPREME,
        UNBREAKABLE,
        SENSE
    }
}

