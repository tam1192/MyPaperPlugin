package org.adw39.mypaperplugin

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

class MyPaperPlugin: JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage(Component.text("Welcome back!", NamedTextColor.GREEN))
    }
}