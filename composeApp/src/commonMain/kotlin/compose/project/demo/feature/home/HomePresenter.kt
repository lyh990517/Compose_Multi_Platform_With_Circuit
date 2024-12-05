package compose.project.demo.feature.home

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen

class HomePresenter(
    private val screen: HomeScreen,
    private val navigator: Navigator
) : Presenter<HomeScreen.State> {

    @Composable
    override fun present(): HomeScreen.State {

        return HomeScreen.State(
            state = "hello circuit"
        )
    }
}

@Suppress("UNCHECKED_CAST")
class PresenterFactory<SCREEN : Screen, PRESENTER : Presenter<*>>(
    private val presenterFactory: (Navigator, SCREEN) -> PRESENTER
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*> {
        return presenterFactory(navigator, screen as SCREEN)
    }
}