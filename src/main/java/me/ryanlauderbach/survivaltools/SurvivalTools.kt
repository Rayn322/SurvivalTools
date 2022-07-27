package me.ryanlauderbach.survivaltools

import me.ryanlauderbach.survivaltools.commands.Find
import me.ryanlauderbach.survivaltools.commands.Home
import me.ryanlauderbach.survivaltools.commands.Tpa
import me.ryanlauderbach.survivaltools.listeners.DimensionNameColor
import org.bukkit.plugin.java.JavaPlugin

class SurvivalTools : JavaPlugin() {
	override fun onEnable() {
		plugin = this
		getCommand("home")!!.setExecutor(Home())
		getCommand("find")!!.setExecutor(Find())
		getCommand("tpa")!!.setExecutor(Tpa())
		server.pluginManager.registerEvents(DimensionNameColor(), this)
	}
	
	companion object {
		var plugin: SurvivalTools? = null
	}
}