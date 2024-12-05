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

class HomePresenterFactory(
    private val presenterFactory: (Navigator, HomeScreen) -> HomePresenter,
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is HomeScreen -> presenterFactory(navigator, screen)
            else -> null
        }
    }
}