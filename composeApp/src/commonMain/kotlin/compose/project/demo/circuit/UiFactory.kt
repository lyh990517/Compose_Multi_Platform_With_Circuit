package compose.project.demo.circuit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import org.koin.core.module.Module


@Suppress("UNCHECKED_CAST")
class UiFactory<STATE : CircuitUiState>(
    private val uiFactory: @Composable (STATE, Modifier) -> Unit
) : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return ui<CircuitUiState> { state, modifier ->
            uiFactory(state as STATE, modifier)
        }
    }
}

fun <STATE : CircuitUiState> Module.createUiFactory(ui: @Composable (CircuitUiState, Modifier) -> Unit) {
    factory<Ui.Factory> {
        UiFactory<STATE> { state, modifier ->
            ui(state, modifier)
        }
    }
}