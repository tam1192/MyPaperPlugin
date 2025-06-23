package org.adw39.mypaperplugin

import org.bukkit.block.Block
import org.bukkit.util.BlockVector

fun ikkatuHakai(block: Block) {
    val targetBlock = MyPaperPlugin.instance.config.getStringList("targetBlock")
    // targetBlockの中に存在すれば
//    if (block.type.name.lowercase() in targetBlock) {
//        block.breakNaturally()
//        }
//    }

}

// 前方向such
fun fowardSuch(centerBlock: Block, target: BlockVector) {
    val suchBlockType = centerBlock.type



}