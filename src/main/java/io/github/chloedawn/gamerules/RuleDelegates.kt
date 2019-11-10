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

import com.google.common.annotations.Beta
import net.minecraft.server.MinecraftServer
import net.minecraft.world.GameRules.BooleanRule
import net.minecraft.world.GameRules.IntRule
import net.minecraft.world.GameRules.RuleKey
import kotlin.reflect.KProperty

@JvmName("getBoolean")
operator fun MinecraftServer.get(key: RuleKey<BooleanRule>): Boolean =
	RuleDelegates.getBoolean(this, key)

@JvmName("setBoolean")
operator fun MinecraftServer.set(key: RuleKey<BooleanRule>, value: Boolean) =
	RuleDelegates.setBoolean(this, key, value)

@JvmName("getBoolean")
operator fun RuleKey<BooleanRule>.getValue(server: MinecraftServer, property: KProperty<*>): Boolean =
	RuleDelegates.getBoolean(server, this)

@JvmName("setBoolean")
operator fun RuleKey<BooleanRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: Boolean) =
	RuleDelegates.setBoolean(server, this, value)

@JvmName("getInt")
operator fun MinecraftServer.get(key: RuleKey<IntRule>): Int =
	RuleDelegates.getInt(this, key)

@JvmName("setInt")
operator fun MinecraftServer.set(key: RuleKey<IntRule>, value: Int) =
	RuleDelegates.setInt(this, key, value)

@JvmName("getInt")
operator fun RuleKey<IntRule>.getValue(server: MinecraftServer, property: KProperty<*>): Int =
	RuleDelegates.getInt(server, this)

@JvmName("setInt")
operator fun RuleKey<IntRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: Int) =
	RuleDelegates.setInt(server, this, value)

@Beta
@JvmName("getDouble")
operator fun MinecraftServer.get(key: RuleKey<DoubleRule>): Double =
	RuleDelegates.getDouble(this, key)

@Beta
@JvmName("setDouble")
operator fun MinecraftServer.set(key: RuleKey<DoubleRule>, value: Double) =
	RuleDelegates.setDouble(this, key, value)

@Beta
@JvmName("getDouble")
operator fun RuleKey<DoubleRule>.getValue(server: MinecraftServer, property: KProperty<*>): Double =
	RuleDelegates.getDouble(server, this)

@Beta
@JvmName("setDouble")
operator fun RuleKey<DoubleRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: Double) =
	RuleDelegates.setDouble(server, this, value)

@Beta
@JvmName("getEnum")
operator fun <E : Enum<E>> MinecraftServer.get(key: RuleKey<EnumRule<E>>): E =
	RuleDelegates.getEnum(this, key)

@Beta
@JvmName("setEnum")
operator fun <E : Enum<E>> MinecraftServer.set(key: RuleKey<EnumRule<E>>, value: E) =
	RuleDelegates.setEnum(this, key, value)

@Beta
@JvmName("getEnum")
operator fun <E : Enum<E>> RuleKey<EnumRule<E>>.getValue(server: MinecraftServer, property: KProperty<*>): E =
	RuleDelegates.getEnum(server, this)

@Beta
@JvmName("setEnum")
operator fun <E : Enum<E>> RuleKey<EnumRule<E>>.setValue(server: MinecraftServer, property: KProperty<*>, value: E) =
	RuleDelegates.setEnum(server, this, value)

@Beta
@JvmName("getFloat")
operator fun MinecraftServer.get(key: RuleKey<FloatRule>): Float =
	RuleDelegates.getFloat(this, key)

@Beta
@JvmName("setFloat")
operator fun MinecraftServer.set(key: RuleKey<FloatRule>, value: Float) =
	RuleDelegates.setFloat(this, key, value)

@Beta
@JvmName("getFloat")
operator fun RuleKey<FloatRule>.getValue(server: MinecraftServer, property: KProperty<*>): Float =
	RuleDelegates.getFloat(server, this)

@Beta
@JvmName("setFloat")
operator fun RuleKey<FloatRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: Float) =
	RuleDelegates.setFloat(server, this, value)

@JvmName("getString")
operator fun MinecraftServer.get(key: RuleKey<StringRule>): String =
	RuleDelegates.getString(this, key)

@Beta
@JvmName("setString")
operator fun MinecraftServer.set(key: RuleKey<StringRule>, value: String) =
	RuleDelegates.setString(this, key, value)

@Beta
@JvmName("getString")
operator fun RuleKey<StringRule>.getValue(server: MinecraftServer, property: KProperty<*>): String =
	RuleDelegates.getString(server, this)

@Beta
@JvmName("setString")
operator fun RuleKey<StringRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: String) =
	RuleDelegates.setString(server, this, value)
