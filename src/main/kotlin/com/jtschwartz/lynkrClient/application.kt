//*******************************************************************************
//
//      filename:  application.kt
//
//   description:  Handles the startup of all program elements and concurrent communication
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T.
//
//******************************************************************************
package com.jtschwartz.lynkrClient

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

object ChannelManager {
	val access = Channel<String>()
	val keystroke = Channel<String>()
	val media = Channel<String>()
	val power = Channel<String>()
	val volume = Channel<String>()
}

fun main() {
	Access; Keystroke; Media; Power; Volume
	
	for (module in listOf(
		Pair(ChannelManager.access, ::accessParser),
		Pair(ChannelManager.keystroke, {payload: String -> Keystroke.run(payload)}),
		Pair(ChannelManager.media, ::mediaParser),
		Pair(ChannelManager.power, ::powerParser),
		Pair(ChannelManager.volume, ::volumeParser))) {
		val (channel, parser) = module
		launchCoroutine(channel, parser)
	}
	
	BluetoothServer.runServer()
	//	launch<Window>(args)
}

private fun launchCoroutine(channel: Channel<String>, function: (String) -> Unit) {
	GlobalScope.launch {
		while (true) function(channel.receive())
	}
}

private fun accessParser(payload: String) {
	when (payload) {
		"logout" -> Access.logout()
		"lock"   -> Access.lock()
		else     -> error("Access Cannot Handle: $payload")
	}
}

private fun mediaParser(payload: String) {
	when (payload) {
		"next"      -> Media.nextTrack()
		"playpause" -> Media.togglePlay()
		"previous"  -> Media.prevTrack()
		else        -> error("Media Cannot Handle: $payload")
	}
}

private fun powerParser(payload: String) {
	when (payload) {
		"shutdown"  -> Power.shutdown()
		"restart"   -> Power.restart()
		"hibernate" -> Power.hibernate()
		else        -> error("Power Cannot Handle: $payload")
	}
}

private fun volumeParser(payload: String) {
	when (payload) {
		"increase" -> Volume.increase()
		"decrease" -> Volume.decrease()
		"mute"     -> Volume.toggleMute()
		else       -> try {
			Volume.setLevel(payload.toInt())
		} catch (ex: NumberFormatException) {
			ex.printStackTrace()
		}
	}
}