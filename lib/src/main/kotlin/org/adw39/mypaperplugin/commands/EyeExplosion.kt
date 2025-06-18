package org.adw39.mypaperplugin.commands

import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Firework
import org.bukkit.entity.Player

class EyeExplosion: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        val player = sender
        val origin = player.eyeLocation              // 視線の起点
        val direction = origin.direction.normalize() // 向いてる方向ベクトル（単位ベクトル）

        val targetLocation = origin.clone().add(direction.multiply(5.0)) // 5ブロック先

        // 花火をスポーン
        val world = player.world
        val firework = world.spawn(targetLocation, Firework::class.java)

        // エフェクトの設定
        val meta = firework.fireworkMeta
        meta.addEffect(
            FireworkEffect.builder()
                .withColor(Color.RED)
                .withFade(Color.ORANGE)
                .with(FireworkEffect.Type.BALL_LARGE)
                .trail(true)
                .flicker(true)
                .build()
        )
        meta.power = 1
        firework.fireworkMeta = meta

        // デバッグ表示
        player.sendMessage("§e[DEBUG] 目の位置: ${origin.toVector()}")
        player.sendMessage("§e[DEBUG] 向き: ${direction}")
        player.sendMessage("§e[DEBUG] 花火位置: ${targetLocation.toVector()}")

        return true
    }

}