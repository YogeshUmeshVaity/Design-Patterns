package bluetoothspeakerexample

interface UsbAudio {
    fun supplyAudio()
}

interface BluetoothAudio {
    fun broadcastAudio()
}

class BluetoothSpeaker {
    fun connect(bluetooth: BluetoothAudio) {
        bluetooth.broadcastAudio()
    }
}

class UsbToBluetoothAudioAdapter(private val usb: UsbAudio) : BluetoothAudio {
    override fun broadcastAudio() = usb.supplyAudio()
}

fun main() {
    val usbAudio = object : UsbAudio {
        override fun supplyAudio() {
            println("Supplying audio to USB")
        }
    }
    val usbToBluetoothAudioAdapter = UsbToBluetoothAudioAdapter(usbAudio)
    val bluetoothSpeaker = BluetoothSpeaker()
    bluetoothSpeaker.connect(usbToBluetoothAudioAdapter)
}