package compose.project.demo.feature.home

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

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
