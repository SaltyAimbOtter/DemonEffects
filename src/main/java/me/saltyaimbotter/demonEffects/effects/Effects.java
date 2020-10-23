package me.saltyaimbotter.demonEffects.effects;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import static me.saltyaimbotter.demonEffects.DemonEffects.getPerms;
import static me.saltyaimbotter.demonEffects.effects.SimplePotionEffects.applyEffect;

public class Effects {

    public static int applyEffects(Player p, EFFECT effect) {
        OfflinePlayer offp = Bukkit.getOfflinePlayer(p.getUniqueId());
        switch (effect) {
            case REGEN:
                getPerms().playerAdd(null, offp, "demonic.regen");
                return applyEffect(p, PotionEffectType.REGENERATION);
            case VIOLENCE:
                getPerms().playerAdd(null, offp, "demonic.violence");
                return applyEffect(p, PotionEffectType.SPEED);
            case VISION:
                getPerms().playerAdd(null, offp,"demonic.vision");
                return applyEffect(p, PotionEffectType.NIGHT_VISION);
            case WRAITH:
                getPerms().playerAdd(null, offp, "demonic.wraith");
                return applyEffect(p, PotionEffectType.INVISIBILITY);
            case IMMORTAL:
                getPerms().playerAdd(null, offp, "demonic.immortal");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case BANQUIET:
                getPerms().playerAdd(null, offp, "demonic.banquiet");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case PAIN:
                getPerms().playerAdd(null, offp, "demonic.pain");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case INDOMITABLE:
                getPerms().playerAdd(null, offp, "demonic.indomitable");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case UNRELENTING:
                getPerms().playerAdd(null, offp, "demonic.unrelenting");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case FLAWLESS:
                getPerms().playerAdd(null, offp, "demonic.flawless");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case SUPREME:
                getPerms().playerAdd(null, offp, "demonic.supreme");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case UNBREAKABLE:
                getPerms().playerAdd(null, offp, "demonic.unbreakable");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
            case SENSE:
                getPerms().playerAdd(null, offp, "demonic.sense");
                return applyEffect(p, PotionEffectType.DAMAGE_RESISTANCE);
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

