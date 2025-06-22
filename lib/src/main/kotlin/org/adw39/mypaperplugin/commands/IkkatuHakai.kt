package org.adw39.mypaperplugin.commands

import org.adw39.mypaperplugin.MyPaperPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.Material
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.logging.Logger

class IkkatuHakai: TabExecutor {

    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>
    ): MutableList<String>? {
        val args = p3.iterator()
        val arg1 = args.next()
        if (arg1.isBlank()) {
            val result: MutableList<String> = mutableListOf()
            result.add("add")
            result.add("del")
            result.add("list")
            return result
        }
        return when(arg1) {
            // 極力lowercaseで登録させる
            "add" -> {
                Material.entries.filter { it.isBlock }.map { it.name.lowercase() }.toMutableList()
            }
            // リストの中から選ばせる
            "del" -> {
                MyPaperPlugin.instance.config.getStringList("targetBlock")
            }
            else -> null
        }
    }

    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        val args = p3.iterator()
        val arg1 = args.next()
        if (arg1.isBlank()) {return false}
        val targetBlock = MyPaperPlugin.instance.config.getStringList("targetBlock")
        return when(arg1) {
            "add", "del" -> {
                // 存在しなければ、プレイヤー目の前のブロックを取得してみる
                val val1 = kotlin.runCatching { args.next() }.getOrElse {
                    if (sender is Player) {
                        val val1 = sender.getTargetBlock(null, 10).type
                        if (val1 != Material.AIR) {
                            val1.name.lowercase()
                        } else {
                            return false
                        }
                    } else {
                        return false
                    }
                }.lowercase()
                // マテリアルかつblockIdであることを確認する
                Material.getMaterial(val1.uppercase()).also {
                    if ( it === null ) {
                        sender.sendMessage("このブロックidは存在しません")
                        return true
                    } else if (!it.isBlock) {
                        sender.sendMessage("これはブロックidではありません")
                        return true
                    }
                }
                // すでにリストに登録されているか
                val isExistTargetBlock = val1 in targetBlock
                // add or delにより挙動が変わる
                if (arg1=="add") {
                    // リストに存在しなければ追加
                    if (!isExistTargetBlock) {
                        MyPaperPlugin.instance.config.set("targetBlock", (targetBlock + val1))
                        sender.sendMessage("${val1}を登録しました")
                        true
                    } else {
                        sender.sendMessage("このブロックidはすでに登録されています")
                        true
                    }
                } else {
                    // リストに存在すれば削除
                    if (isExistTargetBlock) {
                        targetBlock.filter { it != val1 }
                            .also { MyPaperPlugin.instance.config.set("targetBlock", it) }
                        sender.sendMessage("${val1}を削除しました。")
                        true
                    } else {
                        sender.sendMessage("このブロックidはすでに削除されています")
                        true
                    }
                }

            }
            "list" -> {
                if (targetBlock.isEmpty()) {
                    sender.sendMessage("ブロックが登録されていません。")
                    true
                } else {
                    // - で区切って表示
                    var sendMessage = "登録済みのブロック\n\t- "
                    sendMessage += targetBlock.joinToString("\n\t- ")
                    sender.sendMessage(sendMessage)
                    true
                }
            }
            else -> false
        }
    }
}