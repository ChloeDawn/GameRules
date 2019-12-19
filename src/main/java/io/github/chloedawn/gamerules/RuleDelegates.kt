/**
 * Rule delegates API for Kotlin library users, to allow for simplified
 * access to game rule values. Example usage:
 *
 * ```
 * import io.github.chloedawn.gamerules.getValue
 * import io.github.chloedawn.gamerules.setValue
 *
 * var GameRules.isFooBar by IS_FOO_BAR
 * var MinecraftServer.isFooBar by IS_FOO_BAR
 * var World.isFooBar by IS_FOO_BAR
 *
 * fun foobar(server: MinecraftServer) {
 *  var isFooBar by server.gameRules[IS_FOO_BAR]
 * }
 * ```
 *
 * @author Chloe Dawn
 * @since 0.1.0
 */

@file:JvmName("RuleDelegates")

package io.github.chloedawn.gamerules

import com.google.common.annotations.Beta
import net.minecraft.server.MinecraftServer
import net.minecraft.world.GameRules
import net.minecraft.world.GameRules.BooleanRule
import net.minecraft.world.GameRules.IntRule
import net.minecraft.world.GameRules.RuleKey
import net.minecraft.world.World
import kotlin.reflect.KProperty

/**
 * Gets the value of the delegate [BooleanRule]
 *
 * @since 0.1.0
 * @see BooleanRule.get
 */
@JvmSynthetic
operator fun BooleanRule.getValue(any: Any?, property: KProperty<*>): Boolean {
  return get()
}

/**
 * Sets the value of the delegate [BooleanRule] to the given [value]
 *
 * @since 0.1.0
 * @see BooleanRule.set
 */
@JvmSynthetic
operator fun BooleanRule.setValue(any: Any?, property: KProperty<*>, value: Boolean) {
  set(value, null)
}

/**
 * Gets the value of the delegate [IntRule]
 *
 * @since 0.1.0
 * @see IntRule.get
 */
@JvmSynthetic
operator fun IntRule.getValue(any: Any?, property: KProperty<*>): Int {
  return get()
}

/**
 * Sets the value of the delegate [IntRule] to the given [value]
 *
 * @since 0.1.0
 * @see IntRule.set
 */
@JvmSynthetic
operator fun IntRule.setValue(any: Any?, property: KProperty<*>, value: Int) {
  Rules.set(this, value, null)
}

/**
 * Gets the value of the delegate [DoubleRule]
 *
 * @since 0.1.0
 * @see DoubleRule.get
 */
@Beta
@JvmSynthetic
operator fun DoubleRule.getValue(any: Any?, property: KProperty<*>): Double {
  return get()
}

/**
 * Sets the value of the delegate [DoubleRule] to the given [value]
 *
 * @since 0.1.0
 * @see DoubleRule.set
 */
@Beta
@JvmSynthetic
operator fun DoubleRule.setValue(any: Any?, property: KProperty<*>, value: Double) {
  set(value, null)
}

/**
 * Gets the value of the delegate [EnumRule]
 *
 * @since 0.1.0
 * @see EnumRule.get
 */
@Beta
@JvmSynthetic
operator fun <E : Enum<E>> EnumRule<E>.getValue(any: Any?, property: KProperty<*>): E {
  return get()
}

/**
 * Sets the value of the delegate [EnumRule] to the given [value]
 *
 * @since 0.1.0
 * @see DoubleRule.set
 */
@Beta
@JvmSynthetic
operator fun <E : Enum<E>> EnumRule<E>.setValue(any: Any?, property: KProperty<*>, value: E) {
  set(value, null)
}

/**
 * Gets the value of the delegate [FloatRule]
 *
 * @since 0.1.0
 * @see FloatRule.get
 */
@Beta
@JvmSynthetic
operator fun FloatRule.getValue(any: Any?, property: KProperty<*>): Float {
  return get()
}

/**
 * Sets the value of the delegate [FloatRule] to the given [value]
 *
 * @since 0.1.0
 * @see FloatRule.set
 */
@Beta
@JvmSynthetic
operator fun FloatRule.setValue(any: Any?, property: KProperty<*>, value: Float) {
  set(value, null)
}

/**
 * Gets the value of the delegate [StringRule]
 *
 * @since 0.1.0
 * @see StringRule.get
 */
@Beta
@JvmSynthetic
operator fun StringRule.getValue(any: Any?, property: KProperty<*>): String {
  return get()
}

/**
 * Sets the value of the delegate [StringRule] to the given [value]
 *
 * @since 0.1.0
 * @see StringRule.set
 */
@Beta
@JvmSynthetic
operator fun StringRule.setValue(any: Any?, property: KProperty<*>, value: String) {
  set(value, null)
}

@JvmSynthetic
operator fun RuleKey<BooleanRule>.getValue(rules: GameRules, property: KProperty<*>): Boolean {
  return rules.getBoolean(this)
}

@JvmSynthetic
operator fun RuleKey<BooleanRule>.getValue(server: MinecraftServer, property: KProperty<*>): Boolean {
  return Rules.getBoolean(server, this)
}

