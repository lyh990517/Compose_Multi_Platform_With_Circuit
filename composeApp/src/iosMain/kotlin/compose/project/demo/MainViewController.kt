package compose.project.demo

import androidx.compose.ui.window.ComposeUIViewController
import com.slack.circuit.foundation.Circuit
import compose.project.demo.di.initKoin
import org.koin.compose.getKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    val koin = getKoin()
    val circuit: Circuit = koin.get()

    App(circuit)
}