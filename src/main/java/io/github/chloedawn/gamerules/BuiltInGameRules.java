/*
 * Copyright (C) 2019 Chloe Dawn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.chloedawn.gamerules;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Contract;

public final class BuiltInGameRules {
	private BuiltInGameRules() {
	}

	@Contract(pure = true)
	public static boolean hasFireTick(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_FIRE_TICK);
	}

	@Contract(mutates = "param1")
	public static void setHasFireTick(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_FIRE_TICK, value);
	}

	@Contract(pure = true)
	public static boolean getMobGriefing(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.MOB_GRIEFING);
	}

	@Contract(mutates = "param1")
	public static void setMobGriefing(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.MOB_GRIEFING, value);
	}

	@Contract(pure = true)
	public static boolean getKeepInventory(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.KEEP_INVENTORY);
	}

	@Contract(mutates = "param1")
	public static void setKeepInventory(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.KEEP_INVENTORY, value);
	}

	@Contract(pure = true)
	public static boolean hasMobSpawning(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_MOB_SPAWNING);
	}

	@Contract(mutates = "param1")
	public static void setHasMobSpawning(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_MOB_SPAWNING, value);
	}

	@Contract(pure = true)
	public static boolean hasMobLoot(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_MOB_LOOT);
	}

	@Contract(mutates = "param1")
	public static void setHasMobLoot(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_MOB_LOOT, value);
	}

	@Contract(pure = true)
	public static boolean hasTileDrops(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_TILE_DROPS);
	}

	@Contract(mutates = "param1")
	public static void setHasTileDrops(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_TILE_DROPS, value);
	}

	@Contract(pure = true)
	public static boolean hasEntityDrops(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_ENTITY_DROPS);
	}

	@Contract(mutates = "param1")
	public static void setHasEntityDrops(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_ENTITY_DROPS, value);
	}

	@Contract(pure = true)
	public static boolean getCommandBlockOutput(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.COMMAND_BLOCK_OUTPUT);
	}

	@Contract(mutates = "param1")
	public static void setCommandBlockOutput(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.COMMAND_BLOCK_OUTPUT, value);
	}

	@Contract(pure = true)
	public static boolean getNaturalRegeneration(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.NATURAL_REGENERATION);
	}

	@Contract(mutates = "param1")
	public static void setNaturalRegeneration(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.NATURAL_REGENERATION, value);
	}

	@Contract(pure = true)
	public static boolean hasDaylightCycle(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_DAYLIGHT_CYCLE);
	}

	@Contract(mutates = "param1")
	public static void setHasDaylightCycle(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_DAYLIGHT_CYCLE, value);
	}

	@Contract(pure = true)
	public static boolean getLogAdminCommands(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.LOG_ADMIN_COMMANDS);
	}

	@Contract(mutates = "param1")
	public static void setLogAdminCommands(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.LOG_ADMIN_COMMANDS, value);
	}

	@Contract(pure = true)
	public static boolean getShowDeathMessages(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.SHOW_DEATH_MESSAGES);
	}

	@Contract(mutates = "param1")
	public static void setShowDeathMessages(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.SHOW_DEATH_MESSAGES, value);
	}

	@Contract(pure = true)
	public static int getRandomTickSpeed(final MinecraftServer server) {
		return RuleDelegates.getInt(server, GameRules.RANDOM_TICK_SPEED);
	}

	@Contract(mutates = "param1")
	public static void setRandomTickSpeed(final MinecraftServer server, final int value) {
		RuleDelegates.setInt(server, GameRules.RANDOM_TICK_SPEED, value);
	}

	@Contract(pure = true)
	public static boolean getSendCommandFeedback(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.SEND_COMMAND_FEEDBACK);
	}

	@Contract(mutates = "param1")
	public static void setSendCommandFeedback(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.SEND_COMMAND_FEEDBACK, value);
	}

	@Contract(pure = true)
	public static boolean getReducedDebugInfo(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.REDUCED_DEBUG_INFO);
	}

	@Contract(mutates = "param1")
	public static void setReducedDebugInfo(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.REDUCED_DEBUG_INFO, value);
	}

	@Contract(pure = true)
	public static boolean getSpectatorsGenerateChunks(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.SPECTATORS_GENERATE_CHUNKS);
	}

	@Contract(mutates = "param1")
	public static void setSpectatorsGenerateChunks(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.SPECTATORS_GENERATE_CHUNKS, value);
	}

	@Contract(pure = true)
	public static int getSpawnRadius(final MinecraftServer server) {
		return RuleDelegates.getInt(server, GameRules.SPAWN_RADIUS);
	}

	@Contract(mutates = "param1")
	public static void setSpawnRadius(final MinecraftServer server, final int value) {
		RuleDelegates.setInt(server, GameRules.SPAWN_RADIUS, value);
	}

	@Contract(pure = true)
	public static boolean getDisableElytraMovementCheck(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK);
	}

	@Contract(mutates = "param1")
	public static void setDisableElytraMovementCheck(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK, value);
	}

	@Contract(pure = true)
	public static int getMaxEntityCramming(final MinecraftServer server) {
		return RuleDelegates.getInt(server, GameRules.MAX_ENTITY_CRAMMING);
	}

	@Contract(mutates = "param1")
	public static void setMaxEntityCramming(final MinecraftServer server, final int value) {
		RuleDelegates.setInt(server, GameRules.MAX_ENTITY_CRAMMING, value);
	}

	@Contract(pure = true)
	public static boolean hasWeatherCycle(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_WEATHER_CYCLE);
	}

	@Contract(mutates = "param1")
	public static void setHasWeatherCycle(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_WEATHER_CYCLE, value);
	}

	@Contract(pure = true)
	public static boolean hasLimitedCrafting(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_LIMITED_CRAFTING);
	}

	@Contract(mutates = "param1")
	public static void setHasLimitedCrafting(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_LIMITED_CRAFTING, value);
	}

	@Contract(pure = true)
	public static int getMaxCommandChainLength(final MinecraftServer server) {
		return RuleDelegates.getInt(server, GameRules.MAX_COMMAND_CHAIN_LENGTH);
	}

	@Contract(mutates = "param1")
	public static void setMaxCommandChainLength(final MinecraftServer server, final int value) {
		RuleDelegates.setInt(server, GameRules.MAX_COMMAND_CHAIN_LENGTH, value);
	}

	@Contract(pure = true)
	public static boolean getAnnounceAdvancements(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.ANNOUNCE_ADVANCEMENTS);
	}

	@Contract(mutates = "param1")
	public static void setAnnounceAdvancements(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.ANNOUNCE_ADVANCEMENTS, value);
	}

	@Contract(pure = true)
	public static boolean getDisableRaids(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DISABLE_RAIDS);
	}

	@Contract(mutates = "param1")
	public static void setDisableRaids(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DISABLE_RAIDS, value);
	}

	@Contract(pure = true)
	public static boolean hasInsomnia(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_INSOMNIA);
	}

	@Contract(mutates = "param1")
	public static void setHasInsomnia(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_INSOMNIA, value);
	}

	@Contract(pure = true)
	public static boolean hasImmediateRespawn(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DO_IMMEDIATE_RESPAWN);
	}

	@Contract(mutates = "param1")
	public static void setHasImmediateRespawn(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DO_IMMEDIATE_RESPAWN, value);
	}

	@Contract(pure = true)
	public static boolean hasDrowningDamage(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.DROWNING_DAMAGE);
	}

	@Contract(mutates = "param1")
	public static void setHasDrowningDamage(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.DROWNING_DAMAGE, value);
	}

	@Contract(pure = true)
	public static boolean hasFallDamage(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.FALL_DAMAGE);
	}

	@Contract(mutates = "param1")
	public static void setHasFallDamage(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.FALL_DAMAGE, value);
	}

	@Contract(pure = true)
	public static boolean hasFireDamage(final MinecraftServer server) {
		return RuleDelegates.getBoolean(server, GameRules.FIRE_DAMAGE);
	}

	@Contract(mutates = "param1")
	public static void setHasFireDamage(final MinecraftServer server, final boolean value) {
		RuleDelegates.setBoolean(server, GameRules.FIRE_DAMAGE, value);
	}
}
