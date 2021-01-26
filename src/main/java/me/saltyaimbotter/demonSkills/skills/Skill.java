package me.saltyaimbotter.demonSkills.skills;

import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.bukkit.potion.PotionEffectType;

import java.util.Locale;

import static org.bukkit.potion.PotionEffectType.*;

public class Skill {

    public enum LISTENER_SKILL {
        //Damage Effects | EntityDamageByEntityEvent -> Player
        UNDENIABLE(), // 1.5 extra true damage for melee attacks

        //Damage Effects | EntityDamageEvent -> Player
        UNCONTROLLABLE(), //No explosion and fire damage
        UNSTOPPABLE(), //No knockback,
        DESIRES(), //on damage: +0.5 hearts 1sec resistance [2sec cooldown]

        //DeathEffects | EntityDeathEvent
        BANQUET(), //+0.5 hunger upon any kill

        //AI Effects
        //TODO
        SOULLESS(), //Zombies and skeletons do not aggro onto the player unless attacked

        //Special Effects
        SENSE(); //Display a message "&5You feel a presence nearby" to the person in chat whenever another player logs in within 100 blocks [30s cooldown]

        /**
         * Returns the permission of the effect.
         * Example: "demonic.sense"
         */
        public String getPermission() {
            return "demonic." + toString().toLowerCase(Locale.ROOT);
        }

    }

    public enum POTION_SKILL {

        //PotionEffects | Scheduled Tasks
        UNBREAKABLE(new ImmutablePair<>(DAMAGE_RESISTANCE, 1)),
        BLOODLETTING(new ImmutablePair<>(REGENERATION, 1)),
        ADRENALINE(new ImmutablePair<>(SPEED, 1)),
        PHANTOM(new ImmutablePair<>(INVISIBILITY, 1), new ImmutablePair<>(NIGHT_VISION, 1));

        @Getter
        ImmutablePair<PotionEffectType, Integer>[] potionData;


        @SafeVarargs
        POTION_SKILL(ImmutablePair<PotionEffectType, Integer>... potionData) {
            this.potionData = potionData;
        }

        /**
         * Returns the permission of the effect.
         * Example: "demonic.unbreakable"
         */

        public String getPermission() {
            return "demonic." + toString().toLowerCase(Locale.ROOT);
        }
    }
}

