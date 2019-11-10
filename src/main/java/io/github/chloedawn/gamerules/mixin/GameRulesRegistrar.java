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

import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleKey;
import net.minecraft.world.GameRules.RuleType;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.throwables.MixinException;

@Mixin(GameRules.class)
@SuppressWarnings("Contract") // Contract is not violated at runtime
public interface GameRulesRegistrar {
	@Invoker("register")
	@Contract("_, _ -> new")
	static <T extends Rule<T>> RuleKey<T> moregamerules$register(
		@SuppressWarnings("unused") final String name,
		@SuppressWarnings("unused") final RuleType<T> type
	) {
		throw new MixinException("Missing invoker implementation");
	}
}
