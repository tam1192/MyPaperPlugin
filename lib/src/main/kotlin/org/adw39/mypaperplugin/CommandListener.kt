package org.adw39.mypaperplugin

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class CommandListener: TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val argsIter = args.iterator()

        if (!argsIter.hasNext()) return false
        return when (argsIter.next()) {
            "echo" -> argX(sender, argsIter)
            else -> false
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        if (args.size == 1) {
            return listOf("echo").toMutableList()
        }

        return null // 他に補完候補がない場合は `null` を返す
    }

    private fun argX(sender:CommandSender, args:Iterator<String>): Boolean {
        sender.sendMessage(args.asSequence().joinToString(" "))
        return true
    }
}