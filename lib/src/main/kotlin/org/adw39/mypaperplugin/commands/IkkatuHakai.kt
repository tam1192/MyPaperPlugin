package org.adw39.mypaperplugin.commands

import org.adw39.mypaperplugin.MyPaperPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.Material
import org.bukkit.Bukkit
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
            "add", "del" -> {
                Material.entries.map { it.name }.toMutableList()
            }
            else -> null
        }
    }

    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        val args = p3.iterator()
        val arg1 = args.next()
        if (arg1.isBlank()) {return false}
        val targetBlock = MyPaperPlugin.instance.config.getList("targetBlock")?.filterIsInstance<Material>() ?: mutableListOf<Material>()
        return when(arg1) {
            "add" -> {
                val val1 = kotlin.runCatching { args.next() }.getOrElse { return false }
                val material = Material.getMaterial(val1) ?: run {
                    sender.sendMessage("ブロックidが存在しません")
                    return true
                }
                if (targetBlock.find { it == material } == null) {
                    MyPaperPlugin.instance.config.set("targetBlock", (targetBlock + material))
                    true
                } else {
                    sender.sendMessage("このブロックidはすでに登録されています")
                    true
                }
            }
            "del" -> {
                val val1 = kotlin.runCatching { args.next() }.getOrElse { return false }
                val material = Material.getMaterial(val1) ?: run {
                    sender.sendMessage("ブロックidが存在しません")
                    return true
                }
                targetBlock.filter { it != material }.also {
                    MyPaperPlugin.instance.config.set("targetBlock", it)
                }
                true
            }
            "list" -> {
                var sendMessage = "登録済みのブロック\n"
                targetBlock.forEach {
                    sendMessage += "- ${it.name}\n"
                }
                sender.sendMessage(sendMessage)
                true
            }
            else -> false
        }
    }
}