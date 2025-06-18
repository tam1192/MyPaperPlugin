package org.adw39.mypaperplugin

import org.adw39.mypaperplugin.commands.EyeExplosion
import org.adw39.mypaperplugin.commands.EyeSmash
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

class MyPaperPlugin: JavaPlugin() {
    override fun onEnable() {
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

    }
}