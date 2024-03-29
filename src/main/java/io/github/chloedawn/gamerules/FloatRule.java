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

import com.google.common.base.Preconditions;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;


/**
 * A {@link Rule} implementation for finite {@code float} values
 *
 * @author Chloe Dawn
 * @see Rules#createFloatRule
 * @since 0.1.0
 */
public final class FloatRule extends Rule<FloatRule> {
  private static final Logger LOGGER = LogManager.getLogger();

  private float value;

  @Contract(pure = true)
  private FloatRule(final RuleType<FloatRule> type, final float initialValue) {
    super(type);
    this.value = initialValue;
  }

  @Contract("_, _ -> new")
  static RuleType<FloatRule> create(final float initialValue, final BiConsumer<MinecraftServer, FloatRule> changeCallback) {
    Preconditions.checkArgument(Float.isFinite(initialValue), "Initial value must be a number %s", initialValue);
    return Rules.type("float", FloatArgumentType::floatArg, type -> new FloatRule(type, initialValue), changeCallback);
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
  public void set(final float value, final @Nullable MinecraftServer server) {
    Preconditions.checkArgument(Float.isFinite(value), "Value must be a number %s", value);
    this.value = value;
    this.changed(server);
  }

  @Override
  @Contract(mutates = "this")
  protected void setFromArgument(final CommandContext<ServerCommandSource> context, final String name) {
    final float value = FloatArgumentType.getFloat(context, name);
    if (Float.isFinite(value)) {
      this.value = value;
    } else {
      LOGGER.warn("Float argument was not a number {}", value);
      this.value = 0.0F;
    }
  }

  @Override
  @Contract(mutates = "this")
  protected void deserialize(final String string) {
    this.value = parseFloat(string);
  }

  @Override
  protected String serialize() {
    return Float.toString(this.value);
  }

  @Override
  @Contract(pure = true)
  public int getCommandResult() {
    return Float.compare(this.value, 0.0F);
  }

  @Override
  @Contract(pure = true)
  protected FloatRule getThis() {
    return this;
  }
}
