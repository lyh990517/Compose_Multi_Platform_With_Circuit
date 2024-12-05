package compose.project.demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.slack.circuit.foundation.Circuit
import org.koin.compose.getKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeDemo",
    ) {
        val koin = getKoin()
        val circuit: Circuit = koin.get()

        App(circuit)
    }
}