package compose.project.demo.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    state: HomeScreen.State,
    modifier: Modifier = Modifier
) {
    Text(state.state)
}