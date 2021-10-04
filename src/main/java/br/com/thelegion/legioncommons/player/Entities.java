package br.com.thelegion.legioncommons.player;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.UUID;
import java.util.function.Consumer;

public class Entities {
	
	public static void forEachServerEntities(Consumer<Entity> entityConsumer) {
		for (World world : Bukkit.getWorlds()) {
			world.getEntities().forEach(entityConsumer);
		}
	}
	
	public static Entity getEntityFromUniqueId(UUID uniqueId){
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if(entity.getUniqueId().equals(uniqueId)){
					return entity;
				}
			}
		}
		
		return null;
	}
}
