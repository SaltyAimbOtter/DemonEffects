package me.saltyaimbotter.demonEffects;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.saltyaimbotter.demonEffects.effects.Effects.EFFECT;

public class Command implements CommandExecutor {

    // /vamps SaltyAimbOtter WRAITH
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!sender.isOp()) {
            return true;
        }
        if (args.length != 2) {
            return true;
        }

        Player p = Bukkit.getServer().getPlayer(args[0]);
        if (p == null) {
            sender.sendMessage("Player " + args[0] + "does not exist. Contact the admins.");
            return true;
        }
        EFFECT effect = null;
        try {
            effect = EFFECT.valueOf(args[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage("This is not a valid effect. Contact the admins.");
            return true;
        }
        DemonEffects instance = DemonEffects.getInstance();
        instance.getProfile(p.getUniqueId()).startEffect(effect);
        return true;

    }
}
