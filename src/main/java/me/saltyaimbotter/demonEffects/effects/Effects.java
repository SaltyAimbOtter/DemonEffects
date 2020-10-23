package me.saltyaimbotter.demonEffects.effects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import static me.saltyaimbotter.demonEffects.effects.SimplePotionEffects.applyPotionEffect;

public class Effects {

    public static int applyEffects(Player p, EFFECT effect) {
        switch (effect) {
            case REGEN:
                return applyPotionEffect(p, PotionEffectType.REGENERATION);
            case VIOLENCE:
                return applyPotionEffect(p, PotionEffectType.SPEED);
            case VISION:
                return applyPotionEffect(p, PotionEffectType.NIGHT_VISION);
            case WRAITH:
                return applyPotionEffect(p, PotionEffectType.INVISIBILITY);
            case IMMORTAL:
                return applyPotionEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case BANQUIET:
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
        REGEN("demonic.regen"),
        VIOLENCE("demonic.violence"),
        VISION("demonic.vision"),
        WRAITH("demonic.wraith"),
        IMMORTAL("demonic.immortal"),

        //Health / Damaging Effects
        BANQUIET("demonic.banquiet"),
        PAIN("demonic.pain"),
        INDOMITABLE("demonic.indomitable"),
        UNRELENTING("demonic.unrelenting"),
        FLAWLESS("demonic.flawless"),
        SUPREME("demonic.supreme"),
        UNBREAKABLE("demonic.unbreakable"),

        //Special Effects
        SENSE("demonic.sense");

        private final String permission;
        EFFECT(String permission) {
            this.permission = permission;
        }

        public String getPermission() {
            return permission;
        }
    }
}

