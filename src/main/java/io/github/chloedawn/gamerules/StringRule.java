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
import org.jetbrains.annotations.Contract;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

@Beta
public final class StringRule extends Rule<StringRule> {
  private String value;

  @Contract(pure = true)
  private StringRule(final RuleType<StringRule> type, final String value) {
    super(type);
    this.value = value;
  }

  @Contract("_, _ -> new")
  static RuleType<StringRule> of(final String value, final BiConsumer<MinecraftServer, StringRule> notifier) {
    return RuleTypeFactory.getInstance().make(StringArgumentType::string, type -> new StringRule(type, value), notifier);
  }

  @Contract("_ -> new")
  static RuleType<StringRule> of(final String value) {
    return of(value, (server, rule) -> {});
  }

  @Contract(pure = true)
  public String get() {
    return this.value;
  }

  @Contract(mutates = "this")
  public void set(final String value, @Nullable final MinecraftServer server) {
    this.value = value;
    this.notify(server);
  }

  @Override
  @Contract(mutates = "this")
  protected void setFromArgument(final CommandContext<ServerCommandSource> context, final String name) {
    this.value = StringArgumentType.getString(context, name);
  }

  @Override
  @Contract(mutates = "this")
  protected void setFromString(final String string) {
    this.value = string;
  }

  @Override
  @Contract(pure = true)
  protected String valueToString() {
    return this.value;
  }

  @Override
  @Contract(pure = true)
  public int toCommandResult() {
    return this.value.hashCode();
  }

  @Override
  @Contract(pure = true)
  protected StringRule getThis() {
    return this;
  }
}
