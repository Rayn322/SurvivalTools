package me.ryanlauderbach.survivaltools.listener

import io.papermc.paper.event.player.AsyncChatDecorateEvent
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class ChatFormatter : Listener {
	
	@EventHandler
	fun onChatMessage(event: AsyncChatDecorateEvent) {
		val miniMessage = MiniMessage.miniMessage()
		val plainMessage = PlainTextComponentSerializer.plainText().serialize(event.originalMessage())
		event.result(miniMessage.deserialize(plainMessage))
	}
}
