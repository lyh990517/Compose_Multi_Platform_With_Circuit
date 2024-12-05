package compose.project.demo.di

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import compose.project.demo.feature.home.HomePresenter
import compose.project.demo.feature.home.HomePresenterFactory
import compose.project.demo.feature.home.HomeScreenUiFactory
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
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