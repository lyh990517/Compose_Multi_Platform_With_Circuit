package compose.project.demo.di

import com.slack.circuit.foundation.Circuit
import compose.project.demo.circuit.createPresenterFactory
import compose.project.demo.circuit.createUiFactory
import compose.project.demo.feature.home.HomePresenter
import compose.project.demo.feature.home.HomeScreen
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    createPresenterFactory<HomeScreen, HomePresenter> { navigator, screen ->
        HomePresenter(screen as HomeScreen, navigator)
    }

    createUiFactory<HomeScreen.State> { state, modifier ->
        HomeScreen(state as HomeScreen.State, modifier)
    }

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
