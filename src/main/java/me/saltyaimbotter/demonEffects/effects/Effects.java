package me.saltyaimbotter.demonEffects.effects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Locale;

import static me.saltyaimbotter.demonEffects.effects.SimplePotionEffects.applyPotionEffect;

public class Effects {

    public static int applyEffects(Player p, EFFECT effect) {
        switch (effect) {
            case REGEN:
                return applyPotionEffect(p, PotionEffectType.REGENERATION, 0);
            case VIOLENCE:
                return applyPotionEffect(p, PotionEffectType.SPEED, 1);
            case VISION:
                return applyPotionEffect(p, PotionEffectType.NIGHT_VISION, 0);
            case WRAITH:
                return applyPotionEffect(p, PotionEffectType.INVISIBILITY, 0);
            case IMMORTAL:
                return applyPotionEffect(p, PotionEffectType.DAMAGE_RESISTANCE, 0);
            case BANQUET:
            case PAIN:
            case INDOMITABLE:
            case UNRELENTING:
            case FLAWLESS:
            case SUPREME:
            case UNBREAKABLE:
            case SENSE:
                return -100;
            default:
                return -2;
        }
    }


    public enum EFFECT {

        //PotionEffects
        UNBREAKABLE(), //RESISTANCE
        BLOODLETTING(), //REGEN

        //Damage Effects
        UNCONTROLLABLE(), //No explosion and fire damage
        UNDENIABLE(), // 1.5 extra true damage for melee attacks
        UNSTOPPABLE(), //No knockback,
        DESIRES(), //

        //Health / Damaging Effects,
        ADRENALINE(),
        BANQUET(),
        PHANTOM(),
        SOULLESS(),


        //Special Effects
        SENSE();

        EFFECT() { }

        public String getPermission() {
            return "demonic." + toString().toLowerCase(Locale.ROOT);
        }
    }

}

