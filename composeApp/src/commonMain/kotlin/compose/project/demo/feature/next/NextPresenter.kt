package compose.project.demo.feature.next

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import compose.project.demo.feature.NextScreen

class NextPresenter(
    private val screen: NextScreen,
    private val navigator: Navigator
) : Presenter<NextScreen.State> {

    @Composable
    override fun present(): NextScreen.State {
        return NextScreen.State(
            state = "dataFromHome: ${screen.dataFromHome}",
            eventSink = {
                navigator.pop()
            }
        )
    }
}