package me.ryanlauderbach.survivaltools.listeners

import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent

class DimensionNameColor : Listener {
	@EventHandler
	fun onDimensionChange(event: PlayerChangedWorldEvent) {
		setName(event.player)
	}
	
	@EventHandler
	fun onServerJoin(event: PlayerJoinEvent) {
		setName(event.player)
	}
	
	private fun setName(player: Player) {
		val color: ChatColor = when (player.world.environment) {
			World.Environment.NORMAL -> ChatColor.GREEN
			World.Environment.NETHER -> ChatColor.RED
			World.Environment.THE_END -> ChatColor.BLUE
			World.Environment.CUSTOM -> ChatColor.DARK_PURPLE
			else -> ChatColor.GRAY
		}
		player.playerListName(Component.text(color.toString() + player.name))
	}
}