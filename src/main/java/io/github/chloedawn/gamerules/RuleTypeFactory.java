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

import com.mojang.brigadier.arguments.ArgumentType;
import io.github.chloedawn.gamerules.mixin.RuleTypeAccessors;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleType;
import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

// TODO: Replace with constructor invoker in Mixin 0.8
public interface RuleTypeFactory {
	@Contract(pure = true)
	static RuleTypeFactory getInstance() {
		return RuleTypeAccessors.getRuleTypeFactory();
	}

	@Contract(value = "_, _, _ -> new", pure = true)
	<T extends Rule<T>> RuleType<T> make(
		final Supplier<ArgumentType<?>> argumentType,
		final Function<RuleType<T>, T> ruleFactory,
		final BiConsumer<MinecraftServer, T> notifier
	);
}
