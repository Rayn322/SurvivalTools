package me.ryanlauderbach.survivaltools.command

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class PlayerNameTabCompleter : TabCompleter {

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String>? {
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