package compose.project.demo.feature.next

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.internal.BackHandler
import compose.project.demo.feature.NextScreen

@Composable
fun NextScreenUi(
    state: NextScreen.State,
    modifier: Modifier = Modifier
) {
    BackHandler {
        state.eventSink(NextScreen.Event.GoToBack)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(state.state)
    }
}