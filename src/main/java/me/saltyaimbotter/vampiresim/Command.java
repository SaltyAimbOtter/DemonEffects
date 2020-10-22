package me.saltyaimbotter.vampiresim;

import me.saltyaimbotter.vampiresim.effects.Effects;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            sender.sendMessage("Player " + args[0] + "does not exist.");
            return true;
        }

        Effects.EFFECT effect = Effects.EFFECT.valueOf(args[1]);



    }
}
