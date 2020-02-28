//*******************************************************************************
//
//      filename:  Power.kt
//
//   description:  Power control module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T.
//
//******************************************************************************
package com.jtschwartz.lynkrClient

object Media {
	fun togglePlay() {
		mediaControl("playpause")
	}
	
	fun nextTrack() {
		mediaControl("next")
	}
	
	fun prevTrack() {
		mediaControl("prev")
	}
	
	private fun mediaControl(command: String) {
		val path = "${System.getProperty("user.dir")}\\lib"
		Executioner.run("$path\\$command.exe ")
	}
}