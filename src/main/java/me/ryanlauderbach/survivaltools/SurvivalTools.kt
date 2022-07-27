package me.ryanlauderbach.survivaltools

import me.ryanlauderbach.survivaltools.command.Find
import me.ryanlauderbach.survivaltools.command.Home
import me.ryanlauderbach.survivaltools.command.Tpa
import me.ryanlauderbach.survivaltools.listener.DimensionNameColor
import org.bukkit.plugin.java.JavaPlugin

class SurvivalTools : JavaPlugin() {
	override fun onEnable() {
		plugin = this
		getCommand("home")!!.setExecutor(Home())
		getCommand("find")!!.setExecutor(Find())
		getCommand("tpa")!!.setExecutor(Tpa())
		getCommand("tpa")!!.tabCompleter = Tpa()
		server.pluginManager.registerEvents(DimensionNameColor(), this)
	}
	
	companion object {
		var plugin: SurvivalTools? = null
	}
}
