package io.github.chloedawn.gamerules;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.Rule;
import org.jetbrains.annotations.Contract;

public interface Notifiers<T extends Rule<T>> {
	@Contract(value = " -> new", pure = true)
	static <T extends Rule<T>> Notifiers<T> create() {
		return new MutableNotifiers<>();
	}

	void onEach(final MinecraftServer server, final T rule);
}
