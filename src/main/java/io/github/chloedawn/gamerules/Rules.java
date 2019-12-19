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

import com.google.common.annotations.Beta;
import com.google.common.collect.MoreCollectors;
import com.mojang.brigadier.arguments.ArgumentType;
import io.github.chloedawn.gamerules.mixin.access.BooleanRuleAccessor;
import io.github.chloedawn.gamerules.mixin.access.GameRulesAccessor;
import io.github.chloedawn.gamerules.mixin.access.IntRuleAccessor;
import io.github.chloedawn.gamerules.mixin.access.RuleAccessor;
import io.github.chloedawn.gamerules.mixin.access.RuleTypeAccessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.IntRule;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleKey;
import net.minecraft.world.GameRules.RuleType;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.optional.qual.MaybePresent;
import org.jetbrains.annotations.Contract;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Main library class for creating rule types and manipulating rule values
 *
 * @author Chloe Dawn
 * @since 0.1.0
 */
public final class Rules {
  private static final Logger LOGGER = LogManager.getLogger();

  private Rules() {
  }

  /**
   * Gets the keys of all currently registered rules
   *
   * @return A stream of rule keys
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static Stream<RuleKey<?>> ruleKeys() {
    return Collections.unmodifiableSet(ruleTypes().keySet()).stream();
  }

  /**
   * Searches registered game rules for a rule matching the given {@code name}
   *
   * @param name The name of the game rule
   * @return The rule key given {@code name} if present
   * @since 0.1.0
   */
  public static @MaybePresent Optional<RuleKey<?>> find(final String name) {
    return ruleKeys().filter(k -> name.equals(k.getName())).collect(MoreCollectors.toOptional());
  }

  /**
   * Gets the registered game rule bound to the given {@code name}
   *
   * @param name The name of the game rule
   * @return The rule key for the given {@code name}
   * @throws NoSuchRuleException If no rule exists for the given {@code name}
   * @since 0.1.0
   */
  public static RuleKey<?> get(final String name) {
    return find(name).orElseThrow(() -> new NoSuchRuleException(name));
  }

  /**
   * Searches registered game rules for a rule matching the given {@code name},
   * and casts it to the declared type {@link T}. The cast is unchecked and
   * it is expected that the caller is aware of the actual type.
   *
   * @param name The name of the game rule
   * @return The rule key given {@code name} if present
   * @since 0.1.0
   */
  @SuppressWarnings("unchecked")
  public static <T extends Rule<T>> @MaybePresent Optional<RuleKey<T>> findUnchecked(final String name) {
    return find(name).map(k -> (RuleKey<T>) k);
  }

  /**
   * Gets the registered game rule bound to the given {@code name}
   * and casts it to the declared type {@link T}. The cast is unchecked and
   * it is expected that the caller is aware of the actual type.
   *
   * @param name The name of the game rule
   * @return The rule key for the given {@code name}
   * @throws NoSuchRuleException If no rule exists for the given {@code name}
   * @since 0.1.0
   */
  public static <T extends Rule<T>> RuleKey<T> getUnchecked(final String name) {
    return Rules.<T>findUnchecked(name).orElseThrow(() -> new NoSuchRuleException(name));
  }

