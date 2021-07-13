package br.com.thelegion.legioncommons.internal;

import br.com.thelegion.legioncommons.LegionCommonsPlugin;
import br.com.thelegion.legioncommons.internal.menu.InternalMenu;
import br.com.thelegion.legioncommons.menu.MenuProvider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InternalMenuTestCommand extends Command {

	private final MenuProvider provider;

	public InternalMenuTestCommand(LegionCommonsPlugin plugin) {
		super("testmenu");

		this.setPermission("legioncommons.testmenu");

		this.provider = plugin.getMenuProvider();
	}

	@Override
	public boolean execute(CommandSender commandSender, String s, String[] strings) {
		if (!commandSender.hasPermission(getPermission())) {
			return false;
		}

		if (!(commandSender instanceof Player)) {
			return false;
		}

		provider.openMenu(((Player) commandSender), new InternalMenu());
		return false;
	}
}
