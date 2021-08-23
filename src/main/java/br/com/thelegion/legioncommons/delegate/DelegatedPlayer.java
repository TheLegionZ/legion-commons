package br.com.thelegion.legioncommons.delegate;

import com.google.common.base.Preconditions;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.util.*;

public abstract class DelegatedPlayer implements Player {

  private final Player player;

  public DelegatedPlayer(Player player) {
    Preconditions.checkNotNull(player, "player");
    this.player = player;
  }

  public String getDisplayName() {
    return this.player.getDisplayName();
  }

  public void setDisplayName(String s) {
    this.player.setDisplayName(s);
  }

  public String getPlayerListName() {
    return this.player.getPlayerListName();
  }

  public void setPlayerListName(String s) {
    this.player.setPlayerListName(s);
  }

  public void setCompassTarget(Location location) {
    this.player.setCompassTarget(location);
  }

  public Location getCompassTarget() {
    return this.player.getCompassTarget();
  }

  public InetSocketAddress getAddress() {
    return this.player.getAddress();
  }

  public void sendRawMessage(String s) {
    this.player.sendRawMessage(s);
  }

  public void kickPlayer(String s) {
    this.player.kickPlayer(s);
  }

  public void chat(String s) {
    this.player.chat(s);
  }

  public boolean performCommand(String s) {
    return this.player.performCommand(s);
  }

  public boolean isSneaking() {
    return this.player.isSneaking();
  }

  public void setSneaking(boolean b) {
    this.player.setSneaking(b);
  }

  public boolean isSprinting() {
    return this.player.isSprinting();
  }

  public void setSprinting(boolean b) {
    this.player.setSprinting(b);
  }

  public void saveData() {
    this.player.saveData();
  }

  public void loadData() {
    this.player.loadData();
  }

  public void setSleepingIgnored(boolean b) {
    this.player.setSleepingIgnored(b);
  }

  public boolean isSleepingIgnored() {
    return this.player.isSleepingIgnored();
  }

  public void playNote(Location location, byte b, byte b1) {
    this.player.playNote(location, b, b1);
  }

  public void playNote(Location location, Instrument instrument, Note note) {
    this.player.playNote(location, instrument, note);
  }

  public void playSound(Location location, Sound sound, float v, float v1) {
    this.player.playSound(location, sound, v, v1);
  }

  public void playSound(Location location, String s, float v, float v1) {
    this.player.playSound(location, s, v, v1);
  }

  public void playEffect(Location location, Effect effect, int i) {
    this.player.playEffect(location, effect, i);
  }

  public <T> void playEffect(Location location, Effect effect, T t) {
    this.player.playEffect(location, effect, t);
  }

  public void sendBlockChange(Location location, Material material, byte b) {
    this.player.sendBlockChange(location, material, b);
  }

  public boolean sendChunkChange(Location location, int i, int i1, int i2, byte[] bytes) {
    return this.player.sendChunkChange(location, i, i1, i2, bytes);
  }

  public void sendBlockChange(Location location, int i, byte b) {
    this.player.sendBlockChange(location, i, b);
  }

  public void sendSignChange(Location location, String[] strings) throws IllegalArgumentException {
    this.player.sendSignChange(location, strings);
  }

  public void sendMap(MapView mapView) {
    this.player.sendMap(mapView);
  }

  public void updateInventory() {
    this.player.updateInventory();
  }

  public void awardAchievement(Achievement achievement) {
    this.player.awardAchievement(achievement);
  }

  public void removeAchievement(Achievement achievement) {
    this.player.removeAchievement(achievement);
  }

  public boolean hasAchievement(Achievement achievement) {
    return this.player.hasAchievement(achievement);
  }

