package me.ryanlauderbach.survivaltools

import me.ryanlauderbach.survivaltools.command.*
import me.ryanlauderbach.survivaltools.listener.ChatFormatter
import me.ryanlauderbach.survivaltools.listener.DimensionNameColor
import me.ryanlauderbach.survivaltools.listener.PreventInventoryInteraction
import org.bukkit.plugin.java.JavaPlugin

class SurvivalTools : JavaPlugin() {
    override fun onEnable() {
        plugin = this

        getCommand("home")!!.setExecutor(Home())
        getCommand("find")!!.setExecutor(Find())
        getCommand("tpa")!!.setExecutor(Tpa())
        getCommand("tpa")!!.tabCompleter = Tpa()
        getCommand("ping")!!.setExecutor(Ping())
        getCommand("ping")!!.tabCompleter = PlayerNameTabCompleter()
        getCommand("invsee")!!.setExecutor(InventorySee())
        getCommand("invsee")!!.tabCompleter = PlayerNameTabCompleter()

        server.pluginManager.registerEvents(DimensionNameColor(), this)
        server.pluginManager.registerEvents(PreventInventoryInteraction(), this)
        server.pluginManager.registerEvents(ChatFormatter(), this)
    }

    companion object {
        var plugin: SurvivalTools? = null
    }
}
