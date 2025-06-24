package org.adw39.mypaperplugin.utils

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player
import org.bukkit.util.BlockVector
import org.bukkit.util.Vector
import kotlin.math.sign

//fun blockChainSearch(centerBlock: Block) {
//    val world = centerBlock.world
//    val
//
//
//}

fun blockForwardSearch(centerVec: BlockVector, targetVec: BlockVector): List<BlockVector> {
    // どっちの方向向いてるか確定させる
    val direction = run {
        val dif = centerVec.subtract(targetVec)
        Triple(sign(dif.x).toInt(), sign(dif.y).toInt(), sign(dif.z).toInt())
    }
    val res = mutableListOf<BlockVector>()

    // targetの1つ外側に対してブロックを設置する。
    for (a in -1..1) {
        for (b in -1..1) {
            if (direction.first != 0) {
                res.add(BlockVector(direction.first, a, b))
            }
            if (direction.second != 0) {
                res.add(BlockVector(a, direction.second, b))
            }
            if (direction.third != 0) {
                res.add(BlockVector(a, b, direction.third))
            }
        }
    }
    return res
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