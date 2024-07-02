package me.ryanlauderbach.survivaltools.listener

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory

class PreventInventoryInteraction : Listener {

    companion object {
        val inventories = mutableListOf<Inventory>()
    }

    @EventHandler
    fun onInventoryInteraction(event: InventoryClickEvent) {
        if (inventories.contains(event.inventory)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        if (inventories.contains(event.inventory)) {
            inventories.remove(event.inventory)
            (event.player as Player).updateInventory()
        }
    }
}