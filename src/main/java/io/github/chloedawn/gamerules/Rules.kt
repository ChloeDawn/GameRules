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
import net.minecraft.world.GameRules.Rule
import net.minecraft.world.GameRules.RuleKey
import net.minecraft.world.World
import org.jetbrains.annotations.ApiStatus

/**
 * Adds the given [changeCallback] to the registered rule type of the receiver `key`
 *
 * @receiver The rule key to add a callback for
 * @param changeCallback The callback to be added
 * @throws NoSuchRuleException If no rule exists for the receiver `key`
 * @since 0.1.0
 */
fun <T : Rule<T>> RuleKey<T>.observe(changeCallback: (server: MinecraftServer, rule: T) -> Unit) {
  Rules.observe(this, changeCallback)
}

/**
 * Creates and registers a new [BooleanRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun booleanRuleOf(name: String, initialValue: Boolean, changeCallback: (server: MinecraftServer, rule: BooleanRule) -> Unit): RuleKey<BooleanRule> {
  return Rules.createBooleanRule(name, initialValue, changeCallback)
}

/**
 * Creates and registers a new [BooleanRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun booleanRuleOf(name: String, initialValue: Boolean): RuleKey<BooleanRule> {
  return Rules.createBooleanRule(name, initialValue)
}

/**
 * Creates and registers a new [BooleanRule] by the given [name], with an initial value of `false`
 *
 * @param name The unique name of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun booleanRuleOf(name: String, changeCallback: (server: MinecraftServer, rule: BooleanRule) -> Unit): RuleKey<BooleanRule> {
  return Rules.createBooleanRule(name, changeCallback)
}

/**
 * Creates and registers a new [BooleanRule] by the given [name], with an initial value of `false`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun booleanRuleOf(name: String): RuleKey<BooleanRule> {
  return Rules.createBooleanRule(name)
}

/**
 * Creates and registers a new [IntRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun intRuleOf(name: String, initialValue: Int, changeCallback: (server: MinecraftServer, rule: IntRule) -> Unit): RuleKey<IntRule> {
  return Rules.createIntRule(name, initialValue, changeCallback)
}

/**
 * Creates and registers a new [IntRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun intRuleOf(name: String, initialValue: Int): RuleKey<IntRule> {
  return Rules.createIntRule(name, initialValue)
}

/**
 * Creates and registers a new [IntRule] by the given [name], with an initial value of `0`
 *
 * @param name The unique name of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun intRuleOf(name: String, changeCallback: (server: MinecraftServer, rule: IntRule) -> Unit): RuleKey<IntRule> {
  return Rules.createIntRule(name, changeCallback)
}

/**
 * Creates and registers a new [IntRule] by the given [name], with an initial value of `0`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun intRuleOf(name: String): RuleKey<IntRule> {
  return Rules.createIntRule(name)
}

/**
 * Creates and registers a new [DoubleRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun doubleRuleOf(name: String, initialValue: Double, changeCallback: (server: MinecraftServer, rule: DoubleRule) -> Unit): RuleKey<DoubleRule> {
  return Rules.createDoubleRule(name, initialValue, changeCallback)
}

/**
 * Creates and registers a new [DoubleRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun doubleRuleOf(name: String, initialValue: Double): RuleKey<DoubleRule> {
  return Rules.createDoubleRule(name, initialValue)
}

/**
 * Creates and registers a new [DoubleRule] by the given [name], with an initial value of `0.0`
 *
 * @param name The unique name of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun doubleRuleOf(name: String, changeCallback: (server: MinecraftServer, rule: DoubleRule) -> Unit): RuleKey<DoubleRule> {
  return Rules.createDoubleRule(name, changeCallback)
}

/**
 * Creates and registers a new [DoubleRule] by the given [name], with an initial value of `0.0`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun doubleRuleOf(name: String): RuleKey<DoubleRule> {
  return Rules.createDoubleRule(name)
}

/**
 * Creates and registers a new {@link EnumRule<E>} by the given [name], for the given [valueType],
 * with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param valueType The enum's type
 * @param initialValue The initial value of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun <E : Enum<E>> enumRuleOf(name: String, valueType: Class<E>, initialValue: E, changeCallback: (server: MinecraftServer, rule: EnumRule<E>) -> Unit): RuleKey<EnumRule<E>> {
  return Rules.createEnumRule(name, valueType, initialValue, changeCallback)
}

/**
 * Creates and registers a new {@link EnumRule<E>} by the given [name], for the reified value type,
 * with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param E The enum's reified type
 * @param initialValue The initial value of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
inline fun <reified E : Enum<E>> enumRuleOf(name: String, initialValue: E, noinline changeCallback: (server: MinecraftServer, rule: EnumRule<E>) -> Unit): RuleKey<EnumRule<E>> {
  return enumRuleOf(name, E::class.java, initialValue, changeCallback)
}

/**
 * Creates and registers a new {@link EnumRule<E>} by the given [name], for the given [valueType],
 * with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param valueType The enum's type
 * @param initialValue The initial value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun <E : Enum<E>> enumRuleOf(name: String, valueType: Class<E>, initialValue: E): RuleKey<EnumRule<E>> {
  return Rules.createEnumRule(name, valueType, initialValue)
}

/**
 * Creates and registers a new {@link EnumRule<E>} by the given [name], for the reified value type,
 * with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param E The enum's reified type
 * @param initialValue The initial value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
inline fun <reified E : Enum<E>> enumRuleOf(name: String, initialValue: E): RuleKey<EnumRule<E>> {
  return enumRuleOf(name, E::class.java, initialValue)
}

/**
 * Creates and registers a new {@link EnumRule<E>} by the given [name], for the given [valueType],
 * with an initial value of {@link EnumRule#initialValue}
 *
 * @param name The unique name of the rule
 * @param valueType The enum's type
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun <E : Enum<E>> enumRuleOf(name: String, valueType: Class<E>, changeCallback: (server: MinecraftServer, rule: EnumRule<E>) -> Unit): RuleKey<EnumRule<E>> {
  return Rules.createEnumRule(name, valueType, changeCallback)
}

/**
 * Creates and registers a new {@link EnumRule<E>} by the given [name], for the reified value type,
 * with an initial value of {@link EnumRule#initialValue}
 *
 * @param name The unique name of the rule
 * @param E The enum's reified type
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
inline fun <reified E : Enum<E>> enumRuleOf(name: String, noinline changeCallback: (server: MinecraftServer, rule: EnumRule<E>) -> Unit): RuleKey<EnumRule<E>> {
  return enumRuleOf(name, E::class.java, changeCallback)
}

/**
 * Creates and registers a new {@link EnumRule<E>} by the given [name], for the given [valueType],
 * with an initial value of {@link EnumRule#initialValue}
 *
 * @param name The unique name of the rule
 * @param valueType The enum's type
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun <E : Enum<E>> enumRuleOf(name: String, valueType: Class<E>): RuleKey<EnumRule<E>> {
  return Rules.createEnumRule(name, valueType)
}

/**
 * Creates and registers a new {@link EnumRule<E>} by the given [name], for the reified value type,
 * with an initial value of {@link EnumRule#initialValue}
 *
 * @param name The unique name of the rule
 * @param E The enum's reified type
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
inline fun <reified E : Enum<E>> enumRuleOf(name: String): RuleKey<EnumRule<E>> {
  return enumRuleOf(name, E::class.java)
}

/**
 * Creates and registers a new [FloatRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun floatRuleOf(name: String, initialValue: Float, changeCallback: (server: MinecraftServer, rule: FloatRule) -> Unit): RuleKey<FloatRule> {
  return Rules.createFloatRule(name, initialValue, changeCallback)
}

/**
 * Creates and registers a new [FloatRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun floatRuleOf(name: String, initialValue: Float): RuleKey<FloatRule> {
  return Rules.createFloatRule(name, initialValue)
}

/**
 * Creates and registers a new [FloatRule] by the given [name], with an initial value of `0.0F`
 *
 * @param name The unique name of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun floatRuleOf(name: String, changeCallback: (server: MinecraftServer, rule: FloatRule) -> Unit): RuleKey<FloatRule> {
  return Rules.createFloatRule(name, changeCallback)
}

/**
 * Creates and registers a new [FloatRule] by the given [name], with an initial value of `0.0F`
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun floatRuleOf(name: String): RuleKey<FloatRule>? {
  return Rules.createFloatRule(name)
}

/**
 * Creates and registers a new [StringRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun stringRuleOf(name: String, initialValue: String, changeCallback: (server: MinecraftServer, rule: StringRule) -> Unit): RuleKey<StringRule> =
  Rules.createStringRule(name, initialValue, changeCallback)

/**
 * Creates and registers a new [StringRule] by the given [name], with the given [initialValue]
 *
 * @param name The unique name of the rule
 * @param initialValue The initial value of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun stringRuleOf(name: String, initialValue: String): RuleKey<StringRule>? {
  return Rules.createStringRule(name, initialValue)
}

/**
 * Creates and registers a new [StringRule] by the given [name], with an initial value of {@code ""}
 *
 * @param name The unique name of the rule
 * @param changeCallback The callback invoked when the rule is changed
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun stringRuleOf(name: String, changeCallback: (server: MinecraftServer, rule: StringRule) -> Unit): RuleKey<StringRule> {
  return Rules.createStringRule(name, changeCallback)
}

/**
 * Creates and registers a new [StringRule] by the given [name], with an initial value of {@code ""}
 *
 * @param name The unique name of the rule
 * @return A [RuleKey] for querying the rule from a level's [GameRules]
 * @throws IllegalStateException If a rule by the given [name] already exists
 * @since 0.1.0
 */
