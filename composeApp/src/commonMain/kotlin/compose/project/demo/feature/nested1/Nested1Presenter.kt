package compose.project.demo.feature.nested1

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import compose.project.demo.feature.NestedScreen1

class Nested1Presenter(
    private val screen: NestedScreen1,
    private val navigator: Navigator
) : Presenter<NestedScreen1.State> {

    @Composable
    override fun present(): NestedScreen1.State {
        return NestedScreen1.State(
            state = "nested screen1",
            eventSink = { event ->
                when (event) {
                    NestedScreen1.Event.GoToNext -> {

                    }
                }
            }
        )
    }
}