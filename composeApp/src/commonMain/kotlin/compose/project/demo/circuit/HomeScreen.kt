package compose.project.demo.circuit

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui


class HomeScreenUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is HomeScreen -> {
            ui<HomeScreen.State> { state, modifier ->
                HomeScreen(state, modifier)
            }
        }

        else -> null
    }
}

@Composable
fun HomeScreen(
    state: HomeScreen.State,
    modifier: Modifier = Modifier
) {
    Text(state.state)
}