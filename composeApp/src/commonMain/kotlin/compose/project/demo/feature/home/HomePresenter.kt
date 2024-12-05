package compose.project.demo.feature.home

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import compose.project.demo.domain.TestUseCase

class HomePresenter(
    private val screen: HomeScreen,
    private val navigator: Navigator,
    private val testUseCase: TestUseCase
) : Presenter<HomeScreen.State> {

    @Composable
    override fun present(): HomeScreen.State {

        return HomeScreen.State(
            state = "${testUseCase.hello()}~ circuit"
        )
    }
}