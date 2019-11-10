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
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.IntRule;
import net.minecraft.world.GameRules.RuleKey;

public final class RuleDelegates {
	private RuleDelegates() {
	}

	public static boolean getBoolean(final MinecraftServer server, final RuleKey<BooleanRule> key) {
		return server.getGameRules().get(key).get();
	}

	public static void setBoolean(final MinecraftServer server, final RuleKey<BooleanRule> key, final boolean value) {
		server.getGameRules().get(key).set(value, server);
	}

	public static int getInt(final MinecraftServer server, final RuleKey<IntRule> key) {
		return server.getGameRules().get(key).get();
	}

	public static void setInt(final MinecraftServer server, final RuleKey<IntRule> key, final int value) {
		IntRuleSetter.set(server.getGameRules().get(key), value, server);
	}

	@Beta
	public static double getDouble(final MinecraftServer server, final RuleKey<DoubleRule> key) {
		return server.getGameRules().get(key).get();
	}

	@Beta
	public static void setDouble(final MinecraftServer server, final RuleKey<DoubleRule> key, final double value) {
		server.getGameRules().get(key).set(value, server);
	}

	@Beta
	public static float getFloat(final MinecraftServer server, final RuleKey<FloatRule> key) {
		return server.getGameRules().get(key).get();
	}

	@Beta
	public static void setFloat(final MinecraftServer server, final RuleKey<FloatRule> key, final float value) {
		server.getGameRules().get(key).set(value, server);
	}

	@Beta
	public static <E extends Enum<E>> E getEnum(final MinecraftServer server, final RuleKey<EnumRule<E>> key) {
		return server.getGameRules().get(key).get();
	}

	@Beta
	public static <E extends Enum<E>> void setEnum(final MinecraftServer server, final RuleKey<EnumRule<E>> key, final E value) {
		server.getGameRules().get(key).set(value, server);
	}

	@Beta
	public static String getString(final MinecraftServer server, final RuleKey<StringRule> key) {
		return server.getGameRules().get(key).get();
	}

	@Beta
	public static void setString(final MinecraftServer server, final RuleKey<StringRule> key, final String value) {
		server.getGameRules().get(key).set(value, server);
	}
}
