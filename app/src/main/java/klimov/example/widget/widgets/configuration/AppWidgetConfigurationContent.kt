package klimov.example.widget.widgets.configuration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppWidgetConfigurationContent(
    state: AppWidgetConfigurationState,
    isDynamicColorsAvailable: Boolean,
    onDynamicColorsCheckedChange: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars.union(WindowInsets.navigationBars))
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Toolbar(
            title = "Configuration Activity",
            onBackClick = onBackClick
        )

        if (isDynamicColorsAvailable) {
            CustomSwitch(
                checked = state.dynamicColorChecked,
                onCheckedChange = onDynamicColorsCheckedChange
            )
        } else {
            Text(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = "Unfortunately, dynamic colors are not available to you.",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { onSaveClick.invoke() }
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Save",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
private fun ColumnScope.CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange.invoke(!checked) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Dynamic colors",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )

        Switch(
            checked = checked,
            onCheckedChange = null,
        )
    }

    HorizontalDivider(modifier = Modifier.padding(start = 16.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    title: String,
    onBackClick: (() -> Unit)? = null,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { onBackClick?.invoke() },
                modifier = Modifier.fillMaxHeight()
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowBack),
                    contentDescription = null
                )
            }
        },
        title = {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = title,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}


@Preview
@Composable
fun Preview() {
    val state = AppWidgetConfigurationState()

    AppWidgetConfigurationContent(
        state = state,
        isDynamicColorsAvailable = true,
        onDynamicColorsCheckedChange = {  },
        onSaveClick = {},
        onBackClick = {}
    )
}