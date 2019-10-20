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

import io.github.chloedawn.gamerules.Notifiers;
import io.github.chloedawn.gamerules.RuleTypeFactory;
import net.minecraft.world.GameRules.Rule;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Accessor;

@Pseudo
@Mixin(targets = "net.minecraft.world.GameRules$RuleType")
public interface RuleTypeAccessors<T extends Rule<T>> {
  // TODO: Replace with constructor invoker in Mixin 0.8
  @Contract(pure = true)
  @Accessor(value = "RULE_TYPE_FACTORY", remap = false)
  static RuleTypeFactory getRuleTypeFactory() {
    throw new AssertionError();
  }

  @Contract(pure = true)
  @Accessor(remap = false)
  Notifiers<T> getAdditionalNotifiers();
}
