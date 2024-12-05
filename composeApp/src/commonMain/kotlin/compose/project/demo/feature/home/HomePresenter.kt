package compose.project.demo.feature.home

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import compose.project.demo.domain.TestUseCase
import compose.project.demo.feature.HomeScreen
import compose.project.demo.feature.NextScreen

class HomePresenter(
    private val screen: HomeScreen,
    private val navigator: Navigator,
    private val testUseCase: TestUseCase
) : Presenter<HomeScreen.State> {

    @Composable
    override fun present(): HomeScreen.State {

        return HomeScreen.State(
            state = "${testUseCase.hello()}~ circuit",
            eventSink = { event ->
                when (event) {
                    HomeScreen.Event.GoToNext -> navigator.goTo(NextScreen("helloNext"))
                }
            }
        )
    }
}