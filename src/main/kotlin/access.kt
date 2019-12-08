object Access {
    fun logout() {
        Executioner.run("shutdown /l")
    }

    fun lock() {
        Executioner.run("rundll32.exe user32.dll,LockWorkStation")
    }
}