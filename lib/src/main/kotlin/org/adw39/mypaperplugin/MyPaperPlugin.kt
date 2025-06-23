package org.adw39.mypaperplugin

import org.adw39.mypaperplugin.commands.ExVector
import org.adw39.mypaperplugin.commands.EyeExplosion
import org.adw39.mypaperplugin.commands.EyeSmash
import org.adw39.mypaperplugin.commands.IkkatuHakai
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin

class MyPaperPlugin: JavaPlugin() {
    companion object {
        lateinit var instance: MyPaperPlugin
            private set
    }

    private val config = getConfig()
    override fun onEnable() {
        instance = this
        config.addDefault("targetBlock", mutableListOf<String>())
        config.options().copyDefaults(true)
        saveConfig()

        Bukkit.getPluginManager().registerEvents(PlayerListener(), this)
        logger.info("MyPaperPlugin enabled")

        getCommand("test")?.setExecutor(CommandListener())
        getCommand("eyesmash")?.setExecutor(EyeSmash())
        getCommand("eyeexplosion")?.setExecutor(EyeExplosion())
        getCommand("ikkatuhakai")?.setExecutor(IkkatuHakai())
        getCommand("exvector")?.setExecutor(ExVector())

    }
    override fun onDisable() {
        saveConfig()
    }
}