fun stringRuleOf(name: String): RuleKey<StringRule> {
  return Rules.createStringRule(name)
}

/*
 * Gets the value of the given [BooleanRule] key
 *
 * @receiver The game rules instance
 * @param key The key representing the rule
 * @return The `boolean` value of the rule
 * @since 0.1.0
 */
//fun GameRules.getBoolean(key: RuleKey<BooleanRule>): Boolean {
//  return Rules.getBoolean(this, key)
//}

/**
 * Gets the value of the given [BooleanRule] key
 *
 * @receiver The server containing the game rules
 * @param key The key representing the rule
 * @return The `boolean` value of the rule instance
 * @since 0.1.0
 */
fun MinecraftServer.getBoolean(key: RuleKey<BooleanRule>): Boolean {
  return Rules.getBoolean(this, key)
}

/**
 * Gets the value of the given [BooleanRule] key
 *
 * @receiver The level containing the game rules
 * @param key The key representing the rule
 * @return The `boolean` value of the rule instance
 * @since 0.1.0
 */
fun World.getBoolean(key: RuleKey<BooleanRule>): Boolean {
  return Rules.getBoolean(this, key)
}

/**
 * Sets the value of the given [BooleanRule] key to the given [value]
 *
 * @receiver The game rules instance
 * @param key The rule's key
 * @param value The {@code boolean} value to be set
 * @since 0.1.0
 */
