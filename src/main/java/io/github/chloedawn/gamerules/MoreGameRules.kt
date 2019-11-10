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
import net.minecraft.world.GameRules
import net.minecraft.world.GameRules.BooleanRule
import net.minecraft.world.GameRules.IntRule
import net.minecraft.world.GameRules.RuleKey
import java.util.function.BiConsumer

/**
 * Adds the given [notifier] to the registered rule type of the receiver `key`
 *
 * @receiver The rule key to add a notifier for
 * @param notifier The additional notifier to be added
 * @throws IllegalArgumentException If the `key` is not registered
 */
fun <T : GameRules.Rule<T>> RuleKey<T>.addNotifier(notifier: (MinecraftServer?, T) -> Unit) =
	MoreGameRules.addNotifier(this, notifier)

/**
 * Creates and registers a new [BooleanRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
fun booleanRuleOf(name: String, defaultValue: Boolean, notifier: (MinecraftServer?, BooleanRule) -> Unit): RuleKey<BooleanRule> =
	MoreGameRules.makeBooleanRule(name, defaultValue, notifier)

/**
 * Creates and registers a new [BooleanRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
fun booleanRuleOf(name: String, defaultValue: Boolean): RuleKey<BooleanRule> =
	MoreGameRules.makeBooleanRule(name, defaultValue)

/**
 * Creates and registers a new [BooleanRule] by the given [name], defaulting to a value of `false`
 *
 * @param name The unique name of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
fun booleanRuleOf(name: String, notifier: (MinecraftServer?, BooleanRule) -> Unit): RuleKey<BooleanRule> =
	MoreGameRules.makeBooleanRule(name, notifier)

/**
 * Creates and registers a new [BooleanRule] by the given [name], defaulting to a value of `false`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
fun booleanRuleOf(name: String): RuleKey<BooleanRule> =
	MoreGameRules.makeBooleanRule(name)

/**
 * Creates and registers a new [IntRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
fun intRuleOf(name: String, defaultValue: Int, notifier: (MinecraftServer?, IntRule) -> Unit): RuleKey<IntRule> =
	MoreGameRules.makeIntRule(name, defaultValue, notifier)

/**
 * Creates and registers a new [IntRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
fun intRuleOf(name: String, defaultValue: Int): RuleKey<IntRule> =
	MoreGameRules.makeIntRule(name, defaultValue)

/**
 * Creates and registers a new [IntRule] by the given [name], defaulting to a value of `0`
 *
 * @param name The unique name of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
fun intRuleOf(name: String, notifier: (MinecraftServer?, IntRule) -> Unit): RuleKey<IntRule> =
	MoreGameRules.makeIntRule(name, notifier)

/**
 * Creates and registers a new [IntRule] by the given [name], defaulting to a value of `0`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
fun intRuleOf(name: String): RuleKey<IntRule> =
	MoreGameRules.makeIntRule(name)

/**
 * Creates and registers a new [DoubleRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun doubleRuleOf(name: String, defaultValue: Double, notifier: (MinecraftServer?, DoubleRule) -> Unit): RuleKey<DoubleRule> =
	MoreGameRules.makeDoubleRule(name, defaultValue, notifier)

/**
 * Creates and registers a new [DoubleRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun doubleRuleOf(name: String, defaultValue: Double): RuleKey<DoubleRule> =
	MoreGameRules.makeDoubleRule(name, defaultValue)

/**
 * Creates and registers a new [DoubleRule] by the given [name], defaulting to a value of `0.0`
 *
 * @param name The unique name of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun doubleRuleOf(name: String, notifier: (MinecraftServer?, DoubleRule) -> Unit): RuleKey<DoubleRule> =
	MoreGameRules.makeDoubleRule(name, notifier)

/**
 * Creates and registers a new [DoubleRule] by the given [name], defaulting to a value of `0.0`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun doubleRuleOf(name: String): RuleKey<DoubleRule> =
	MoreGameRules.makeDoubleRule(name)

/**
 * Creates and registers a new [EnumRule] by the given [name], for the given [valueType],
 * defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param valueType The enum's type
 * @param defaultValue The default value of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun <E : Enum<E>> enumRuleOf(name: String, valueType: Class<E>, defaultValue: E, notifier: (MinecraftServer?, EnumRule<E>) -> Unit): RuleKey<EnumRule<E>> =
	MoreGameRules.makeEnumRule(name, valueType, defaultValue, notifier)

inline fun <reified E : Enum<E>> enumRuleOf(name: String, defaultValue: E, noinline notifier: (MinecraftServer?, EnumRule<E>) -> Unit) =
	enumRuleOf(name, E::class.java, defaultValue, notifier)

/**
 * Creates and registers a new [EnumRule] by the given [name], for the given [valueType],
 * defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param valueType The enum's type
 * @param defaultValue The default value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun <E : Enum<E>> enumRuleOf(name: String, valueType: Class<E>, defaultValue: E): RuleKey<EnumRule<E>> =
	MoreGameRules.makeEnumRule(name, valueType, defaultValue)

inline fun <reified E : Enum<E>> enumRuleOf(name: String, defaultValue: E) =
	enumRuleOf(name, E::class.java, defaultValue)

/**
 * Creates and registers a new [EnumRule] by the given [name], for the given [valueType],
 * defaulting to a value of [EnumRule.defaultValue]
 *
 * @param name The unique name of the rule
 * @param valueType The enum's type
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun <E : Enum<E>> enumRuleOf(name: String, valueType: Class<E>, notifier: (MinecraftServer?, EnumRule<E>) -> Unit): RuleKey<EnumRule<E>> =
	MoreGameRules.makeEnumRule(name, valueType, notifier)

inline fun <reified E : Enum<E>> enumRuleOf(name: String, noinline notifier: (MinecraftServer?, EnumRule<E>) -> Unit) =
	enumRuleOf(name, E::class.java, notifier)

/**
 * Creates and registers a new [EnumRule] by the given [name], for the given [valueType],
 * defaulting to a value of [EnumRule.defaultValue]
 *
 * @param name The unique name of the rule
 * @param valueType The enum's type
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun <E : Enum<E>> enumRuleOf(name: String, valueType: Class<E>): RuleKey<EnumRule<E>> =
	MoreGameRules.makeEnumRule(name, valueType)

inline fun <reified E : Enum<E>> enumRuleOf(name: String) =
	enumRuleOf(name, E::class.java)

/**
 * Creates and registers a new [FloatRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun floatRuleOf(name: String, defaultValue: Float, notifier: BiConsumer<MinecraftServer, FloatRule>): RuleKey<FloatRule> =
	MoreGameRules.makeFloatRule(name, defaultValue, notifier)

/**
 * Creates and registers a new [FloatRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun floatRuleOf(name: String, defaultValue: Float): RuleKey<FloatRule> =
	MoreGameRules.makeFloatRule(name, defaultValue)

/**
 * Creates and registers a new [FloatRule] by the given [name], defaulting to a value of `0.0F`
 *
 * @param name The unique name of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun floatRuleOf(name: String, notifier: BiConsumer<MinecraftServer, FloatRule>): RuleKey<FloatRule> =
	MoreGameRules.makeFloatRule(name, notifier)

/**
 * Creates and registers a new [FloatRule] by the given [name], defaulting to a value of `0.0F`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun floatRuleOf(name: String): RuleKey<FloatRule> =
	MoreGameRules.makeFloatRule(name)

/**
 * Creates and registers a new [StringRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun stringRuleOf(name: String, defaultValue: String, notifier: BiConsumer<MinecraftServer, StringRule>): RuleKey<StringRule> =
	MoreGameRules.makeStringRule(name, defaultValue, notifier)

/**
 * Creates and registers a new [StringRule] by the given [name], defaulting to the given [defaultValue]
 *
 * @param name The unique name of the rule
 * @param defaultValue The default value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun stringRuleOf(name: String, defaultValue: String): RuleKey<StringRule> =
	MoreGameRules.makeStringRule(name, defaultValue)

/**
 * Creates and registers a new [StringRule] by the given [name], defaulting to a value of `""`
 *
 * @param name The unique name of the rule
 * @param notifier The notifier callback when the rule is updated by the server
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun stringRuleOf(name: String, notifier: BiConsumer<MinecraftServer, StringRule>): RuleKey<StringRule> =
	MoreGameRules.makeStringRule(name, notifier)

/**
 * Creates and registers a new [StringRule] by the given [name], defaulting to a value of `""`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 */
@Beta
fun stringRuleOf(name: String): RuleKey<StringRule> =
	MoreGameRules.makeStringRule(name)