@JvmSynthetic
operator fun RuleKey<BooleanRule>.getValue(world: World, property: KProperty<*>): Boolean {
  return Rules.getBoolean(world, this)
}

@JvmSynthetic
operator fun RuleKey<BooleanRule>.setValue(rules: GameRules, property: KProperty<*>, value: Boolean) {
  Rules.setBoolean(rules, this, value)
}

@JvmSynthetic
operator fun RuleKey<BooleanRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: Boolean) {
  Rules.setBoolean(server, this, value)
}

@JvmSynthetic
operator fun RuleKey<BooleanRule>.setValue(world: World, property: KProperty<*>, value: Boolean) {
  Rules.setBoolean(world, this, value)
}

@JvmSynthetic
operator fun RuleKey<IntRule>.getValue(rules: GameRules, property: KProperty<*>): Int {
  return rules.getInt(this)
}

@JvmSynthetic
operator fun RuleKey<IntRule>.getValue(server: MinecraftServer, property: KProperty<*>): Int {
  return Rules.getInt(server, this)
}

@JvmSynthetic
operator fun RuleKey<IntRule>.getValue(world: World, property: KProperty<*>): Int {
  return Rules.getInt(world, this)
}

@JvmSynthetic
operator fun RuleKey<IntRule>.setValue(rules: GameRules, property: KProperty<*>, value: Int) {
  Rules.setInt(rules, this, value)
}

@JvmSynthetic
operator fun RuleKey<IntRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: Int) {
  Rules.setInt(server, this, value)
}

@JvmSynthetic
operator fun RuleKey<IntRule>.setValue(world: World, property: KProperty<*>, value: Int) {
  Rules.setInt(world, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<DoubleRule>.getValue(rules: GameRules, property: KProperty<*>): Double {
  return Rules.getDouble(rules, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<DoubleRule>.getValue(server: MinecraftServer, property: KProperty<*>): Double {
  return Rules.getDouble(server, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<DoubleRule>.getValue(world: World, property: KProperty<*>): Double {
  return Rules.getDouble(world, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<DoubleRule>.setValue(rules: GameRules, property: KProperty<*>, value: Double) {
  Rules.setDouble(rules, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<DoubleRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: Double) {
  Rules.setDouble(server, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<DoubleRule>.setValue(world: World, property: KProperty<*>, value: Double) {
  Rules.setDouble(world, this, value)
}

@Beta
@JvmSynthetic
operator fun <E : Enum<E>> RuleKey<EnumRule<E>>.getValue(rules: GameRules, property: KProperty<*>): E {
  return Rules.getEnum(rules, this)
}

@Beta
@JvmSynthetic
operator fun <E : Enum<E>> RuleKey<EnumRule<E>>.getValue(server: MinecraftServer, property: KProperty<*>): E {
  return Rules.getEnum(server, this)
}

@Beta
@JvmSynthetic
operator fun <E : Enum<E>> RuleKey<EnumRule<E>>.getValue(world: World, property: KProperty<*>): E {
  return Rules.getEnum(world, this)
}

@Beta
@JvmSynthetic
operator fun <E : Enum<E>> RuleKey<EnumRule<E>>.setValue(rules: GameRules, property: KProperty<*>, value: E) {
  Rules.setEnum(rules, this, value)
}

@Beta
@JvmSynthetic
operator fun <E : Enum<E>> RuleKey<EnumRule<E>>.setValue(server: MinecraftServer, property: KProperty<*>, value: E) {
  Rules.setEnum(server, this, value)
}

@Beta
@JvmSynthetic
operator fun <E : Enum<E>> RuleKey<EnumRule<E>>.setValue(world: World, property: KProperty<*>, value: E) {
  Rules.setEnum(world, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<FloatRule>.getValue(rules: GameRules, property: KProperty<*>): Float {
  return Rules.getFloat(rules, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<FloatRule>.getValue(server: MinecraftServer, property: KProperty<*>): Float {
  return Rules.getFloat(server, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<FloatRule>.getValue(world: World, property: KProperty<*>): Float {
  return Rules.getFloat(world, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<FloatRule>.setValue(rules: GameRules, property: KProperty<*>, value: Float) {
  Rules.setFloat(rules, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<FloatRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: Float) {
  Rules.setFloat(server, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<FloatRule>.setValue(world: World, property: KProperty<*>, value: Float) {
  Rules.setFloat(world, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<StringRule>.getValue(rules: GameRules, property: KProperty<*>): String {
  return Rules.getString(rules, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<StringRule>.getValue(server: MinecraftServer, property: KProperty<*>): String {
  return Rules.getString(server, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<StringRule>.getValue(world: World, property: KProperty<*>): String {
  return Rules.getString(world, this)
}

@Beta
@JvmSynthetic
operator fun RuleKey<StringRule>.setValue(rules: GameRules, property: KProperty<*>, value: String) {
  Rules.setString(rules, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<StringRule>.setValue(server: MinecraftServer, property: KProperty<*>, value: String) {
  Rules.setString(server, this, value)
}

@Beta
@JvmSynthetic
operator fun RuleKey<StringRule>.setValue(world: World, property: KProperty<*>, value: String) {
  Rules.setString(world, this, value)
}
