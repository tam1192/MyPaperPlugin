package org.adw39.mypaperplugin.commands

import org.adw39.mypaperplugin.MyPaperPlugin
import org.adw39.mypaperplugin.utils.blockAroundSearch
import org.adw39.mypaperplugin.utils.blockForwardSearch
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.util.BlockVector

class ExVector: TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val fixArgs = args.map{ it.lowercase() }.toTypedArray()
        return when {
            args.size == 1 && fixArgs[0] == "location_vs_vector" && sender is Player -> {
                val loc = sender.location
                val vec = sender.location.toVector()
                sender.sendMessage("loc: ${loc.world}, ${loc.x}, ${loc.y}, ${loc.z}\nvec: ---, ${vec.x}, ${vec.y}, ${vec.z}")
                true
            }
            args.size == 1 && fixArgs[0] == "distance_target_block" -> {
                sender.sendMessage("normal block")
                true
            }
            args.size == 1 && fixArgs[0] == "test1" && sender is Player -> {
                val target = sender.getTargetBlock(null, 10)
                blockAroundSearch(target)
                true
            }
            args.size == 1 && fixArgs[0] == "test2" && sender is Player -> {
                val world = sender.world
                val target = run {
                    val block = sender.getTargetBlock(null, 10)
                    BlockVector(block.x, block.y, block.z)
                }
                val center= run {
                    val block = sender.location
                    BlockVector(block.blockX, block.blockY+1, block.blockZ)
                }
                // targetより外側1ブロックの向きのリストを取得する
                blockForwardSearch(center, target).forEach {
                    it.toLocation(world).block.type = Material.GLASS
                }
                true
            }
            args.size == 2 && fixArgs[0] == "distance_target_block" && fixArgs[1] == "normal" && sender is Player  -> {
                val player = sender.location.toVector()
                val targetBlock = sender.getTargetBlock(null, 100).location.toVector()
                sender.sendMessage("distance: ${player.distance(targetBlock)}")
                true
            }
            args.size == 2 && fixArgs[0] == "distance_target_block" && fixArgs[1] == "block" && sender is Player  -> {
                val player = sender.location.toVector().toBlockVector()
                val targetBlock = sender.getTargetBlock(null, 100).location.toVector().toBlockVector()
                sender.sendMessage("distance: ${player.distance(targetBlock)}")
                true
            }
            else -> {
                sender.sendMessage("location_vs_vector distance_target_block test1")
                false
            }
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        return when {
            args.size == 2 && args[0] == "location_vs_vector" -> ""
            args.size == 2 && args[0] == "distance_target_block" -> "normal,block"
            args.size == 1 && args[0] == "" -> "location_vs_vector,distance_target_block,test1,test2"
            else -> ""
        }.split(",").toMutableList()
    }
}
