package compose.project.demo.feature.nested2

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import compose.project.demo.feature.NestedScreen2
import compose.project.demo.feature.NextScreen

class Nested2Presenter(
    private val screen: NestedScreen2,
    private val navigator: Navigator
) : Presenter<NestedScreen2.State> {

    @Composable
    override fun present(): NestedScreen2.State {
        return NestedScreen2.State(
            state = "nested screen2",
            eventSink = { event ->
                when (event) {
                    NestedScreen2.Event.GoToNext -> {
                        navigator.goTo(NextScreen("hello im nested2"))
                    }
                }
            }
        )
    }
}