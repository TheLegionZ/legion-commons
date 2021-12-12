package br.com.thelegion.legioncommons;

import br.com.thelegion.legioncommons.fixes.Log4jExploitFixListener;
import br.com.thelegion.legioncommons.menu.MenuProvider;
import br.com.thelegion.legioncommons.tick.AsyncServerTickEvent;
import br.com.thelegion.legioncommons.tick.ServerTickEvent;
import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static br.com.thelegion.legioncommons.plugin.PluginUtils.registerPluginListeners;

@Getter
public final class LegionCommonsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerPluginListeners(this, new MenuProvider(this));

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, AsyncServerTickEvent::callEvent, 0, 1);
        Bukkit.getScheduler().runTaskTimer(this, ServerTickEvent::callEvent, 0, 1);

        ProtocolLibrary.getProtocolManager().addPacketListener(new Log4jExploitFixListener(this));
    }

    @Override
    public void onDisable() {

    }

    public static LegionCommonsPlugin getInstance() {
        return getPlugin(LegionCommonsPlugin.class);
    }
}
