package org.adw39.mypaperplugin.utils

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.util.BlockVector
import kotlin.math.sign



// 自分と同じブロックが連鎖的に繋がっているのを検知する
fun blockChainSearch(block: Block): List<BlockVector> {
    val type = block.type
    val world = block.world
    // 中心を取得
    val centerVec = BlockVector(block.x, block.y, block.z)
    // 中心の周りを検知する
    val res = mutableListOf<BlockVector>()
    blockAroundSearch(centerVec).filter {
        it.toLocation(world).block.type == type
    }.forEach {
        res += blockForwardSearch(centerVec, it).filter { filterIt ->
            filterIt.toLocation(world).block.type == type
        }
    }
    return res
}

// previous(手前)とcurrent(対象ブロック、現在)から、探索が必要なブロックを取得する
fun blockForwardSearch(previousVec: BlockVector, currentVec: BlockVector): List<BlockVector> {
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

// 自分の周りのブロックを取得する
fun blockAroundSearch(currentVec: BlockVector): List<BlockVector> {
    val res = mutableListOf<BlockVector>()
    for (x in -1..1) {
        for (y in -1..1) {
            for (z in -1..1) {
                if (x != 0 || y != 0 || z != 0) {
                    res.add(BlockVector(x + currentVec.blockX, y + currentVec.blockY, z + currentVec.blockZ))
                }
            }
        }
    }
    return res
}