package me.saltyaimbotter.demonEffects;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public final class DemonEffects extends JavaPlugin {

    private static Permission perms = null;
    public static Plugin plugin = null;

    private static DemonEffects instance;

    protected HashMap<UUID, EffectsProfile> playerEffectProfiles = new HashMap<>();


    public DemonEffects() {
        instance = this;
    }

    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("vamps").setExecutor(new Command());
        getServer().getPluginManager().registerEvents(new Listeners(), this);
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

    public static DemonEffects getInstance() {
        return instance;
    }

    public static Permission getPerms() {
        return perms;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public EffectsProfile getProfile(UUID uuid) {
        return playerEffectProfiles.get(uuid);
    }

    public void addEffectsProfile(UUID uuid, EffectsProfile profile) {
        this.playerEffectProfiles.put(uuid, profile);

    }
}
