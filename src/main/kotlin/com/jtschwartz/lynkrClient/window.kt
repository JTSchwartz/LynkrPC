package com.jtschwartz.lynkrClient

import tornadofx.*
import javafx.scene.image.Image

class Window : App(Image("lynkrLogo.png"), Gui::class)

class Gui : View("Lynkr") {
	override val root = borderpane {
		
	}
	
	init {
		with(root) {
			prefWidth = 700.0
		}
	}
}
