object Power {
    fun shutdown() {
        Executioner.run("shutdown /s")
    }

    fun restart() {
        Executioner.run("shutdown /r")
    }

    fun hibernate() {
        Executioner.run("shutdown /h")
    }
}