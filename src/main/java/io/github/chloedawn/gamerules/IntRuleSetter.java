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

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.IntRule;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nullable;

public interface IntRuleSetter {
	/**
	 * Sets the value of the given {@link IntRule} to the given {@code value}
	 *
	 * @param rule The int rule to set the value of
	 * @param value The int value
	 * @param server The server to be notified
	 */
	@Contract(mutates = "param1")
	static void set(final IntRule rule, final int value, @Nullable final MinecraftServer server) {
		((IntRuleSetter) rule).moregamerules$set(value, server);
	}

	@Contract(mutates = "this")
	void moregamerules$set(final int value, @Nullable final MinecraftServer server);
}
