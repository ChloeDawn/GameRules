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

package io.github.chloedawn.gamerules.mixin;

import io.github.chloedawn.gamerules.RuleChangeCallbacks;
import io.github.chloedawn.gamerules.mixin.access.RuleTypeAccessor;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * Mixin class for {@link RuleType}
 *
 * @param <T> The rule type
 */
@Mixin(RuleType.class)
abstract class RuleTypeMixin<T extends Rule<T>> {
  /**
   * Storage for additional change callbacks exposed through an accessor mixin
   *
   * @see RuleTypeAccessor#getChangeCallbacks()
   */
  @Unique private final RuleChangeCallbacks<T> changeCallbacks = new RuleChangeCallbacks<>();
}
