package org.adw39.mypaperplugin

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


class PlayerListener: Listener{
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("Welcome to the server!")
    }

//    @EventHandler
//    fun onBlockBreak(event: BlockBreakEvent) {
//        IkkatuHakai.ikkatuHakai(event.block)
//    }
}