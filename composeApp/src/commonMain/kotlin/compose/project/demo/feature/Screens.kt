package compose.project.demo.feature

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import compose.project.demo.Parcelize
import compose.project.demo.feature.HomeScreen.Event

@Parcelize
data object HomeScreen : Screen {
    data class State(
        val state: String,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object GoToNext : Event
    }
}

@Parcelize
data class NextScreen(
    val dataFromHome: String
) : Screen {
    data class State(
        val state: String,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object GoToBack : Event
    }
}