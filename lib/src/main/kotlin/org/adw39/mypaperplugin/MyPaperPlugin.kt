package org.adw39.mypaperplugin

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
        config.addDefault("targetBlock", mutableListOf<Material>())
        config.options().copyDefaults(true)
        saveConfig()

        Bukkit.getPluginManager().registerEvents(PlayerListener(), this)
        logger.info("MyPaperPlugin enabled")

        getCommand("test")?.setExecutor(CommandListener())
            ?: run {
                logger.severe("No CommandListener was set!")
                Bukkit.shutdown()
            }
        getCommand("eyesmash")?.setExecutor(EyeSmash())
            ?: run {
                logger.severe("No CommandListener was set!")
                Bukkit.shutdown()
            }
        getCommand("eyeexplosion")?.setExecutor(EyeExplosion())
            ?: run {
                logger.severe("No CommandListener was set!")
                Bukkit.shutdown()
            }
        getCommand("ikkatuhakai")?.setExecutor(IkkatuHakai())
            ?: run {
                logger.severe("No CommandListener was set!")
                Bukkit.shutdown()
            }
    }
    override fun onDisable() {
        saveConfig()
    }
}