import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import org.gradle.api.tasks.bundling.Jar

buildscript {
	repositories {
		mavenCentral()
	}
	
	dependencies {
		classpath("no.tornado:fxlauncher-gradle-plugin:1.0.20")
	}
}

plugins {
	application
    kotlin("jvm") version "1.3.60"
}

application {
	mainClassName = "com.jtschwartz.lynkrClient.ApplicationKt"
}

repositories {
	mavenCentral()
}

group = "com.jtschwartz"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
	implementation(files("lib/SetVol.exe"))
	implementation(files("lib/prev.exe"))
	implementation(files("lib/playpause.exe"))
	implementation(files("lib/next.exe"))
	implementation(files("lib/bluecove-2.1.1.jar"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
	compile("no.tornado:tornadofx:1.7.20")
}

//tasks.withType<Jar> {
//	manifest {
//		attributes["Main-Class"] = application.mainClassName
//	}
//
//	from(configurations.runtime.get().map {if (it.isDirectory) it else zipTree(it)})
//}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}

apply {
	plugin("no.tornado.fxlauncher")
}

//configure<FXLauncherExtension> {
//	applicationVendor = "Jacob Schwartz (jacob@jtschwartz.com)"
//	// Base URL where you will host the application artifacts
//	applicationUrl = "https://dist.jtschwartz.dev/ktPostfixCalculator"
//	deployTarget = "root@3.208.210.5:/var/www/dist/ktPostfixCalculator"
//	applicationMainClass = "ApplicationKt"
//	acceptDowngrade = false
//}