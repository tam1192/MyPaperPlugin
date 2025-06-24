package org.adw39.mypaperplugin.utils

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player
import org.bukkit.util.Vector

//fun blockChainSearch(centerBlock: Block) {
//    val world = centerBlock.world
//    val
//
//
//}

fun blockForwardSearch(centerVec: Vector, targetVec: Vector): Vector {
    val direction = centerVec.subtract(targetVec)
    return direction.normalize()
}

fun blockAroundSearch(centerBlock: Block) {
    val world = centerBlock.world
    val centerVec = centerBlock.location.toVector()
    for (x in -1..1) {
        for (y in -1..1) {
            for (z in -1..1) {
                if (x != 0 || y != 0 || z != 0) {
                    Vector(x, y, z).add(centerVec).toLocation(world).block.type = Material.GLASS
                }
            }
        }
    }
}