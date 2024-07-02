package me.ryanlauderbach.survivaltools.command

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Home : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            val bedSpawn = sender.bedSpawnLocation

            if (bedSpawn != null) {
                sender.teleport(bedSpawn)
                sender.sendMessage(ChatColor.GREEN.toString() + "Teleporting to spawn.")
            } else {
                sender.teleport(sender.world.spawnLocation)
                sender.sendMessage(ChatColor.GREEN.toString() + "No spawn location set. Teleporting to world spawn.")
            }

        } else {
            sender.sendMessage(ChatColor.RED.toString() + "Only players can be teleported.")
        }

        return true
    }
}
