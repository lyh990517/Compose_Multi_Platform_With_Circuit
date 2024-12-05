package compose.project.demo.circuit

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import compose.project.demo.Parcelize

@Parcelize
data class HomeScreen(
    val name: String
) : Screen {
    data class State(
        val state: String
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent
}