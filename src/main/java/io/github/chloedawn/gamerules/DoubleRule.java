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
import com.google.common.base.Preconditions;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

@Beta
public final class DoubleRule extends Rule<DoubleRule> {
  private static final Logger LOGGER = LogManager.getLogger();

  private double value;

  @Contract(pure = true)
  private DoubleRule(final RuleType<DoubleRule> type, final double value) {
    super(type);
    this.value = value;
  }

  @Contract("_, _ -> new")
  static RuleType<DoubleRule> of(final double value, final BiConsumer<MinecraftServer, DoubleRule> notifier) {
    Preconditions.checkArgument(Double.isFinite(value), "Default value must be a number");
    return RuleTypeFactory.getInstance().make(DoubleArgumentType::doubleArg, type -> new DoubleRule(type, value), notifier);
  }

  @Contract("_ -> new")
  static RuleType<DoubleRule> of(final double value) {
    return of(value, (server, rule) -> {});
  }

  private static double parseDouble(final String string) {
    if (!string.isEmpty()) {
      try {
        final double value = Double.parseDouble(string);
        if (Double.isFinite(value)) {
          return value;
        }
        LOGGER.warn("Parsed double was not a number {}", string);
      } catch (final NumberFormatException e) {
        LOGGER.warn("Failed to parse double {}", string);
      }
    }
    return 0.0;
  }

  @Contract(pure = true)
  public double get() {
    return this.value;
  }

  @Contract(mutates = "this")
  public void set(final double value, @Nullable final MinecraftServer server) {
    this.value = value;
    this.notify(server);
  }

  @Override
  @Contract(mutates = "this")
  protected void setFromArgument(final CommandContext<ServerCommandSource> context, final String name) {
    this.value = DoubleArgumentType.getDouble(context, name);
  }

  @Override
  @Contract(mutates = "this")
  protected void setFromString(final String string) {
    this.value = parseDouble(string);
  }

  @Override
  protected String valueToString() {
    return Double.toString(this.value);
  }

  @Override
  @Contract(pure = true)
  public int toCommandResult() {
    return Double.compare(this.value, 0.0);
  }

  @Override
  @Contract(pure = true)
  protected DoubleRule getThis() {
    return this;
  }
}
