package compose.project.demo.feature.nested1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.project.demo.AppTheme
import compose.project.demo.feature.NestedScreen1

@Composable
fun Nested1ScreenUi(
    state: NestedScreen1.State,
    modifier: Modifier = Modifier
) {
    val layoutType = AppTheme.adaptiveLayoutType
    val contentType = AppTheme.contentType

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(layoutType.toString())
        Spacer(Modifier.height(30.dp))
        Text(contentType.toString())
    }
}