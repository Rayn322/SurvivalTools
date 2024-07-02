package me.ryanlauderbach.survivaltools.command

import me.ryanlauderbach.survivaltools.SurvivalTools
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Find : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args?.size!! > 0) {
                val playerToFind = Bukkit.getPlayer(args[0])
                if (playerToFind == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
                    return true
                }

                playerToFind.isGlowing = true
                playerToFind.sendMessage(ChatColor.GREEN.toString() + sender.name + " made you glow.")

                Bukkit.getScheduler()
                    .runTaskLater(SurvivalTools.plugin!!, Runnable { playerToFind.isGlowing = false }, 100)
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "Please enter a player.")
            }
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "Only players can use this command.")
        }

        return true
    }
}
