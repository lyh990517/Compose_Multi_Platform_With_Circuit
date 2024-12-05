package compose.project.demo.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import compose.project.demo.domain.TestUseCase
import compose.project.demo.feature.HomeScreen
import compose.project.demo.feature.NestedScreen1
import compose.project.demo.feature.NextScreen

class HomePresenter(
    private val screen: HomeScreen,
    private val navigator: Navigator,
    private val testUseCase: TestUseCase
) : Presenter<HomeScreen.State> {

    @Composable
    override fun present(): HomeScreen.State {
        var displayedScreen by remember { mutableStateOf<Screen>(NestedScreen1) }

        return HomeScreen.State(
            displayedScreen = displayedScreen,
            eventSink = { event ->
                when (event) {
                    is HomeScreen.Event.ChangeScreen -> displayedScreen = event.screen
                    is HomeScreen.Event.NestedNavEvent -> navigator.onNavEvent(event.navEvent)
                }
            }
        )
    }
}