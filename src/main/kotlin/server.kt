import java.io.*
import javax.bluetooth.*
import javax.bluetooth.RemoteDevice
import javax.bluetooth.UUID
import javax.microedition.io.Connector
import javax.microedition.io.StreamConnection
import javax.microedition.io.StreamConnectionNotifier

fun main(args: Array<String>) {
    println("Began")
    val localDevice: LocalDevice = LocalDevice.getLocalDevice()
    println("Address: " + localDevice.bluetoothAddress)
    println("Name: " + localDevice.friendlyName)
    val sampleSPPServer = BluetoothServer()
    while (true) {
        sampleSPPServer.startServer()
    }
}

class BluetoothServer {
    // start server
    @Throws(IOException::class)
    fun startServer() {
        val uuid = UUID("C820A3480E0E11EA8D71362B9E155667", false)
        val connectionString = "btspp://localhost:$uuid;name=LynkrSPPServer"
        val streamConnNotifier: StreamConnectionNotifier = Connector.open(connectionString) as StreamConnectionNotifier

        println("\nServer Started. Waiting for clients to connect...")
        val connection: StreamConnection = streamConnNotifier.acceptAndOpen()

        val dev: RemoteDevice = RemoteDevice.getRemoteDevice(connection)
        println("Remote device address: " + dev.bluetoothAddress)
        println("Remote device name: " + dev.getFriendlyName(true))

        val inStream: InputStream = connection.openInputStream()
        val bReader = BufferedReader(InputStreamReader(inStream))
        val lineRead = bReader.readLine()
        println("Message from mobile device: $lineRead")

        val outStream: OutputStream = connection.openOutputStream()
        val pWriter = PrintWriter(OutputStreamWriter(outStream))
        println("Sending response ($RESPONSE)")
        pWriter.write(RESPONSE + "\r\n")
        pWriter.flush()
        pWriter.close()
        streamConnNotifier.close()
    }

    companion object {
        private const val RESPONSE = "Greetings from serverland"
    }
}