package me.saltyaimbotter.vampiresim;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class VampireSim extends JavaPlugin {

    private static Permission perms = null;
    public static Plugin plugin = null;

    @Override
    public void onEnable() {
        this.plugin = this;
        this.getCommand("vamps").setExecutor(new Command());
       if (!setupPermissions()) {
           getLogger().log(Level.SEVERE, "Vault was not found. No permissions could be set. Disabling...");
           getServer().getPluginManager().disablePlugin(this);
       }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static Permission getPerms() {
        return perms;
    }
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}
