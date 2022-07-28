package me.ryanlauderbach.survivaltools.command

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class Ping : TabExecutor {
	
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
		if (args == null || args.isEmpty()) {
			sender.sendMessage(ChatColor.RED.toString() + "Please specify a player")
			return true
		}
		
		val player = Bukkit.getPlayer(args[0])
		if (player == null) {
			sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
		} else {
			sender.sendMessage(ChatColor.YELLOW.toString() + player.name + "'s" + ChatColor.GREEN + "Ping: " + player.ping)
		}
		
		return true
	}
	
	override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String>? {
		return if (args?.size == 1) {
			val list = mutableListOf<String>()
			
			for (player in Bukkit.getOnlinePlayers()) {
				list.add(player.name)
			}
			
			list.filter { it.startsWith(args[0], true) } as MutableList<String>
		} else {
			null
		}
	}
}