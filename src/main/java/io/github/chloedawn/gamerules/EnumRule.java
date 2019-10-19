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
import com.mojang.brigadier.arguments.StringArgumentType;
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
public final class EnumRule<E extends Enum<E>> extends Rule<EnumRule<E>> {
  private static final Logger LOGGER = LogManager.getLogger();

  private final Class<E> valueType;
  private E value;

  private EnumRule(final RuleType<EnumRule<E>> type, final Class<E> valueType, final E value) {
    super(type);
    this.valueType = valueType;
    this.value = value;
  }

  @Contract("_, _, _ -> new")
  static <E extends Enum<E>> RuleType<EnumRule<E>> of(final Class<E> valueType, final E value, final BiConsumer<MinecraftServer, EnumRule<E>> notifier) {
    return RuleTypeFactory.getInstance().make(StringArgumentType::string, type -> new EnumRule<>(type, valueType, value), notifier);
  }

  @Contract("_, _ -> new")
  static <E extends Enum<E>> RuleType<EnumRule<E>> of(final Class<E> valueType, final E value) {
    return of(valueType, value, (server, rule) -> {});
  }

  static <E extends Enum<E>> E defaultValue(final Class<E> valueType) {
    return valueType.getEnumConstants()[0];
  }

  private static <E extends Enum<E>> E parseEnum(final Class<E> type, final String string) {
    if (!string.isEmpty()) {
      try {
        return Enum.valueOf(type, string);
      } catch (final IllegalArgumentException e) {
        LOGGER.warn("Failed to parse enum {} {}", type.getSimpleName(), string);
      }
    }
    return defaultValue(type);
  }

  @Contract(pure = true)
  public E get() {
    return this.value;
  }

  @Contract(mutates = "this")
  public void set(final E value, @Nullable final MinecraftServer server) {
    this.value = value;
    this.notify(server);
  }

  @Override
  protected void setFromArgument(final CommandContext<ServerCommandSource> context, final String name) {
    this.setFromString(StringArgumentType.getString(context, name));
  }

  @Override
  protected void setFromString(final String string) {
    this.value = parseEnum(this.valueType, string);
  }

  @Override
  protected String valueToString() {
    return this.value.name();
  }

  @Override
  public int toCommandResult() {
    return this.value.ordinal();
  }

  @Override
  protected EnumRule<E> getThis() {
    return this;
  }
}
