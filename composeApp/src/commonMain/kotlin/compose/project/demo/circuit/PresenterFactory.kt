package compose.project.demo.circuit

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import org.koin.core.module.Module

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

@Suppress("UNCHECKED_CAST")
fun <SCREEN : Screen, PRESENTER : Presenter<*>> Module.createPresenterFactory(presenter: (Navigator, Screen) -> Presenter<*>) {
    factory<Presenter.Factory> {
        PresenterFactory<SCREEN, PRESENTER> { navigator, screen ->
            presenter(navigator, screen) as PRESENTER
        }
    }
}