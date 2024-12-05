package compose.project.demo.feature.nested2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import compose.project.demo.feature.NestedScreen2

@Composable
fun Nested2ScreenUi(
    state: NestedScreen2.State,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(state.state)
        Button({
            state.eventSink(NestedScreen2.Event.GoToNext)
        }) {

        }
    }
}