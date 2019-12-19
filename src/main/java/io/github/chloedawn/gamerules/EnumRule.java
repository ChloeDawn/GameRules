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

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.Contract;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * A {@link Rule} implementation for typed {@code enum} values
 *
 * @author Chloe Dawn
 * @see Rules#createEnumRule
 * @since 0.1.0
 */
public final class EnumRule<E extends Enum<E>> extends Rule<EnumRule<E>> {
  private static final Logger LOGGER = LogManager.getLogger();

  private final Class<E> valueType;
  private E value;

  private EnumRule(final RuleType<EnumRule<E>> type, final Class<E> valueType, final E initialValue) {
    super(type);
    this.valueType = valueType;
    this.value = initialValue;
  }

  @Contract("_, _, _ -> new")
  static <E extends Enum<E>> RuleType<EnumRule<E>> create(final Class<E> valueType, final E initialValue, final BiConsumer<MinecraftServer, EnumRule<E>> changeCallback) {
    return Rules.type(valueType.getSimpleName() + " enum", StringArgumentType::word, type -> new EnumRule<>(type, valueType, initialValue), changeCallback);
  }

  static <E extends Enum<E>> @Nullable E initialValue(final Class<E> valueType) {
    final E[] constants = valueType.getEnumConstants();
    return constants.length == 0 ? null : constants[0];
  }

  private static <E extends Enum<E>> E parseEnum(final Class<E> type, final String string) {
    if (!string.isEmpty()) {
      try {
        return Enum.valueOf(type, string);
      } catch (final IllegalArgumentException e) {
        LOGGER.warn("Failed to parse enum {} {}", type.getSimpleName(), string);
      }
    }
    final @Nullable E value = initialValue(type);
    assert value != null : "No initial value";
    return value;
  }

  @Contract(pure = true)
  public E get() {
    return this.value;
  }

  @Contract(mutates = "this")
  public void set(final E value, final @Nullable MinecraftServer server) {
    this.value = Objects.requireNonNull(value);
    this.changed(server);
  }

  @Override
  protected void setFromArgument(final CommandContext<ServerCommandSource> context, final String name) {
    this.value = parseEnum(this.valueType, StringArgumentType.getString(context, name));
  }

  @Override
  protected void deserialize(final String string) {
    this.value = parseEnum(this.valueType, string);
  }

  @Override
  protected String serialize() {
    return this.value.name();
  }

  @Override
  public int getCommandResult() {
    return this.value.ordinal();
  }

  @Override
  protected EnumRule<E> getThis() {
    return this;
  }
}
