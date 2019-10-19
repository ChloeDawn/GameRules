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

import io.github.chloedawn.gamerules.RuleTypeFactory;
import net.minecraft.world.GameRules.RuleType;
import org.spongepowered.asm.mixin.Mixin;

// TODO: Replace with constructor invoker in Mixin 0.8
@Mixin(RuleType.class)
abstract class RuleTypeFactoryMixin {
  private static final RuleTypeFactory RULE_TYPE_FACTORY = RuleType::new;
}
