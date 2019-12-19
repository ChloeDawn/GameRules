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

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.RuleType;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiConsumer;

/**
 * Accessor interface for {@link BooleanRule}
 *
 * @author Chloe Dawn
 */
@Mixin(BooleanRule.class)
public interface BooleanRuleAccessor {
  /**
   * Calls the primary {@link RuleType<BooleanRule>} factory method
   *
   * @param initialValue The initial boolean value of rule instances
   * @param changeCallback The change callback for rule instances
   * @return A new boolean rule type
   */
  @Invoker
  @Contract(value = "_, _ -> new", pure = true)
  static RuleType<BooleanRule> callCreate(final boolean initialValue, final BiConsumer<MinecraftServer, BooleanRule> changeCallback) {
    //noinspection Contract
    throw new AssertionError();
  }
}
