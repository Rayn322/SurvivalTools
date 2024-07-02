package me.ryanlauderbach.survivaltools.command

import me.ryanlauderbach.survivaltools.model.TpaRequest
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class Tpa : TabExecutor {

    private val tpaRequests = ArrayList<TpaRequest>()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args == null || args.isEmpty()) {
                sender.sendMessage(ChatColor.RED.toString() + "Please specify a player.")
                return true
            }

            if (args.size > 1) {
                val requester = Bukkit.getPlayer(args[1])
                if (requester == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
                    return true
                }

                val request = tpaRequests.find { it.isSimilar(requester, sender) }

                if (request == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "You have no pending requests from this player.")
                } else {
                    if (args[0] == "accept") {
                        request.accept()
                    } else if (args[0] == "deny") {
                        request.deny()
                    }
                    tpaRequests.remove(request)
                }

            } else {
                val destination = Bukkit.getPlayer(args[0])
                if (destination == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
                    return true
                }

                val request = TpaRequest(sender, destination)

                if (tpaRequests.find { it.isSimilar(sender, destination) } != null) {
                    sender.sendMessage(ChatColor.RED.toString() + "You have already sent a request to this player.")
                } else {
                    tpaRequests.add(request)
                    request.request()
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "Only players can use this command.")
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String>? {
        return if (sender is Player && args?.size == 1) {
            val list = mutableListOf("accept", "deny")

            for (player in Bukkit.getOnlinePlayers()) {
                list.add(player.name)
            }

            list.filter { it.startsWith(args[0], true) } as MutableList<String>
        } else {
            null
        }
    }
}
