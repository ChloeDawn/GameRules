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

package io.github.chloedawn.gamerules.mixin.access;

import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleKey;
import net.minecraft.world.GameRules.RuleType;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

/**
 * Accessor interface for {@link GameRules}
 *
 * @author Chloe Dawn
 */
@Mixin(GameRules.class)
public interface GameRulesAccessor {
  /**
   * Gets the map of currently registered rule types. The map
   * returned by this method is a pure reference, and should
   * be handled with caution
   *
   * @return The rule types
   */
  @Accessor("RULE_TYPES")
  @Contract(value = "-> _", pure = true)
  static Map<RuleKey<?>, RuleType<?>> getRuleTypes() {
    //noinspection Contract
    throw new AssertionError();
  }

  /**
   * Calls the method that registers the given {@code type} {@link RuleType} under the given {@code name}
   *
   * @param name The name of the rule type
   * @param type The rule type to be registered
   * @param <T> The rule instance type
   * @return A {@link RuleKey} used for getting rule instances
   * @throws IllegalStateException If a rule type by the given {@code name} already exists
   */
  @Invoker
  @Contract("_, _ -> new")
  static <T extends Rule<T>> RuleKey<T> callRegister(final String name, final RuleType<T> type) {
    //noinspection Contract
    throw new AssertionError();
  }
}