fun GameRules.setBoolean(key: RuleKey<BooleanRule>, value: Boolean) {
  Rules.setBoolean(this, key, value)
}

/**
 * Sets the value of the given [BooleanRule] key to the given [value]
 *
 * @receiver The server containing the game rules
 * @param key The rule's key
 * @param value The {@code boolean} value to be set
 * @since 0.1.0
 */
fun MinecraftServer.setBoolean(key: RuleKey<BooleanRule>, value: Boolean) {
  Rules.setBoolean(this, key, value)
}

/**
 * Sets the value of the given [BooleanRule] key to the given [value]
 *
 * @receiver The level containing the game rules
 * @param key The rule's key
 * @param value The {@code boolean} value to be set
 * @since 0.1.0
 */
fun World.setBoolean(key: RuleKey<BooleanRule>, value: Boolean) {
  Rules.setBoolean(this, key, value)
}

/*
 * Gets the value of the given [IntRule] key
 *
 * @receiver The game rules instance
 * @param key The key representing the rule
 * @return The `int` value of the rule
 * @since 0.1.0
 */
//fun GameRules.getInt(key: RuleKey<IntRule>): Int {
//  return Rules.getInt(this, key)
//}

fun MinecraftServer.getInt(key: RuleKey<IntRule>): Int {
  return Rules.getInt(this, key)
}

fun World.getInt(key: RuleKey<IntRule>): Int {
  return Rules.getInt(this, key)
}

fun GameRules.setInt(key: RuleKey<IntRule>, value: Int) {
  Rules.setInt(this, key, value)
}

