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

import com.mojang.brigadier.arguments.ArgumentType;
import io.github.chloedawn.gamerules.RuleChangeCallbacks;
import io.github.chloedawn.gamerules.Rules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleType;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Accessor interface for {@link RuleType}
 *
 * @author Chloe Dawn
 */
@Mixin(RuleType.class)
public interface RuleTypeAccessor<T extends Rule<T>> {
  /**
   * Calls the primary constructor
   *
   * @param argumentType The command argument type of the rule type
   * @param ruleFactory The rule instance factory
   * @param changeCallback The rule change callback
   * @param <T> The rule instance type
   * @return A new rule type instance
   * @see Rules#type(String, Supplier, Function, BiConsumer)
   */
  @Invoker("<init>")
  @Contract(value = "_, _, _ -> new", pure = true)
  static <T extends Rule<T>> RuleType<T> newRuleType(
    final Supplier<ArgumentType<?>> argumentType,
    final Function<RuleType<T>, T> ruleFactory,
    final BiConsumer<MinecraftServer, T> changeCallback
  ) {
    //noinspection Contract
    throw new AssertionError();
  }

  /**
   * Gets the rule change callbacks of this rule
   *
   * @return This rule type's additional change callbacks
   * @see RuleChangeCallbacks#of(RuleType)
   */
  @Contract(pure = true)
  @Accessor(remap = false)
  RuleChangeCallbacks<T> getChangeCallbacks();
}
