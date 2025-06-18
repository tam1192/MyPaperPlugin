package org.adw39.mypaperplugin.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class EyeSmash: TabExecutor {
    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>
    ): MutableList<String>? {
        return null
    }

    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        val args = p3.iterator()
        // 破壊距離
        val smashDistance = args.next().toIntOrNull() ?: return false
        var smashCount = 0

        if (p0 is Player) {
            for (d in 0..< smashDistance) {
                // 視線先を...
                val targetBlock = p0.getTargetBlock(null, smashDistance)

                if (targetBlock.type != Material.AIR) {
                    targetBlock.breakNaturally()
                    smashCount++
                }
            }
            p0.sendMessage("§a $smashCount ブロック破壊しました！")
        }
        return true
    }
}