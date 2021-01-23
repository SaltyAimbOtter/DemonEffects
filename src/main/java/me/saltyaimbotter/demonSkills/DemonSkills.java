package me.saltyaimbotter.demonSkills;

import lombok.Getter;
import me.saltyaimbotter.demonSkills.skills.SkillListeners;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public final class DemonSkills extends JavaPlugin {

    private static Permission perms = null;
    public static Plugin plugin = null;

    private static DemonSkills instance;

    @Getter
    protected HashMap<UUID, SkillProfile> playerEffectProfiles = new HashMap<>();


    public DemonSkills()
    {
        instance = this;
        plugin = this;
    }

    @Override
    public void onEnable() {
        if (!setupPermissions()) {
            getLogger().log(Level.SEVERE, "Vault was not found. No permissions could be set. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
        }

        getServer().getPluginManager().registerEvents(new SkillListeners(), this);
        getServer().getPluginManager().registerEvents(new ProfileListeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static DemonSkills getInstance() {
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
}
