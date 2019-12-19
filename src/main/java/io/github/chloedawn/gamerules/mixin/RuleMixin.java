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
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.RuleType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin class for {@link Rule}
 *
 * @author Chloe Dawn
 */
@Mixin(Rule.class)
abstract class RuleMixin<T extends Rule<T>> {
  @Shadow @Final private RuleType<T> type;

  @Shadow
  protected abstract T getThis();

  /**
   * Injects into {@link Rule#changed(MinecraftServer)} after the server has been checked
   * non-null and the primary callback has been called, and calls additional callbacks
   *
   * @param server The server that this rule is bound to
   * @param ci The callback information
   * @see RuleChangeCallbacks#call(MinecraftServer, Rule)
   */
  @Inject(method = "changed", at = @At(value = "INVOKE", target = "Ljava/util/function/BiConsumer;accept(Ljava/lang/Object;Ljava/lang/Object;)V", shift = Shift.AFTER))
  private void callAdditionalCallbacks(final MinecraftServer server, final CallbackInfo ci) {
    RuleChangeCallbacks.<T>of(this.type).call(server, this.getThis());
  }
}
