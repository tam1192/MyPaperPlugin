package org.adw39.mypaperplugin.utils

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.util.BlockVector
import org.bukkit.util.Vector
import kotlin.math.sign

//fun blockChainSearch(centerBlock: Block) {
//    val world = centerBlock.world
//    val
//
//
//}

// previous(手前)とcurrent(対象ブロック、現在)から、自分と同じブロックを取得する
fun blockForwardSearch(previousVec: BlockVector, current: Block): List<BlockVector> {
    val targetBlockType = current.type
    val world = current.world
    val currentVec = BlockVector(current.x, current.y, current.z)
    return blockForwardSearchTarget(previousVec, currentVec).filter {
        val locBlock = it.toLocation(world).block
        locBlock.type === targetBlockType
    }
}

// previous(手前)とcurrent(対象ブロック、現在)から、探索が必要なブロックを取得する
fun blockForwardSearchTarget(previousVec: BlockVector, currentVec: BlockVector): List<BlockVector> {
    // どの方向に進んでるかを取得する
    val direction = run {
        // 現在と手前の差分で、進んでる方向を26方向で表す
        val dif = currentVec.clone().subtract(previousVec)
        Triple(sign(dif.x).toInt(), sign(dif.y).toInt(), sign(dif.z).toInt())
    }

    // currentの1つ外側に対してブロックを設置する。
    val res = mutableSetOf<BlockVector>()

    // 次に探索&進むべき方向を表す
    for (a in -1..1) {
        for (b in -1..1) {
            // x軸方向に進んでる
            if (direction.first != 0) { //1
                res.add(BlockVector(direction.first + currentVec.blockX, a + currentVec.blockY, b + currentVec.blockZ))
            }
            // y軸方向に進んでる
            if (direction.second != 0) { // 1
                res.add(BlockVector(a + currentVec.blockX, direction.second + currentVec.blockY, b + currentVec.blockZ))
            }
            // z軸方向に進んでる
            if (direction.third != 0) { // 1
                res.add(BlockVector(a + currentVec.blockX, b + currentVec.blockY, direction.third + currentVec.blockZ))
            }
        }
    }
    return res.toList()
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