package br.com.thelegion.legioncommons;

import br.com.thelegion.legioncommons.command.CommandUtils;
import br.com.thelegion.legioncommons.internal.InternalMenuTestCommand;
import br.com.thelegion.legioncommons.menu.MenuProvider;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class LegionCommonsPlugin extends JavaPlugin {

	private MenuProvider menuProvider;

	@Override
	public void onEnable() {
		menuProvider = new MenuProvider(this);

		CommandUtils.getCommandMap().register("internal" , new InternalMenuTestCommand(this));
	}

	@Override
	public void onDisable() {

	}

	public static LegionCommonsPlugin getInstance() {
		return getPlugin(LegionCommonsPlugin.class);
	}
}