  public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
    this.player.incrementStatistic(statistic);
  }

  public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
    this.player.decrementStatistic(statistic);
  }

  public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
    this.player.incrementStatistic(statistic, i);
  }

  public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
    this.player.decrementStatistic(statistic, i);
  }

  public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {
    this.player.setStatistic(statistic, i);
  }

  public int getStatistic(Statistic statistic) throws IllegalArgumentException {
    return this.player.getStatistic(statistic);
  }

  public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
    this.player.incrementStatistic(statistic, material);
  }

  public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
    this.player.decrementStatistic(statistic, material);
  }

  public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
    return this.player.getStatistic(statistic, material);
  }

  public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
    this.player.incrementStatistic(statistic, material, i);
  }

  public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
    this.player.decrementStatistic(statistic, material, i);
  }

  public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
    this.player.setStatistic(statistic, material, i);
  }

  public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
    this.player.incrementStatistic(statistic, entityType);
  }

  public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
    this.player.decrementStatistic(statistic, entityType);
  }

  public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
    return this.player.getStatistic(statistic, entityType);
  }

  public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException {
    this.player.incrementStatistic(statistic, entityType, i);
  }

  public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {
    this.player.decrementStatistic(statistic, entityType, i);
  }

  public void setStatistic(Statistic statistic, EntityType entityType, int i) {
    this.player.setStatistic(statistic, entityType, i);
  }

  public void setPlayerTime(long l, boolean b) {
    this.player.setPlayerTime(l, b);
  }

  public long getPlayerTime() {
    return this.player.getPlayerTime();
  }

  public long getPlayerTimeOffset() {
    return this.player.getPlayerTimeOffset();
  }

  public boolean isPlayerTimeRelative() {
    return this.player.isPlayerTimeRelative();
  }

  public void resetPlayerTime() {
    this.player.resetPlayerTime();
  }

  public void setPlayerWeather(WeatherType weatherType) {
    this.player.setPlayerWeather(weatherType);
  }

  public WeatherType getPlayerWeather() {
    return this.player.getPlayerWeather();
  }

  public void resetPlayerWeather() {
    this.player.resetPlayerWeather();
  }

  public void giveExp(int i) {
    this.player.giveExp(i);
  }

  public void giveExpLevels(int i) {
    this.player.giveExpLevels(i);
  }

  public float getExp() {
    return this.player.getExp();
  }

  public void setExp(float v) {
    this.player.setExp(v);
  }

  public int getLevel() {
    return this.player.getLevel();
  }

  public void setLevel(int i) {
    this.player.setLevel(i);
  }

  public int getTotalExperience() {
    return this.player.getTotalExperience();
  }

  public void setTotalExperience(int i) {
    this.player.setTotalExperience(i);
  }

  public float getExhaustion() {
    return this.player.getExhaustion();
  }

  public void setExhaustion(float v) {
    this.player.setExhaustion(v);
  }

  public float getSaturation() {
    return this.player.getSaturation();
  }

  public void setSaturation(float v) {
    this.player.setSaturation(v);
  }

  public int getFoodLevel() {
    return this.player.getFoodLevel();
  }

  public void setFoodLevel(int i) {
    this.player.setFoodLevel(i);
  }

  public Location getBedSpawnLocation() {
    return this.player.getBedSpawnLocation();
  }

  public void setBedSpawnLocation(Location location) {
    this.player.setBedSpawnLocation(location);
  }

  public void setBedSpawnLocation(Location location, boolean b) {
    this.player.setBedSpawnLocation(location, b);
  }

  public boolean getAllowFlight() {
    return this.player.getAllowFlight();
  }

  public void setAllowFlight(boolean b) {
    this.player.setAllowFlight(b);
  }

  public void hidePlayer(Player player) {
    this.player.hidePlayer(player);
  }

  public void showPlayer(Player player) {
    this.player.showPlayer(player);
  }

  public boolean canSee(Player player) {
    return this.player.canSee(player);
  }

  public boolean isOnGround() {
    return this.player.isOnGround();
  }

  public boolean isFlying() {
    return this.player.isFlying();
  }

  public void setFlying(boolean b) {
    this.player.setFlying(b);
  }

  public void setFlySpeed(float v) throws IllegalArgumentException {
    this.player.setFlySpeed(v);
  }

  public void setWalkSpeed(float v) throws IllegalArgumentException {
    this.player.setWalkSpeed(v);
  }

  public float getFlySpeed() {
    return this.player.getFlySpeed();
  }

  public float getWalkSpeed() {
    return this.player.getWalkSpeed();
  }

  public void setTexturePack(String s) {
    this.player.setTexturePack(s);
  }

  public void setResourcePack(String s) {
    this.player.setResourcePack(s);
  }

  public Scoreboard getScoreboard() {
    return this.player.getScoreboard();
  }

  public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
    this.player.setScoreboard(scoreboard);
  }

  public boolean isHealthScaled() {
    return this.player.isHealthScaled();
  }

  public void setHealthScaled(boolean b) {
    this.player.setHealthScaled(b);
  }

  public void setHealthScale(double v) throws IllegalArgumentException {
    this.player.setHealthScale(v);
  }

  public double getHealthScale() {
    return this.player.getHealthScale();
  }

  public Entity getSpectatorTarget() {
    return this.player.getSpectatorTarget();
  }

  public void setSpectatorTarget(Entity entity) {
    this.player.setSpectatorTarget(entity);
  }

  public void sendTitle(String s, String s1) {
    this.player.sendTitle(s, s1);
  }

  public void resetTitle() {
    this.player.resetTitle();
  }

  public Spigot spigot() {
    return this.player.spigot();
  }

  public String getName() {
    return this.player.getName();
  }

  public PlayerInventory getInventory() {
    return this.player.getInventory();
  }

  public Inventory getEnderChest() {
    return this.player.getEnderChest();
  }

  public boolean setWindowProperty(InventoryView.Property property, int i) {
    return this.player.setWindowProperty(property, i);
  }

  public InventoryView getOpenInventory() {
    return this.player.getOpenInventory();
  }

  public InventoryView openInventory(Inventory inventory) {
    return this.player.openInventory(inventory);
  }

  public InventoryView openWorkbench(Location location, boolean b) {
    return this.player.openWorkbench(location, b);
  }

  public InventoryView openEnchanting(Location location, boolean b) {
    return this.player.openEnchanting(location, b);
  }

  public void openInventory(InventoryView inventoryView) {
    this.player.openInventory(inventoryView);
  }

  public void closeInventory() {
    this.player.closeInventory();
  }

  public ItemStack getItemInHand() {
    return this.player.getItemInHand();
  }

  public void setItemInHand(ItemStack itemStack) {
    this.player.setItemInHand(itemStack);
  }

  public ItemStack getItemOnCursor() {
    return this.player.getItemOnCursor();
  }

  public void setItemOnCursor(ItemStack itemStack) {
    this.player.setItemOnCursor(itemStack);
  }

  public boolean isSleeping() {
    return this.player.isSleeping();
  }

  public int getSleepTicks() {
    return this.player.getSleepTicks();
  }

  public GameMode getGameMode() {
    return this.player.getGameMode();
  }

  public void setGameMode(GameMode gameMode) {
    this.player.setGameMode(gameMode);
  }

  public boolean isBlocking() {
    return this.player.isBlocking();
  }

  public int getExpToLevel() {
    return this.player.getExpToLevel();
  }

  public double getEyeHeight() {
    return this.player.getEyeHeight();
  }

  public double getEyeHeight(boolean b) {
    return this.player.getEyeHeight(b);
  }

  public Location getEyeLocation() {
    return this.player.getEyeLocation();
  }

  public List<Block> getLineOfSight(HashSet<Byte> hashSet, int i) {
    return this.player.getLineOfSight(hashSet, i);
  }

  public List<Block> getLineOfSight(Set<Material> set, int i) {
    return this.player.getLineOfSight(set, i);
  }

  public Block getTargetBlock(HashSet<Byte> hashSet, int i) {
    return this.player.getTargetBlock(hashSet, i);
  }

  public Block getTargetBlock(Set<Material> set, int i) {
    return this.player.getTargetBlock(set, i);
  }

  public List<Block> getLastTwoTargetBlocks(HashSet<Byte> hashSet, int i) {
    return this.player.getLastTwoTargetBlocks(hashSet, i);
  }

  public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
    return this.player.getLastTwoTargetBlocks(set, i);
  }

  public Egg throwEgg() {
    return this.player.throwEgg();
  }

  public Snowball throwSnowball() {
    return this.player.throwSnowball();
  }

  public Arrow shootArrow() {
    return this.player.shootArrow();
  }

  public int getRemainingAir() {
    return this.player.getRemainingAir();
  }

  public void setRemainingAir(int i) {
    this.player.setRemainingAir(i);
  }

  public int getMaximumAir() {
    return this.player.getMaximumAir();
  }

  public void setMaximumAir(int i) {
    this.player.setMaximumAir(i);
  }

  public int getMaximumNoDamageTicks() {
    return this.player.getMaximumNoDamageTicks();
  }

  public void setMaximumNoDamageTicks(int i) {
    this.player.setMaximumNoDamageTicks(i);
  }

  public double getLastDamage() {
    return this.player.getLastDamage();
  }

  public void setLastDamage(double v) {
    this.player.setLastDamage(v);
  }

  public int getNoDamageTicks() {
    return this.player.getNoDamageTicks();
  }

  public void setNoDamageTicks(int i) {
    this.player.setNoDamageTicks(i);
  }

  public Player getKiller() {
    return this.player.getKiller();
  }

  public boolean addPotionEffect(PotionEffect potionEffect) {
    return this.player.addPotionEffect(potionEffect);
  }

  public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
    return this.player.addPotionEffect(potionEffect, b);
  }

  public boolean addPotionEffects(Collection<PotionEffect> collection) {
    return this.player.addPotionEffects(collection);
  }

  public boolean hasPotionEffect(PotionEffectType potionEffectType) {
    return this.player.hasPotionEffect(potionEffectType);
  }

  public void removePotionEffect(PotionEffectType potionEffectType) {
    this.player.removePotionEffect(potionEffectType);
  }

  public Collection<PotionEffect> getActivePotionEffects() {
    return this.player.getActivePotionEffects();
  }

  public boolean hasLineOfSight(Entity entity) {
    return this.player.hasLineOfSight(entity);
  }

  public boolean getRemoveWhenFarAway() {
    return this.player.getRemoveWhenFarAway();
  }

  public void setRemoveWhenFarAway(boolean b) {
    this.player.setRemoveWhenFarAway(b);
  }

  public EntityEquipment getEquipment() {
    return this.player.getEquipment();
  }

  public void setCanPickupItems(boolean b) {
    this.player.setCanPickupItems(b);
  }

  public boolean getCanPickupItems() {
    return this.player.getCanPickupItems();
  }

  public boolean isLeashed() {
    return this.player.isLeashed();
  }

  public Entity getLeashHolder() throws IllegalStateException {
    return this.player.getLeashHolder();
  }

  public boolean setLeashHolder(Entity entity) {
    return this.player.setLeashHolder(entity);
  }

  public Location getLocation() {
    return this.player.getLocation();
  }

  public Location getLocation(Location location) {
    return this.player.getLocation(location);
  }

  public void setVelocity(Vector vector) {
    this.player.setVelocity(vector);
  }

  public Vector getVelocity() {
    return this.player.getVelocity();
  }

  public World getWorld() {
    return this.player.getWorld();
  }

  public boolean teleport(Location location) {
    return this.player.teleport(location);
  }

  public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
    return this.player.teleport(location, teleportCause);
  }

  public boolean teleport(Entity entity) {
    return this.player.teleport(entity);
  }

  public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
    return this.player.teleport(entity, teleportCause);
  }

  public List<Entity> getNearbyEntities(double v, double v1, double v2) {
    return this.player.getNearbyEntities(v, v1, v2);
  }

  public int getEntityId() {
    return this.player.getEntityId();
  }

  public int getFireTicks() {
    return this.player.getFireTicks();
  }

  public int getMaxFireTicks() {
    return this.player.getMaxFireTicks();
  }

  public void setFireTicks(int i) {
    this.player.setFireTicks(i);
  }

  public void remove() {
    this.player.remove();
  }

  public boolean isDead() {
    return this.player.isDead();
  }

  public boolean isValid() {
    return this.player.isValid();
  }

  public Server getServer() {
    return this.player.getServer();
  }

  public Entity getPassenger() {
    return this.player.getPassenger();
  }

  public boolean setPassenger(Entity entity) {
    return this.player.setPassenger(entity);
  }

  public boolean isEmpty() {
    return this.player.isEmpty();
  }

  public boolean eject() {
    return this.player.eject();
  }

  public float getFallDistance() {
    return this.player.getFallDistance();
  }

  public void setFallDistance(float v) {
    this.player.setFallDistance(v);
  }

  public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
    this.player.setLastDamageCause(entityDamageEvent);
  }

  public EntityDamageEvent getLastDamageCause() {
    return this.player.getLastDamageCause();
  }

  public UUID getUniqueId() {
    return this.player.getUniqueId();
  }

  public int getTicksLived() {
    return this.player.getTicksLived();
  }

  public void setTicksLived(int i) {
    this.player.setTicksLived(i);
  }

  public void playEffect(EntityEffect entityEffect) {
    this.player.playEffect(entityEffect);
  }

  public EntityType getType() {
    return this.player.getType();
  }

  public boolean isInsideVehicle() {
    return this.player.isInsideVehicle();
  }

  public boolean leaveVehicle() {
    return this.player.leaveVehicle();
  }

  public Entity getVehicle() {
    return this.player.getVehicle();
  }

  public void setCustomName(String s) {
    this.player.setCustomName(s);
  }

  public String getCustomName() {
    return this.player.getCustomName();
  }

  public void setCustomNameVisible(boolean b) {
    this.player.setCustomNameVisible(b);
  }

  public boolean isCustomNameVisible() {
    return this.player.isCustomNameVisible();
  }

  public void setMetadata(String s, MetadataValue metadataValue) {
    this.player.setMetadata(s, metadataValue);
  }

  public List<MetadataValue> getMetadata(String s) {
    return this.player.getMetadata(s);
  }

  public boolean hasMetadata(String s) {
    return this.player.hasMetadata(s);
  }

  public void removeMetadata(String s, Plugin plugin) {
    this.player.removeMetadata(s, plugin);
  }

  public void sendMessage(String s) {
    this.player.sendMessage(s);
  }

  public void sendMessage(String[] strings) {
    this.player.sendMessage(strings);
  }

  public boolean isPermissionSet(String s) {
    return this.player.isPermissionSet(s);
  }

  public boolean isPermissionSet(Permission permission) {
    return this.player.isPermissionSet(permission);
  }

  public boolean hasPermission(String s) {
    return this.player.hasPermission(s);
  }

  public boolean hasPermission(Permission permission) {
    return this.player.hasPermission(permission);
  }

  public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
    return this.player.addAttachment(plugin, s, b);
  }

  public PermissionAttachment addAttachment(Plugin plugin) {
    return this.player.addAttachment(plugin);
  }

  public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
    return this.player.addAttachment(plugin, s, b, i);
  }

  public PermissionAttachment addAttachment(Plugin plugin, int i) {
    return this.player.addAttachment(plugin, i);
  }

  public void removeAttachment(PermissionAttachment permissionAttachment) {
    this.player.removeAttachment(permissionAttachment);
  }

  public void recalculatePermissions() {
    this.player.recalculatePermissions();
  }

  public Set<PermissionAttachmentInfo> getEffectivePermissions() {
    return this.player.getEffectivePermissions();
  }

  public boolean isOp() {
    return this.player.isOp();
  }

  public void setOp(boolean b) {
    this.player.setOp(b);
  }

  public void damage(double v) {
    this.player.damage(v);
  }

  public void damage(double v, Entity entity) {
    this.player.damage(v, entity);
  }

  public double getHealth() {
    return this.player.getHealth();
  }

  public void setHealth(double v) {
    this.player.setHealth(v);
  }

  public double getMaxHealth() {
    return this.player.getMaxHealth();
  }

  public void setMaxHealth(double v) {
    this.player.setMaxHealth(v);
  }

  public void resetMaxHealth() {
    this.player.resetMaxHealth();
  }

  public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
    return this.player.launchProjectile(aClass);
  }

  public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
    return this.player.launchProjectile(aClass, vector);
  }

  public boolean isConversing() {
    return this.player.isConversing();
  }

  public void acceptConversationInput(String s) {
    this.player.acceptConversationInput(s);
  }

  public boolean beginConversation(Conversation conversation) {
    return this.player.beginConversation(conversation);
  }

  public void abandonConversation(Conversation conversation) {
    this.player.abandonConversation(conversation);
  }

  public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {
    this.player.abandonConversation(conversation, conversationAbandonedEvent);
  }

  public boolean isOnline() {
    return this.player.isOnline();
  }

  public boolean isBanned() {
    return this.player.isBanned();
  }

  public void setBanned(boolean b) {
    this.player.setBanned(b);
  }

  public boolean isWhitelisted() {
    return this.player.isWhitelisted();
  }

  public void setWhitelisted(boolean b) {
    this.player.setWhitelisted(b);
  }

  public Player getPlayer() {
    return this.player.getPlayer();
  }

  public long getFirstPlayed() {
    return this.player.getFirstPlayed();
  }

  public long getLastPlayed() {
    return this.player.getLastPlayed();
  }

  public boolean hasPlayedBefore() {
    return this.player.hasPlayedBefore();
  }

  public Map<String, Object> serialize() {
    return this.player.serialize();
  }

  public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {
    this.player.sendPluginMessage(plugin, s, bytes);
  }

  public Set<String> getListeningPluginChannels() {
    return this.player.getListeningPluginChannels();
  }
}
