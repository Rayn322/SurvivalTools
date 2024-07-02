package me.ryanlauderbach.survivaltools.command

import me.ryanlauderbach.survivaltools.listener.PreventInventoryInteraction
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class InventorySee : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args == null || args.isEmpty()) {
                sender.sendMessage(ChatColor.RED.toString() + "Please specify a player")
                return true
            }

            val target = Bukkit.getPlayer(args[0])
            if (target == null) {
                sender.sendMessage(ChatColor.RED.toString() + "Player not found.")
            } else {
                val playerInventory = target.inventory
                val inventory = Bukkit.createInventory(null, 45, Component.text("Inventory of " + target.name))

                for (i in 0..40) {
                    inventory.setItem(getChestIndex(i), playerInventory.getItem(i))
                }

                PreventInventoryInteraction.inventories.add(inventory)
                sender.openInventory(inventory)
            }
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "Only players can use this command.")
        }

        return true
    }

    private fun getChestIndex(index: Int): Int {
        return when (index) {
            // move hotbar to bottom
            in 0..8 -> {
                index + 36
            }

            // leave rest of inventory alone
            in 9..35 -> {
                index
            }

            // boots
            36 -> {
                3
            }

            // leggings
            37 -> {
                2
            }

            // chestplate
            38 -> {
                1
            }

            // helmet
            39 -> {
                0
            }

            // put offhand on top right
            else -> {
                8
            }
        }

    }
}