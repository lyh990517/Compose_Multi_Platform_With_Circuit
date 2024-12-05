package compose.project.demo.di

import com.slack.circuit.foundation.Circuit
import compose.project.demo.circuit.createPresenterFactory
import compose.project.demo.circuit.createUiFactory
import compose.project.demo.domain.TestUseCase
import compose.project.demo.feature.home.HomePresenter
import compose.project.demo.feature.home.HomeScreen
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val circuitModule = module {
    createPresenterFactory<HomeScreen, HomePresenter> { navigator, screen ->
        HomePresenter(screen as HomeScreen, navigator, get())
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

val useCaseModule = module {
    single { TestUseCase() }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(circuitModule)
        modules(useCaseModule)
    }
}
