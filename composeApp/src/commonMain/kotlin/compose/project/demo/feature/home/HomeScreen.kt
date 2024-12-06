package compose.project.demo.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slack.circuit.foundation.CircuitContent
import compose.project.demo.AppTheme
import compose.project.demo.ContentType
import compose.project.demo.feature.HomeScreen
import compose.project.demo.feature.NestedScreen1
import compose.project.demo.feature.NestedScreen2

@Composable
fun HomeScreenUi(
    state: HomeScreen.State,
    modifier: Modifier = Modifier
) {
    val screens = remember {
        listOf(
            NestedScreen1,
            NestedScreen2
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = AppTheme.contentType == ContentType.Single,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    screens.forEach { screen ->
                        NavigationBarItem(
                            selected = screen == state.displayedScreen,
                            onClick = {
                                state.eventSink(HomeScreen.Event.ChangeScreen(screen))
                            },
                            icon = { Icon(imageVector = Icons.Default.PlayArrow, "") }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        CircuitContent(
            screen = state.displayedScreen,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            onNavEvent = { navEvent -> state.eventSink(HomeScreen.Event.NestedNavEvent(navEvent)) })
    }
}