package org.adw39.mypaperplugin

import org.bukkit.Material
import org.bukkit.block.Block

object IkkatuHakai {
    fun ikkatuHakai(block: Block) {
        val targetBlock = MyPaperPlugin.instance.config.getList("targetBlock")?.filterIsInstance<Material>() ?: mutableListOf<Material>()
        if (block.type in targetBlock) {
            block.breakNaturally()
            for (i in 0..<26) {
                val x = (i % 3)-1
                val y = (i / 3 % 3)-1
                val z = (i / 9)-1
                val location = block.location.add(x.toDouble(), y.toDouble(), z.toDouble())
                ikkatuHakai(location.block)
            }
        }
    }
}