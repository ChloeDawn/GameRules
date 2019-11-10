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

package io.github.chloedawn.gamerules

import net.minecraft.server.MinecraftServer
import net.minecraft.world.GameRules

var MinecraftServer.hasFireTick by GameRules.DO_FIRE_TICK

var MinecraftServer.mobGriefing by GameRules.MOB_GRIEFING

var MinecraftServer.keepInventory by GameRules.KEEP_INVENTORY

var MinecraftServer.hasMobSpawning by GameRules.DO_MOB_SPAWNING

var MinecraftServer.hasMobLoot by GameRules.DO_MOB_LOOT

var MinecraftServer.hasTileDrops by GameRules.DO_TILE_DROPS

var MinecraftServer.hasEntityDrops by GameRules.DO_ENTITY_DROPS

var MinecraftServer.commandBlockOutput by GameRules.COMMAND_BLOCK_OUTPUT

var MinecraftServer.naturalRegeneration by GameRules.NATURAL_REGENERATION

var MinecraftServer.hasDaylightCycle by GameRules.DO_DAYLIGHT_CYCLE

var MinecraftServer.logAdminCommands by GameRules.LOG_ADMIN_COMMANDS

var MinecraftServer.showDeathMessages by GameRules.SHOW_DEATH_MESSAGES

var MinecraftServer.randomTickSpeed by GameRules.RANDOM_TICK_SPEED

var MinecraftServer.sendCommandFeedback by GameRules.SEND_COMMAND_FEEDBACK

var MinecraftServer.reducedDebugInfo by GameRules.REDUCED_DEBUG_INFO

var MinecraftServer.spectatorsGenerateChunks by GameRules.SPECTATORS_GENERATE_CHUNKS

var MinecraftServer.spawnRadius by GameRules.SPAWN_RADIUS

var MinecraftServer.disableElytraMovementCheck by GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK

var MinecraftServer.maxEntityCramming by GameRules.MAX_ENTITY_CRAMMING

var MinecraftServer.hasWeatherCycle by GameRules.DO_WEATHER_CYCLE

var MinecraftServer.hasLimitedCrafting by GameRules.DO_LIMITED_CRAFTING

var MinecraftServer.maxCommandChainLength by GameRules.MAX_COMMAND_CHAIN_LENGTH

var MinecraftServer.announceAdvancements by GameRules.ANNOUNCE_ADVANCEMENTS

var MinecraftServer.disableRaids by GameRules.DISABLE_RAIDS

var MinecraftServer.hasInsomnia by GameRules.DO_INSOMNIA

var MinecraftServer.hasImmediateRespawn by GameRules.DO_IMMEDIATE_RESPAWN

var MinecraftServer.hasDrowningDamage by GameRules.DROWNING_DAMAGE

var MinecraftServer.hasFallDamage by GameRules.FALL_DAMAGE

var MinecraftServer.hasFireDamage by GameRules.FIRE_DAMAGE
