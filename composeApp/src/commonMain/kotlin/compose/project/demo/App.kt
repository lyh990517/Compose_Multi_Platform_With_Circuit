package compose.project.demo

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import compose.project.demo.feature.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(circuit: Circuit) {
    DemoTheme {
        val backStack = rememberSaveableBackStack(root = HomeScreen)
        val navigator = rememberCircuitNavigator(backStack) {}

        CircuitCompositionLocals(circuit) {
            NavigableCircuitContent(navigator = navigator, backStack = backStack)
        }
    }
}