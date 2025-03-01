package de.royzer.fabrichg.kit.kits

import de.royzer.fabrichg.data.hgplayer.hgPlayer
import de.royzer.fabrichg.kit.cooldown.checkUsesForCooldown
import de.royzer.fabrichg.kit.kit
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Items

val gravityKit = kit("Gravity") {
    cooldown = 40.0
    val maxUses = 3

    kitSelectorItem = Items.PURPLE_GLAZED_TERRACOTTA.defaultInstance

    description = "Send yourself or others in the sky"

    kitItem {
        itemStack = kitSelectorItem
        onClick { hgPlayer, _ ->
            hgPlayer.serverPlayer?.addEffect(MobEffectInstance(MobEffects.LEVITATION, 300, 0, false, false))

            hgPlayer.checkUsesForCooldown(kit, 1)
        }

        onHitPlayer { hgPlayer, kit, clickedPlayer ->
            if (clickedPlayer.hgPlayer.isNeo) return@onHitPlayer
            clickedPlayer.addEffect(MobEffectInstance(MobEffects.LEVITATION, 75, 0, false, false))

            hgPlayer.checkUsesForCooldown(kit, maxUses)
        }
    }
}