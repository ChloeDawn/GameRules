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

import net.minecraft.world.GameRules.RuleKey;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

/**
 * Thrown by rule accessor methods to indicate that a rule
 * being requested by a given name does not exist
 *
 * @author Chloe Dawn
 * @see Rules#get(String)
 * @see Rules#getUnchecked(String)
 * @see Rules#observe(RuleKey, BiConsumer)
 */
final class NoSuchRuleException extends NoSuchElementException {
  private static final long serialVersionUID = -1936054127254095744L;

  /**
   * Constructs a new exception using the given {@code ruleName} as the
   * message, wrapped with {@code '} quotes, or an unquoted {@code "null"}
   * if the given {@code ruleName} is null
   *
   * @param name The name of the non-existent rule
   */
  NoSuchRuleException(final @Nullable String name) {
    super(name == null ? "null" : '\'' + name + '\'');
  }

  /**
   * Constructs a new exception using the given {@code ruleKey}'s name
   * as the message, wrapped with {@code '} quotes, or an unquoted
   * {@code "null"} if the given {@code ruleKey}'s name is null
   *
   * @param key The unbound key of the non-existent rule
   */
  NoSuchRuleException(final RuleKey<?> key) {
    this(key.getName());
  }
}
