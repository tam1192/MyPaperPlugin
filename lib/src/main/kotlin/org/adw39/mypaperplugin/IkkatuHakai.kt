package org.adw39.mypaperplugin

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.util.BlockVector
import kotlin.math.max

object IkkatuHakai {
    fun ikkatuHakai(block: Block) {
        val targetBlock = MyPaperPlugin.instance.config.getStringList("targetBlock")
        // targetBlockの中に存在すれば
        if (block.type.name.lowercase() in targetBlock) {
            block.breakNaturally()
            }
        }
    }

    fun radius(center: Location, target: Location, type: Material) {
        // 中心座標からどれくらい離れてるかを確認する。
        val distance = center.toVector().subtract(target.toVector()).toBlockVector()




    }
}