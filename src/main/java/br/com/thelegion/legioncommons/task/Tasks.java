package br.com.thelegion.legioncommons.task;

import br.com.thelegion.legioncommons.annotations.AsyncExpected;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.Callable;

public class Tasks {
	private Tasks() {
	}

	public static void ensureMainThread(Plugin plugin, Runnable runnable) {
		Bukkit.getScheduler().runTask(plugin, runnable);
	}

	public static <T> void callSyncMethod(Plugin plugin, Callable<T> callable) {
		Bukkit.getScheduler().callSyncMethod(plugin, callable);
	}

	@AsyncExpected
	public static void ensureAsync(Plugin plugin, Runnable runnable) {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
	}

	public static void repeating(Plugin plugin, Runnable runnable, long delay, long period) {
		Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, period);
	}

	@AsyncExpected
	public static void repeatingAsync(Plugin plugin, Runnable runnable, long delay, long period) {
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, period);
	}
}
