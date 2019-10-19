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
import com.google.common.reflect.Reflection;
import io.github.chloedawn.gamerules.mixin.BooleanRuleFactory;
import io.github.chloedawn.gamerules.mixin.GameRulesAccessor;
import io.github.chloedawn.gamerules.mixin.GameRulesRegistrar;
import io.github.chloedawn.gamerules.mixin.IntRuleFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.IntRule;
import net.minecraft.world.GameRules.RuleKey;
import net.minecraft.world.GameRules.RuleType;
import org.jetbrains.annotations.Contract;

import java.util.Optional;
import java.util.function.BiConsumer;

public final class MoreGameRules {
  static {
    // Ensure classes have been loaded and mixins applied before any static calls are made
    Reflection.initialize(GameRules.class, BooleanRule.class, IntRule.class, RuleType.class);
  }

  private MoreGameRules() {
  }

  /**
   * Searches registered game rules for one matching the given {@code name}
   *
   * @param name The name of the game rule
   * @return An optional of the rule key for the given {@code name}
   */
  public static Optional<RuleKey<?>> findRule(final String name) {
    return GameRulesAccessor.moregamerules$getRules().keySet().stream()
      .filter(key -> name.equals(key.getName()))
      .findFirst();
  }

  /**
   * Retrieves the registered game rule for the given {@code name}
   *
   * @param name The name of the game rule
   * @return The rule key for the given {@code name}
   * @throws NoSuchRuleException If no rule exists for the given {@code name}
   */
  public static RuleKey<?> getRule(final String name) {
    return findRule(name).orElseThrow(() -> new NoSuchRuleException(name));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Contract("_, _, _ -> new")
  public static RuleKey<BooleanRule> makeBooleanRule(final String name, final boolean defaultValue, final BiConsumer<MinecraftServer, BooleanRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, BooleanRuleFactory.moregamerules$make(defaultValue, notifier));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Contract("_, _ -> new")
  public static RuleKey<BooleanRule> makeBooleanRule(final String name, final boolean defaultValue) {
    return GameRulesRegistrar.moregamerules$register(name, BooleanRuleFactory.moregamerules$make(defaultValue, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to a value of {@code false}
   *
   * @param name The unique name of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Contract("_, _ -> new")
  public static RuleKey<BooleanRule> makeBooleanRule(final String name, final BiConsumer<MinecraftServer, BooleanRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, BooleanRuleFactory.moregamerules$make(false, notifier));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to a value of {@code false}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Contract("_ -> new")
  public static RuleKey<BooleanRule> makeBooleanRule(final String name) {
    return GameRulesRegistrar.moregamerules$register(name, BooleanRuleFactory.moregamerules$make(false, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link IntRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Contract("_, _, _ -> new")
  public static RuleKey<IntRule> makeIntRule(final String name, final int defaultValue, final BiConsumer<MinecraftServer, IntRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, IntRuleFactory.moregamerules$make(defaultValue, notifier));
  }

  /**
   * Creates and registers a new {@link IntRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Contract("_, _ -> new")
  public static RuleKey<IntRule> makeIntRule(final String name, final int defaultValue) {
    return GameRulesRegistrar.moregamerules$register(name, IntRuleFactory.moregamerules$make(defaultValue, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link IntRule} by the given {@code name}, defaulting to a value of {@code 0}
   *
   * @param name The unique name of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Contract("_, _ -> new")
  public static RuleKey<IntRule> makeIntRule(final String name, final BiConsumer<MinecraftServer, IntRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, IntRuleFactory.moregamerules$make(0, notifier));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to a value of {@code false}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Contract("_ -> new")
  public static RuleKey<IntRule> makeIntRule(final String name) {
    return GameRulesRegistrar.moregamerules$register(name, IntRuleFactory.moregamerules$make(0, (server, rule) -> {}));
  }

  /**
   * Creates and registers a new {@link DoubleRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _, _ -> new")
  public static RuleKey<DoubleRule> makeDoubleRule(final String name, final double defaultValue, final BiConsumer<MinecraftServer, DoubleRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, DoubleRule.of(defaultValue, notifier));
  }

  /**
   * Creates and registers a new {@link DoubleRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _ -> new")
  public static RuleKey<DoubleRule> makeDoubleRule(final String name, final double defaultValue) {
    return GameRulesRegistrar.moregamerules$register(name, DoubleRule.of(defaultValue));
  }

  /**
   * Creates and registers a new {@link DoubleRule} by the given {@code name}, defaulting to a value of {@code 0.0}
   *
   * @param name The unique name of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _ -> new")
  public static RuleKey<DoubleRule> makeDoubleRule(final String name, final BiConsumer<MinecraftServer, DoubleRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, DoubleRule.of(0.0, notifier));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to a value of {@code false}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_ -> new")
  public static RuleKey<DoubleRule> makeDoubleRule(final String name) {
    return GameRulesRegistrar.moregamerules$register(name, DoubleRule.of(0.0));
  }

  /**
   * Creates and registers a new {@link FloatRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _, _ -> new")
  public static RuleKey<FloatRule> makeFloatRule(final String name, final float defaultValue, final BiConsumer<MinecraftServer, FloatRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, FloatRule.of(defaultValue, notifier));
  }

  /**
   * Creates and registers a new {@link FloatRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _ -> new")
  public static RuleKey<FloatRule> makeFloatRule(final String name, final float defaultValue) {
    return GameRulesRegistrar.moregamerules$register(name, FloatRule.of(defaultValue));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to a value of {@code 0.0F}
   *
   * @param name The unique name of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _ -> new")
  public static RuleKey<FloatRule> makeFloatRule(final String name, final BiConsumer<MinecraftServer, FloatRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, FloatRule.of(0.0F, notifier));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to a value of {@code false}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_ -> new")
  public static RuleKey<FloatRule> makeFloatRule(final String name) {
    return GameRulesRegistrar.moregamerules$register(name, FloatRule.of(0.0F));
  }

  /**
   * Creates and registers a new {@link StringRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _, _ -> new")
  public static RuleKey<StringRule> makeStringRule(final String name, final String defaultValue, final BiConsumer<MinecraftServer, StringRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, StringRule.of(defaultValue, notifier));
  }

  /**
   * Creates and registers a new {@link StringRule} by the given {@code name}, defaulting to the given {@code defaultValue}
   *
   * @param name The unique name of the rule
   * @param defaultValue The default value of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _ -> new")
  public static RuleKey<StringRule> makeStringRule(final String name, final String defaultValue) {
    return GameRulesRegistrar.moregamerules$register(name, StringRule.of(defaultValue));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to a value of {@code ""}
   *
   * @param name The unique name of the rule
   * @param notifier The notifier callback when the rule is updated by the server
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_, _ -> new")
  public static RuleKey<StringRule> makeStringRule(final String name, final BiConsumer<MinecraftServer, StringRule> notifier) {
    return GameRulesRegistrar.moregamerules$register(name, StringRule.of("", notifier));
  }

  /**
   * Creates and registers a new {@link BooleanRule} by the given {@code name}, defaulting to a value of {@code false}
   *
   * @param name The unique name of the rule
   * @return A {@link RuleKey} for querying the rule from a level's {@link GameRules}
   * @throws IllegalStateException If a rule by the given {@code name} already exists
   */
  @Beta
  @Contract("_ -> new")
  public static RuleKey<StringRule> makeStringRule(final String name) {
    return GameRulesRegistrar.moregamerules$register(name, StringRule.of(""));
  }
}
