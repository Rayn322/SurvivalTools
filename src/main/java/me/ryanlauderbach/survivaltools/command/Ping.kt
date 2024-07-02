package me.ryanlauderbach.survivaltools.command

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Ping : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (args == null || args.isEmpty()) {
            sender.sendMessage(ChatColor.RED.toString() + "Please specify a player")
            return true
        }

        val player = Bukkit.getPlayer(args[0])
        if (player == null) {
            sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
        } else {
            sender.sendMessage(ChatColor.YELLOW.toString() + player.name + "'s " + ChatColor.GREEN + "ping: " + player.ping + "ms")
        }

        return true
    }
}