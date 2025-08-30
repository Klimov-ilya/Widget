package klimov.example.widget.widgets.ui

import androidx.annotation.StringRes

sealed class AppWidgetUiState

class AppWidgetLoadedData(
    @StringRes val titleResId: Int,
    val description: String,
    val value: Long
) : AppWidgetUiState()

