package io.github.chloedawn.gamerules;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.Rule;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiConsumer;

final class MutableNotifiers<T extends Rule<T>> implements Notifiers<T> {
  private final Set<BiConsumer<MinecraftServer, T>> notifiers = new LinkedHashSet<>(0);

  @Override
  public void onEach(final MinecraftServer server, final T rule) {
    this.notifiers.forEach(notifier -> notifier.accept(server, rule));
  }

  void add(final BiConsumer<MinecraftServer, T> notifier) {
    if (!this.notifiers.add(notifier)) {
      throw new IllegalArgumentException("Notifier already added " + notifier);
    }
  }
}
