package br.com.thelegion.legioncommons.service.economy;

import br.com.thelegion.legioncommons.annotations.BukkitService;
import br.com.thelegion.legioncommons.service.economy.exceptions.EconomyProviderNotFoundException;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

@BukkitService
public final class EconomyService {

  private final Plugin plugin;

  private Economy economy;

  public EconomyService(Plugin plugin) {
    this.plugin = plugin;
    this.setup();
  }

  public double getPlayerMoney(OfflinePlayer player) throws EconomyProviderNotFoundException {
    if (economy == null) {
      throw new EconomyProviderNotFoundException("Economy provider not found on " + plugin.getName());
    }


    return economy.getBalance(player);
  }

  public boolean withdraw(OfflinePlayer player, double amount) throws EconomyProviderNotFoundException {
    if (economy == null) {
      throw new EconomyProviderNotFoundException("Economy provider not found on " + plugin.getName());
    }

    return economy.withdrawPlayer(player, amount).transactionSuccess();
  }

  public boolean deposit(OfflinePlayer player, double amount) throws EconomyProviderNotFoundException {
    if (economy == null) {
      throw new EconomyProviderNotFoundException("Economy provider not found on " + plugin.getName());
    }

    return economy.depositPlayer(player, amount).transactionSuccess();
  }


  private void setup() {
    if (economy != null) {
      return;
    }

    RegisteredServiceProvider<Economy> registration = plugin.getServer().getServicesManager().getRegistration(Economy.class);
    if (registration == null) {
      return;
    }

    Economy provider = registration.getProvider();
    if (provider == null) {
      return;
    }

    this.economy = provider;
  }
}
