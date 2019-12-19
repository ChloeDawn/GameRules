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

import io.github.chloedawn.gamerules.mixin.access.RuleTypeAccessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleKey;
import net.minecraft.world.GameRules.RuleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;

/**
 * Data class for holding additional change callbacks for a rule type
 *
 * @param <T> The rule type
 * @author Chloe Dawn
 */
@ApiStatus.Internal
public final class RuleChangeCallbacks<T extends Rule<T>> {
  private static final Logger LOGGER = LogManager.getLogger();

  private final Collection<BiConsumer<MinecraftServer, T>> callbacks = new LinkedHashSet<>(0);

  /**
   * Gets the additional change callbacks of the given {@link RuleType}
   *
   * @param type The rule type to get the callbacks of
   * @param <T> The rule instance type
   * @return The rule type's callbacks
   */
  @Contract(pure = true)
  @SuppressWarnings("unchecked")
  public static <T extends Rule<T>> RuleChangeCallbacks<T> of(final RuleType<?> type) {
    return ((RuleTypeAccessor<T>) type).getChangeCallbacks();
  }

  /**
   * Appends the given change {@code callback} to the given {@link RuleType}
   *
   * @param key The key used to lookup the type reference
   * @param type The rule type to add the callback to
   * @param callback The callback to be added
   * @param <T> The rule instance type
   * @throws IllegalArgumentException If the callback has already been added
   */
  static <T extends Rule<T>> void add(final RuleKey<?> key, final RuleType<?> type, final BiConsumer<MinecraftServer, T> callback) {
    if (!RuleChangeCallbacks.<T>of(type).callbacks.add(callback)) {
      throw new IllegalArgumentException("Duplicate change callback for " + key);
    }
  }

  /**
   * Calls any added change callbacks for the given {@code server} and {@code rule}
   *
   * @param server The server that the given rule is bound to
   * @param rule The rule that was changed
   */
  public void call(final MinecraftServer server, final T rule) {
    LOGGER.debug("Calling additional change callbacks");
    for (final BiConsumer<MinecraftServer, T> callback : this.callbacks) {
      callback.accept(server, rule);
    }
  }
}
