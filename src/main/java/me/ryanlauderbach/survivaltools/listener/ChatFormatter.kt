package me.ryanlauderbach.survivaltools.listener

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatPreviewEvent

class ChatFormatter : Listener {
	
	@EventHandler
	fun onChatMessage(event: AsyncChatEvent) {
		val miniMessage = MiniMessage.miniMessage()
		val plainMessage = PlainTextComponentSerializer.plainText().serialize(event.message())
		val parsed: Component = miniMessage.deserialize(plainMessage)
		
		event.message(parsed)
	}
	
	@EventHandler
	fun onChatMessage(event: AsyncPlayerChatPreviewEvent) {
		val miniMessage = MiniMessage.miniMessage()
		val plainMessage = event.message
		val parsed: Component = miniMessage.deserialize(plainMessage)
		val serialized = LegacyComponentSerializer.legacySection().serialize(parsed)
		
		event.message = serialized
	}
}

