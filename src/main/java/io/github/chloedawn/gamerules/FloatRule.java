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
import com.mojang.brigadier.arguments.FloatArgumentType;
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
public final class FloatRule extends Rule<FloatRule> {
  private static final Logger LOGGER = LogManager.getLogger();

  private float value;

  @Contract(pure = true)
  private FloatRule(final RuleType<FloatRule> type, final float value) {
    super(type);
    this.value = value;
  }

  @Contract("_, _ -> new")
  static RuleType<FloatRule> of(final float value, final BiConsumer<MinecraftServer, FloatRule> notifier) {
    Preconditions.checkArgument(Float.isFinite(value), "Default value must be a number");
    return RuleTypeFactory.getInstance().make(FloatArgumentType::floatArg, type -> new FloatRule(type, value), notifier);
  }

  @Contract("_ -> new")
  static RuleType<FloatRule> of(final float value) {
    return of(value, (server, rule) -> {});
  }

  private static float parseFloat(final String string) {
    if (!string.isEmpty()) {
      try {
        final float value = Float.parseFloat(string);
        if (Float.isFinite(value)) {
          return value;
        }
        LOGGER.warn("Parsed float was not a number {}", string);
      } catch (final NumberFormatException e) {
        LOGGER.warn("Failed to parse float {}", string);
      }
    }
    return 0.0F;
  }

  @Contract(pure = true)
  public float get() {
    return this.value;
  }

  @Contract(mutates = "this")
  public void set(final float value, @Nullable final MinecraftServer server) {
    this.value = value;
    this.notify(server);
  }

  @Override
  @Contract(mutates = "this")
  protected void setFromArgument(final CommandContext<ServerCommandSource> context, final String name) {
    this.value = FloatArgumentType.getFloat(context, name);
  }

  @Override
  @Contract(mutates = "this")
  protected void setFromString(final String string) {
    this.value = parseFloat(string);
  }

  @Override
  protected String valueToString() {
    return Float.toString(this.value);
  }

  @Override
  @Contract(pure = true)
  public int toCommandResult() {
    return Float.compare(this.value, 0.0F);
  }

  @Override
  @Contract(pure = true)
  protected FloatRule getThis() {
    return this;
  }
}
