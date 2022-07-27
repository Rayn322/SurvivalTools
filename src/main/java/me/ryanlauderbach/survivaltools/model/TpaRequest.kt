package me.ryanlauderbach.survivaltools.model

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

class TpaRequest(requester: Player, destination: Player) {
	private val requesterId: UUID = requester.uniqueId
	private val destinationId: UUID = destination.uniqueId
	
	fun request() {
		val requester = Bukkit.getPlayer(requesterId)
		val destination = Bukkit.getPlayer(destinationId)
		
		requester?.sendMessage(ChatColor.GREEN.toString() + "Sent teleport request to " + ChatColor.YELLOW + destination?.name + ChatColor.GREEN + ".")
		
		val clickEvent = ClickEvent.runCommand("/tpa accept " + requester?.name)
		val hoverEvent = HoverEvent.showText(Component.text("Accept request from " + requester?.name))
		val textComponent = Component.text()
			.content(ChatColor.YELLOW.toString() + requester?.name)
			.append(Component.text(ChatColor.GREEN.toString() + " has sent you a teleport request."))
			.build()
		val clickCommand = Component.text(ChatColor.GREEN.toString() + "Type ")
			.append(
				Component.text(ChatColor.YELLOW.toString() + "/tpa accept " + requester?.name).clickEvent(clickEvent).hoverEvent(hoverEvent)
			)
			.append(Component.text(ChatColor.GREEN.toString() + " to accept."))
		
		destination?.sendMessage(textComponent)
		destination?.sendMessage(clickCommand)
	}
	
	fun accept() {
		val requester = Bukkit.getPlayer(requesterId)
		val destination = Bukkit.getPlayer(destinationId)
		
		if (!isOnline()) {
			destination?.sendMessage(ChatColor.RED.toString() + "The player who requested to teleport is now offline.")
		}
		
		destination?.let { requester?.teleport(it) }
		
		destination?.sendMessage(ChatColor.GREEN.toString() + "Teleported to " + ChatColor.YELLOW + requester?.name + ChatColor.GREEN + ".")
		requester?.sendMessage(ChatColor.GREEN.toString() + "Teleported " + ChatColor.YELLOW + destination?.name + ChatColor.GREEN + " to you.")
	}
	
	fun deny() {
		val requester = Bukkit.getPlayer(requesterId)
		val destination = Bukkit.getPlayer(destinationId)
		
		destination?.sendMessage(ChatColor.RED.toString() + "Teleport request from " + ChatColor.YELLOW + requester?.name + ChatColor.RED + " denied.")
		requester?.sendMessage(ChatColor.RED.toString() + "Teleport request to " + ChatColor.YELLOW + destination?.name + ChatColor.RED + " denied.")
	}
	
	fun isSimilar(requester: Player, destination: Player): Boolean {
		return (requester.uniqueId == this.requesterId) && (destination.uniqueId == this.destinationId)
	}
	
	private fun isOnline(): Boolean {
		return (Bukkit.getPlayer(requesterId)?.isOnline == true) && (Bukkit.getPlayer(destinationId)?.isOnline == true)
	}
}