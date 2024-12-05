package compose.project.demo

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import compose.project.demo.circuit.HomePresenter
import compose.project.demo.circuit.HomePresenterFactory
import compose.project.demo.circuit.HomeScreenUiFactory
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single { KoinDummy() }

    factory<Presenter.Factory> {
        HomePresenterFactory { navigator, screen ->
            HomePresenter(
                screen,
                navigator
            )
        }
    }
    factory<Ui.Factory> { HomeScreenUiFactory() }
    single {
        Circuit.Builder()
            .addUiFactory(get())
            .addPresenterFactory(get())
            .build()
    }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule)
    }
}