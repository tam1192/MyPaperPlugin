package org.adw39.mypaperplugin

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.awt.TextComponent


class PlayerListener: Listener{
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("Welcome to the server!")
    }
}