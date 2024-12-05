package compose.project.demo.di

import com.slack.circuit.foundation.Circuit
import compose.project.demo.circuit.createPresenterFactory
import compose.project.demo.circuit.createUiFactory
import compose.project.demo.domain.TestUseCase
import compose.project.demo.feature.HomeScreen
import compose.project.demo.feature.NestedScreen1
import compose.project.demo.feature.NestedScreen2
import compose.project.demo.feature.NextScreen
import compose.project.demo.feature.home.HomePresenter
import compose.project.demo.feature.home.HomeScreenUi
import compose.project.demo.feature.nested1.Nested1Presenter
import compose.project.demo.feature.nested1.Nested1ScreenUi
import compose.project.demo.feature.nested2.Nested2Presenter
import compose.project.demo.feature.nested2.Nested2ScreenUi
import compose.project.demo.feature.next.NextPresenter
import compose.project.demo.feature.next.NextScreenUi
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val circuitModule = module {
    createPresenterFactory { navigator, screen ->
        when (screen) {
            is HomeScreen -> HomePresenter(screen, navigator, get())
            is NextScreen -> NextPresenter(screen, navigator)
            is NestedScreen1 -> Nested1Presenter(screen, navigator)
            is NestedScreen2 -> Nested2Presenter(screen, navigator)
            else -> throw Exception("Invalid Screen Detected! :: $screen")
        }
    }

    createUiFactory { state, modifier ->
        when (state) {
            is HomeScreen.State -> HomeScreenUi(state, modifier)
            is NextScreen.State -> NextScreenUi(state, modifier)
            is NestedScreen1.State -> Nested1ScreenUi(state, modifier)
            is NestedScreen2.State -> Nested2ScreenUi(state, modifier)
        }
    }

    single {
        Circuit.Builder()
            .addUiFactories(getAll())
            .addPresenterFactories(getAll())
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