fun MinecraftServer.setInt(key: RuleKey<IntRule>, value: Int) {
  Rules.setInt(this, key, value)
}

fun World.setInt(key: RuleKey<IntRule>, value: Int) {
  Rules.setInt(this, key, value)
}

fun GameRules.getDouble(key: RuleKey<DoubleRule>): Double {
  return Rules.getDouble(this, key)
}

fun MinecraftServer.getDouble(key: RuleKey<DoubleRule>): Double {
  return Rules.getDouble(this, key)
}

fun World.getDouble(key: RuleKey<DoubleRule>): Double {
  return Rules.getDouble(this, key)
}

fun GameRules.setDouble(key: RuleKey<DoubleRule>, value: Double) {
  Rules.setDouble(this, key, value)
}

fun MinecraftServer.setDouble(key: RuleKey<DoubleRule>, value: Double) {
  Rules.setDouble(this, key, value)
}

fun World.setDouble(key: RuleKey<DoubleRule>, value: Double) {
  Rules.setDouble(this, key, value)
}

fun <E : Enum<E>> GameRules.getEnum(key: RuleKey<EnumRule<E>>): E {
  return Rules.getEnum(this, key)
}

fun <E : Enum<E>> MinecraftServer.getEnum(key: RuleKey<EnumRule<E>>): E {
  return Rules.getEnum(this, key)
}

fun <E : Enum<E>> World.getEnum(key: RuleKey<EnumRule<E>>): E {
  return Rules.getEnum(this, key)
}

fun <E : Enum<E>> GameRules.setEnum(key: RuleKey<EnumRule<E>>, value: E) {
  Rules.setEnum(this, key, value)
}

fun <E : Enum<E>> MinecraftServer.setEnum(key: RuleKey<EnumRule<E>>, value: E) {
  Rules.setEnum(this, key, value)
}

fun <E : Enum<E>> World.setEnum(key: RuleKey<EnumRule<E>>, value: E) {
  Rules.setEnum(this, key, value)
}

fun GameRules.getFloat(key: RuleKey<FloatRule>): Float {
  return Rules.getFloat(this, key)
}

fun MinecraftServer.getFloat(key: RuleKey<FloatRule>): Float {
  return Rules.getFloat(this, key)
}

fun World.getFloat(key: RuleKey<FloatRule>): Float {
  return Rules.getFloat(this, key)
}

fun GameRules.setFloat(key: RuleKey<FloatRule>, value: Float) {
  Rules.setFloat(this, key, value)
}

fun MinecraftServer.setFloat(key: RuleKey<FloatRule>, value: Float) {
  Rules.setFloat(this, key, value)
}

fun World.setFloat(key: RuleKey<FloatRule>, value: Float) {
  Rules.setFloat(this, key, value)
}

fun GameRules.getString(key: RuleKey<StringRule>): String {
  return Rules.getString(this, key)
}

fun MinecraftServer.getString(key: RuleKey<StringRule>): String {
  return Rules.getString(this, key)
}

fun World.getString(key: RuleKey<StringRule>): String {
  return Rules.getString(this, key)
}

fun GameRules.setString(key: RuleKey<StringRule>, value: String) {
  Rules.setString(this, key, value)
}

fun MinecraftServer.setString(key: RuleKey<StringRule>, value: String) {
  Rules.setString(this, key, value)
}

fun World.setString(key: RuleKey<StringRule>, value: String) {
  Rules.setString(this, key, value)
}

/**
 * Sets the value of the receiver [IntRule] to the given [value], and invokes
 * the callback method declared in [Rule]. This method is effectively a placeholder
 * for an absent/stripped method in the actual class. This method is semantically
 * equivalent to [BooleanRule.set]
 *
 * This extension function is [ApiStatus.Experimental] as it is not guaranteed to
 * exist in future versions targeting newer Minecraft versions, dependant on
 * whether Mojang introduce the method themselves in the [IntRule] class. If you
 * have concerns about stability, please consider using the Java API method instead
 *
 * @receiver The rule
 * @param value The value to be set
 * @param server The server to call back to
 * @since 0.1.0
 */
@Beta
@ApiStatus.Experimental
fun IntRule.set(value: Int, server: MinecraftServer?) {
  Rules.set(this, value, server)
}