  /**
   * Adds the given {@code changeCallback} to the registered rule type of the given {@code key}
   *
   * @param key The rule key to add a callback for
   * @param changeCallback The callback to be added
   * @throws NoSuchRuleException If no rule exists for the given {@code key}
   * @since 0.1.0
   */
  public static <T extends Rule<T>> void observe(final RuleKey<T> key, final BiConsumer<MinecraftServer, T> changeCallback) {
    final @Nullable RuleType<?> type = ruleTypes().get(key);
    if (type == null) {
      throw new NoSuchRuleException(key);
    }
    RuleChangeCallbacks.add(key, type, changeCallback);
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _, _ -> new")
  public static RuleKey<BooleanRule> createBooleanRule(final String name, final boolean initialValue, final BiConsumer<MinecraftServer, BooleanRule> changeCallback) {
    return register(name, createBooleanRule(initialValue, changeCallback));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<BooleanRule> createBooleanRule(final String name, final boolean initialValue) {
    return register(name, createBooleanRule(initialValue, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, with an initial value of {@code false}
   *
   * @param name The unique name of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<BooleanRule> createBooleanRule(final String name, final BiConsumer<MinecraftServer, BooleanRule> changeCallback) {
    return register(name, createBooleanRule(false, changeCallback));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, with an initial value of {@code false}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_ -> new")
  public static RuleKey<BooleanRule> createBooleanRule(final String name) {
    return register(name, createBooleanRule(false, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link IntRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _, _ -> new")
  public static RuleKey<IntRule> createIntRule(final String name, final int initialValue, final BiConsumer<MinecraftServer, IntRule> changeCallback) {
    return register(name, createIntRule(initialValue, changeCallback));
  }

  /**
   * Creates and registers a new {@link IntRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<IntRule> createIntRule(final String name, final int initialValue) {
    return register(name, createIntRule(initialValue, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link IntRule} by the given {@code name}, with an initial value of {@code 0}
   *
   * @param name The unique name of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<IntRule> createIntRule(final String name, final BiConsumer<MinecraftServer, IntRule> changeCallback) {
    return register(name, createIntRule(0, changeCallback));
  }

  /**
   * Creates and registers a new {@link IntRule} by the given {@code name}, with an initial value of {@code 0}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_ -> new")
  public static RuleKey<IntRule> createIntRule(final String name) {
    return register(name, createIntRule(0, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link DoubleRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _, _ -> new")
  public static RuleKey<DoubleRule> createDoubleRule(final String name, final double initialValue, final BiConsumer<MinecraftServer, DoubleRule> changeCallback) {
    return register(name, DoubleRule.create(initialValue, changeCallback));
  }

  /**
   * Creates and registers a new {@link DoubleRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<DoubleRule> createDoubleRule(final String name, final double initialValue) {
    return register(name, DoubleRule.create(initialValue, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link DoubleRule} by the given {@code name}, with an initial value of {@code 0.0}
   *
   * @param name The unique name of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<DoubleRule> createDoubleRule(final String name, final BiConsumer<MinecraftServer, DoubleRule> changeCallback) {
    return register(name, DoubleRule.create(0.0, changeCallback));
  }

  /**
   * Creates and registers a new {@link DoubleRule} by the given {@code name}, with an initial value of {@code 0.0}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_ -> new")
  public static RuleKey<DoubleRule> createDoubleRule(final String name) {
    return register(name, DoubleRule.create(0.0, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link EnumRule<E>} by the given {@code name}, for the given {@code valueType},
   * with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param valueType The enum's type
   * @param initialValue The initial value of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _, _, _ -> new")
  public static <E extends Enum<E>> RuleKey<EnumRule<E>> createEnumRule(final String name, final Class<E> valueType, final E initialValue, final BiConsumer<MinecraftServer, EnumRule<E>> changeCallback) {
    return register(name, EnumRule.create(valueType, initialValue, changeCallback));
  }

  /**
   * Creates and registers a new {@link EnumRule<E>} by the given {@code name}, for the given {@code valueType},
   * with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param valueType The enum's type
   * @param initialValue The initial value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _, _ -> new")
  public static <E extends Enum<E>> RuleKey<EnumRule<E>> createEnumRule(final String name, final Class<E> valueType, final E initialValue) {
    return register(name, EnumRule.create(valueType, initialValue, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link EnumRule<E>} by the given {@code name}, for the given {@code valueType},
   * with an initial value of {@link EnumRule#initialValue}
   *
   * @param name The unique name of the rule
   * @param valueType The enum's type
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _, _ -> new")
  public static <E extends Enum<E>> RuleKey<EnumRule<E>> createEnumRule(final String name, final Class<E> valueType, final BiConsumer<MinecraftServer, EnumRule<E>> changeCallback) {
    final @Nullable E value = EnumRule.initialValue(valueType);
    if (value == null) {
      throw new IllegalArgumentException("No constants in enum " + valueType);
    }
    return register(name, EnumRule.create(valueType, value, changeCallback));
  }

  /**
   * Creates and registers a new {@link EnumRule<E>} by the given {@code name}, for the given {@code valueType},
   * with an initial value of {@link EnumRule#initialValue}
   *
   * @param name The unique name of the rule
   * @param valueType The enum's type
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static <E extends Enum<E>> RuleKey<EnumRule<E>> createEnumRule(final String name, final Class<E> valueType) {
    final @Nullable E value = EnumRule.initialValue(valueType);
    if (value == null) {
      throw new IllegalArgumentException("No constants in enum " + valueType);
    }
    return register(name, EnumRule.create(valueType, value, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link FloatRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _, _ -> new")
  public static RuleKey<FloatRule> createFloatRule(final String name, final float initialValue, final BiConsumer<MinecraftServer, FloatRule> changeCallback) {
    return register(name, FloatRule.create(initialValue, changeCallback));
  }

  /**
   * Creates and registers a new {@link FloatRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<FloatRule> createFloatRule(final String name, final float initialValue) {
    return register(name, FloatRule.create(initialValue, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link FloatRule} by the given {@code name}, with an initial value of {@code 0.0F}
   *
   * @param name The unique name of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<FloatRule> createFloatRule(final String name, final BiConsumer<MinecraftServer, FloatRule> changeCallback) {
    return register(name, FloatRule.create(0.0F, changeCallback));
  }

  /**
   * Creates and registers a new {@link FloatRule} by the given {@code name}, with an initial value of {@code 0.0F}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_ -> new")
  public static RuleKey<FloatRule> createFloatRule(final String name) {
    return register(name, FloatRule.create(0.0F, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link StringRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _, _ -> new")
  public static RuleKey<StringRule> createStringRule(final String name, final String initialValue, final BiConsumer<MinecraftServer, StringRule> changeCallback) {
    return register(name, StringRule.create(initialValue, changeCallback));
  }

  /**
   * Creates and registers a new {@link StringRule} by the given {@code name}, with the given {@code initialValue}
   *
   * @param name The unique name of the rule
   * @param initialValue The initial value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<StringRule> createStringRule(final String name, final String initialValue) {
    return register(name, StringRule.create(initialValue, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link StringRule} by the given {@code name}, with an initial value of {@code ""}
   *
   * @param name The unique name of the rule
   * @param changeCallback The callback invoked when the rule is changed
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_, _ -> new")
  public static RuleKey<StringRule> createStringRule(final String name, final BiConsumer<MinecraftServer, StringRule> changeCallback) {
    return register(name, StringRule.create("", changeCallback));
  }

  /**
   * Creates and registers a new {@link StringRule} by the given {@code name}, with an initial value of {@code ""}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   * @since 0.1.0
   */
  @Contract("_ -> new")
  public static RuleKey<StringRule> createStringRule(final String name) {
    return register(name, StringRule.create("", (server, rule) -> {}));
  }

  /*
   * Gets the value of the given {@link BooleanRule} key
   *
   * @param rules The game rules instance
   * @param key The key representing the rule
   * @return The {@code boolean} value of the rule
   * @since 0.1.0
   */
  //@Contract(pure = true)
  //public static boolean getBoolean(final GameRules rules, final RuleKey<BooleanRule> key) {
  //	return rules.getBoolean(key);
  //}

  /**
   * Gets the value of the given {@link BooleanRule} key
   *
   * @param server The server containing the game rules
   * @param key The key representing the rule
   * @return The {@code boolean} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static boolean getBoolean(final MinecraftServer server, final RuleKey<BooleanRule> key) {
    return server.getGameRules().get(key).get();
  }

  /**
   * Gets the value of the given {@link BooleanRule} key
   *
   * @param level The level containing the game rules
   * @param key The key representing the rule
   * @return The {@code boolean} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static boolean getBoolean(final World level, final RuleKey<BooleanRule> key) {
    return level.getGameRules().get(key).get();
  }

  /**
   * Sets the value of the given {@link BooleanRule} key to the given {@code value}
   *
   * @param rules The game rules instance
   * @param key The rule's key
   * @param value The {@code boolean} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setBoolean(final GameRules rules, final RuleKey<BooleanRule> key, final boolean value) {
    rules.get(key).set(value, null);
  }

  /**
   * Sets the value of the given {@link BooleanRule} key to the given {@code value}
   *
   * @param server The server containing the game rules
   * @param key The rule's key
   * @param value The {@code boolean} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setBoolean(final MinecraftServer server, final RuleKey<BooleanRule> key, final boolean value) {
    server.getGameRules().get(key).set(value, server);
  }

  /**
   * Sets the value of the given {@link BooleanRule} key to the given {@code value}
   *
   * @param level The level containing the game rules
   * @param key The rule's key
   * @param value The {@code boolean} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setBoolean(final World level, final RuleKey<BooleanRule> key, final boolean value) {
    level.getGameRules().get(key).set(value, level.getServer());
  }

  /*
   * Gets the value of the given {@link IntRule} key
   *
   * @param rules The game rules instance
   * @param key The key representing the rule
   * @return The {@code int} value of the rule
   * @since 0.1.0
   */
  //@Contract(pure = true)
  //public static int getInt(final GameRules rules, final RuleKey<IntRule> key) {
  //	return rules.getInt(key);
  //}

  /**
   * Gets the value of the given {@link IntRule} key
   *
   * @param server The server containing the game rules
   * @param key The key representing the rule
   * @return The {@code int} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static int getInt(final MinecraftServer server, final RuleKey<IntRule> key) {
    return server.getGameRules().get(key).get();
  }

  /**
   * Gets the value of the given {@link IntRule} key
   *
   * @param level The level containing the game rules
   * @param key The key representing the rule
   * @return The {@code int} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static int getInt(final World level, final RuleKey<IntRule> key) {
    return level.getGameRules().get(key).get();
  }

  /**
   * Sets the value of the given {@link IntRule} key to the given {@code value}
   *
   * @param rules The game rules instance
   * @param key The rule's key
   * @param value The {@code int} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setInt(final GameRules rules, final RuleKey<IntRule> key, final int value) {
    set(rules.get(key), value, null);
  }

  /**
   * Sets the value of the given {@link IntRule} key to the given {@code value}
   *
   * @param server The server containing the game rules
   * @param key The rule's key
   * @param value The {@code int} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setInt(final MinecraftServer server, final RuleKey<IntRule> key, final int value) {
    set(server.getGameRules().get(key), value, server);
  }

  /**
   * Sets the value of the given {@link IntRule} key to the given {@code value}
   *
   * @param level The level containing the game rules
   * @param key The rule's key
   * @param value The {@code int} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setInt(final World level, final RuleKey<IntRule> key, final int value) {
    set(level.getGameRules().get(key), value, level.getServer());
  }

  /**
   * Gets the value of the given {@link DoubleRule} key
   *
   * @param rules The game rules instance
   * @param key The key representing the rule
   * @return The {@code double} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static double getDouble(final GameRules rules, final RuleKey<DoubleRule> key) {
    return rules.get(key).get();
  }

  /**
   * Gets the value of the given {@link DoubleRule} key
   *
   * @param server The server containing the game rules
   * @param key The key representing the rule
   * @return The {@code double} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static double getDouble(final MinecraftServer server, final RuleKey<DoubleRule> key) {
    return server.getGameRules().get(key).get();
  }

  /**
   * Gets the value of the given {@link DoubleRule} key
   *
   * @param level The level containing the game rules
   * @param key The key representing the rule
   * @return The {@code double} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static double getDouble(final World level, final RuleKey<DoubleRule> key) {
    return level.getGameRules().get(key).get();
  }

  /**
   * Sets the value of the given {@link DoubleRule} key to the given {@code value}
   *
   * @param rules The game rules instance
   * @param key The rule's key
   * @param value The {@code double} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setDouble(final GameRules rules, final RuleKey<DoubleRule> key, final double value) {
    rules.get(key).set(value, null);
  }

  /**
   * Sets the value of the given {@link DoubleRule} key to the given {@code value}
   *
   * @param server The server containing the game rules
   * @param key The rule's key
   * @param value The {@code int} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setDouble(final MinecraftServer server, final RuleKey<DoubleRule> key, final double value) {
    server.getGameRules().get(key).set(value, server);
  }

  /**
   * Sets the value of the given {@link DoubleRule} key to the given {@code value}
   *
   * @param level The level containing the game rules
   * @param key The rule's key
   * @param value The {@code double} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setDouble(final World level, final RuleKey<DoubleRule> key, final double value) {
    level.getGameRules().get(key).set(value, level.getServer());
  }

  /**
   * Gets the value of the given {@link EnumRule} key
   *
   * @param rules The game rules instance
   * @param key The key representing the rule
   * @return The {@link E} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static <E extends Enum<E>> E getEnum(final GameRules rules, final RuleKey<EnumRule<E>> key) {
    return rules.get(key).get();
  }

  /**
   * Gets the value of the given {@link EnumRule} key
   *
   * @param server The server containing the game rules
   * @param key The key representing the rule
   * @return The {@link E} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static <E extends Enum<E>> E getEnum(final MinecraftServer server, final RuleKey<EnumRule<E>> key) {
    return server.getGameRules().get(key).get();
  }

  /**
   * Gets the value of the given {@link EnumRule} key
   *
   * @param level The level containing the game rules
   * @param key The key representing the rule
   * @return The {@link E} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static <E extends Enum<E>> E getEnum(final World level, final RuleKey<EnumRule<E>> key) {
    return level.getGameRules().get(key).get();
  }

  /**
   * Sets the value of the given {@link EnumRule} key to the given {@code value}
   *
   * @param rules The game rules instance
   * @param key The rule's key
   * @param value The {@link E} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static <E extends Enum<E>> void setEnum(final GameRules rules, final RuleKey<EnumRule<E>> key, final E value) {
    rules.get(key).set(value, null);
  }

  /**
   * Sets the value of the given {@link EnumRule} key to the given {@code value}
   *
   * @param server The server containing the game rules
   * @param key The rule's key
   * @param value The {@link E} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static <E extends Enum<E>> void setEnum(final MinecraftServer server, final RuleKey<EnumRule<E>> key, final E value) {
    server.getGameRules().get(key).set(value, server);
  }

  /**
   * Sets the value of the given {@link EnumRule} key to the given {@code value}
   *
   * @param level The level containing the game rules
   * @param key The rule's key
   * @param value The {@link E} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static <E extends Enum<E>> void setEnum(final World level, final RuleKey<EnumRule<E>> key, final E value) {
    level.getGameRules().get(key).set(value, level.getServer());
  }

  /**
   * Gets the value of the given {@link FloatRule} key
   *
   * @param rules The game rules instance
   * @param key The key representing the rule
   * @return The {@code float} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static float getFloat(final GameRules rules, final RuleKey<FloatRule> key) {
    return rules.get(key).get();
  }

  /**
   * Gets the value of the given {@link FloatRule} key
   *
   * @param server The server containing the game rules
   * @param key The key representing the rule
   * @return The {@code float} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static float getFloat(final MinecraftServer server, final RuleKey<FloatRule> key) {
    return server.getGameRules().get(key).get();
  }

  /**
   * Gets the value of the given {@link FloatRule} key
   *
   * @param level The level containing the game rules
   * @param key The key representing the rule
   * @return The {@code float} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static float getFloat(final World level, final RuleKey<FloatRule> key) {
    return level.getGameRules().get(key).get();
  }

  /**
   * Sets the value of the given {@link FloatRule} key to the given {@code value}
   *
   * @param rules The game rules instance
   * @param key The rule's key
   * @param value The {@code float} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setFloat(final GameRules rules, final RuleKey<FloatRule> key, final float value) {
    rules.get(key).set(value, null);
  }

  /**
   * Sets the value of the given {@link FloatRule} key to the given {@code value}
   *
   * @param server The server containing the game rules
   * @param key The rule's key
   * @param value The {@code float} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setFloat(final MinecraftServer server, final RuleKey<FloatRule> key, final float value) {
    server.getGameRules().get(key).set(value, server);
  }

  /**
   * Sets the value of the given {@link FloatRule} key to the given {@code value}
   *
   * @param level The level containing the game rules
   * @param key The rule's key
   * @param value The {@code float} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setFloat(final World level, final RuleKey<FloatRule> key, final float value) {
    level.getGameRules().get(key).set(value, level.getServer());
  }

  /**
   * Gets the value of the given {@link StringRule} key
   *
   * @param rules The game rules instance
   * @param key The key representing the rule
   * @return The {@link String} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static String getString(final GameRules rules, final RuleKey<StringRule> key) {
    return rules.get(key).get();
  }

  /**
   * Gets the value of the given {@link StringRule} key
   *
   * @param server The server containing the game rules
   * @param key The key representing the rule
   * @return The {@link String} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static String getString(final MinecraftServer server, final RuleKey<StringRule> key) {
    return server.getGameRules().get(key).get();
  }

  /**
   * Gets the value of the given {@link StringRule} key
   *
   * @param level The level containing the game rules
   * @param key The key representing the rule
   * @return The {@link String} value of the rule instance
   * @since 0.1.0
   */
  @Contract(pure = true)
  public static String getString(final World level, final RuleKey<StringRule> key) {
    return level.getGameRules().get(key).get();
  }

  /**
   * Sets the value of the given {@link StringRule} key to the given {@code value}
   *
   * @param rules The game rules instance
   * @param key The rule's key
   * @param value The {@link String} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setString(final GameRules rules, final RuleKey<StringRule> key, final String value) {
    rules.get(key).set(value, null);
  }

  /**
   * Sets the value of the given {@link StringRule} key to the given {@code value}
   *
   * @param server The server containing the game rules
   * @param key The rule's key
   * @param value The {@link String} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setString(final MinecraftServer server, final RuleKey<StringRule> key, final String value) {
    server.getGameRules().get(key).set(value, server);
  }

  /**
   * Sets the value of the given {@link StringRule} key to the given {@code value}
   *
   * @param level The level containing the game rules
   * @param key The rule's key
   * @param value The {@link String} value to be set
   * @since 0.1.0
   */
  @Contract(mutates = "param1")
  public static void setString(final World level, final RuleKey<StringRule> key, final String value) {
    level.getGameRules().get(key).set(value, level.getServer());
  }

  /**
   * Sets the value of the given {@link IntRule} to the given {@code value}, and invokes
   * the callback method declared in {@link Rule}. This method is effectively a placeholder
   * for an absent/stripped method in the actual class. This method is semantically equivalent
   * to {@link BooleanRule#set(boolean, MinecraftServer)}.
   *
   * @param rule The receiver rule
   * @param value The value to be set
   * @param server The server to call back to
   * @since 0.1.0
   */
  @Beta
  @Contract(mutates = "param1")
  public static void set(final IntRule rule, final int value, final @Nullable MinecraftServer server) {
    ((IntRuleAccessor) rule).setValue(value);
    ((RuleAccessor) rule).invokeChanged(server);
  }

  @Contract("_, _, _, _ -> new")
  static <T extends Rule<T>> RuleType<T> type(
    final String ruleValueType,
    final Supplier<ArgumentType<?>> argumentType,
    final Function<RuleType<T>, T> ruleFactory,
    final BiConsumer<MinecraftServer, T> changeCallback
  ) {
    LOGGER.debug("Creating new {} rule type", ruleValueType);
    return RuleTypeAccessor.newRuleType(argumentType, ruleFactory, changeCallback);
  }

  @Contract(pure = true)
  private static Map<RuleKey<?>, RuleType<?>> ruleTypes() {
    return GameRulesAccessor.getRuleTypes();
  }

  @Contract(value = "_, _ -> new", pure = true)
  private static RuleType<BooleanRule> createBooleanRule(final boolean initialValue, final BiConsumer<MinecraftServer, BooleanRule> changeCallback) {
    LOGGER.debug("Creating new boolean rule type");
    return BooleanRuleAccessor.callCreate(initialValue, changeCallback);
  }

  @Contract(value = "_, _ -> new", pure = true)
  private static RuleType<IntRule> createIntRule(final int initialValue, final BiConsumer<MinecraftServer, IntRule> changeCallback) {
    LOGGER.debug("Creating new int rule type");
    return IntRuleAccessor.callCreate(initialValue, changeCallback);
  }

  @Contract("_, _ -> new")
  private static <T extends Rule<T>> RuleKey<T> register(final String name, final RuleType<T> type) {
    LOGGER.debug("Registering rule '{}' {}", name, type.getClass().getName());
    return GameRulesAccessor.callRegister(name, type);
  }
}
