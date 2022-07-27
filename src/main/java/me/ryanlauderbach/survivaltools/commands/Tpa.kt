package me.ryanlauderbach.survivaltools.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Tpa : CommandExecutor {
	
	// Key: Player, Value: Their outgoing requests
	private val tpaRequests = HashMap<UUID, ArrayList<UUID>>()
	
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
		if (sender is Player) {
			if (args?.get(0) == "accept") {
				val target = Bukkit.getPlayer(args[1])
				if (target == null) {
					sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
					return true
				}
				
				if (tpaRequests[target.uniqueId]?.contains(sender.uniqueId) == true) {
					val requests = tpaRequests[target.uniqueId]!!
					requests.remove(sender.uniqueId)
					// unnecessary?
					tpaRequests[target.uniqueId] = requests
					
					target.teleport(sender)
					target.sendMessage(ChatColor.GREEN.toString() + "Teleported to " + ChatColor.YELLOW + sender.name + ChatColor.GREEN + ".")
					sender.sendMessage(ChatColor.GREEN.toString() + "Teleported " + ChatColor.YELLOW + target.name + ChatColor.GREEN + " to you.")
					
				} else {
					sender.sendMessage(ChatColor.RED.toString() + "You have no pending requests from this player.")
					return true
				}
				
			} else if (args?.get(0) == "deny") {
				val target = Bukkit.getPlayer(args[1])
				if (target == null) {
					sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
					return true
				}
				
				if (tpaRequests[target.uniqueId]?.contains(sender.uniqueId) == true) {
					val requests = tpaRequests[target.uniqueId]!!
					requests.remove(sender.uniqueId)
					// unnecessary?
					tpaRequests[target.uniqueId] = requests
					
					target.sendMessage(ChatColor.RED.toString() + "Teleport request to " + ChatColor.YELLOW + sender.name + ChatColor.GREEN + " denied.")
					sender.sendMessage(ChatColor.RED.toString() + "Teleport request " + ChatColor.YELLOW + target.name + ChatColor.GREEN +  " denied.")
					
				} else {
					sender.sendMessage(ChatColor.RED.toString() + "You have no pending requests from this player.")
					return true
				}
				
			} else {
				val target = Bukkit.getPlayer(args?.get(0) ?: "")
				if (target == null) {
					sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
					return true
				}
				
				if (tpaRequests[sender.uniqueId]?.contains(target.uniqueId) == true) {
					sender.sendMessage(ChatColor.RED.toString() + "You have already sent a request to this player.")
					return true
				} else {
					val requests = tpaRequests[sender.uniqueId] ?: ArrayList<UUID>()
					requests.add(target.uniqueId)
					tpaRequests[sender.uniqueId] = requests
					sender.sendMessage(ChatColor.GREEN.toString() + "Sent teleport request to " + ChatColor.YELLOW + target.name + ChatColor.GREEN + ".")
					
					val clickEvent = ClickEvent.runCommand("/tpa accept " + sender.name)
					val hoverEvent = HoverEvent.showText(Component.text("Accept request from " + sender.name))
					val textComponent = Component.text()
						.content(ChatColor.YELLOW.toString() + sender.name)
						.append(Component.text(ChatColor.GREEN.toString() + " has sent you a teleport request."))
						.build()
					val clickCommand = Component.text(ChatColor.GREEN.toString() + "Type ")
						.append(
							Component.text(ChatColor.YELLOW.toString() + "/tpa accept " + sender.name).clickEvent(clickEvent)
								.hoverEvent(hoverEvent)
						)
						.append(Component.text(ChatColor.GREEN.toString() + " to accept."))
					
					target.sendMessage(textComponent)
					target.sendMessage(clickCommand)
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED.toString() + "Only players can use this command.")
		}
		
		return true
	}
}
