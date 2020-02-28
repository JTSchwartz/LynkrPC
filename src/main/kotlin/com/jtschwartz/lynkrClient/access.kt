//*******************************************************************************
//
//      filename:  access.kt
//
//   description:  com.jtschwartz.lynkrClient.Access control module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T.
//
//******************************************************************************
package com.jtschwartz.lynkrClient

object Access {
	fun logout() {
		Executioner.run("shutdown /l")
	}
	
	fun lock() {
		Executioner.run("rundll32.exe user32.dll,LockWorkStation")
	}
}