package io.github.chloedawn.gamerules.mixin.access;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.Rule;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Accessor interface for {@link Rule}
 *
 * @author Chloe Dawn
 */
@Mixin(Rule.class)
public interface RuleAccessor {
  /**
   * Invokes the method that calls the primary change callback using the given
   * {@code server} and {@code this} if the given {@code server} is non-null
   *
   * @param server The server that this rule is bound to
   * @implNote The method will also call additional callbacks
   * @see Rule#changed(MinecraftServer)
   */
  @Invoker
  void invokeChanged(final @Nullable MinecraftServer server);
}
